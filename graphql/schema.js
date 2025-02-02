const { GraphQLObjectType, GraphQLString, GraphQLSchema, GraphQLList, GraphQLNonNull } = require('graphql');
const { Participante } = require('./database');

const ParticipanteType = new GraphQLObjectType({
  name: 'Participante',
  fields: {
    carnet: { type: GraphQLString },
    nombres: { type: GraphQLString },
    apellidos: { type: GraphQLString },
    fecha_nacimiento: { type: GraphQLString }
  }
});

const RootQuery = new GraphQLObjectType({
  name: 'RootQueryType',
  fields: {
    participantes: {
      type: new GraphQLList(ParticipanteType),
      resolve(parent, args) {
        return Participante.findAll();
      }
    },
    participante: {
      type: ParticipanteType,
      args: { carnet: { type: GraphQLString } },
      resolve(parent, args) {
        return Participante.findByPk(args.carnet);
      }
    }
  }
});


const Mutation = new GraphQLObjectType({
  name: 'Mutation',
  fields: {
    addParticipante: {
      type: ParticipanteType,
      args: {
        carnet: { type: new GraphQLNonNull(GraphQLString) },
        nombres: { type: new GraphQLNonNull(GraphQLString) },
        apellidos: { type: new GraphQLNonNull(GraphQLString) },
        fecha_nacimiento: { type: new GraphQLNonNull(GraphQLString) }
      },
      resolve(parent, args) {
        return Participante.create({
          carnet: args.carnet,
          nombres: args.nombres,
          apellidos: args.apellidos,
          fecha_nacimiento: args.fecha_nacimiento
        });
      }
    },
    updateParticipante: {
      type: ParticipanteType,
      args: {
        carnet: { type: new GraphQLNonNull(GraphQLString) },
        nombres: { type: GraphQLString },
        apellidos: { type: GraphQLString },
        fecha_nacimiento: { type: GraphQLString }
      },
      resolve(parent, args) {
        return Participante.findByPk(args.carnet)
          .then(participante => {
            if (!participante) {
              throw new Error('Participante no encontrado');
            }
            return participante.update({
              nombres: args.nombres !== undefined ? args.nombres : participante.nombres,
              apellidos: args.apellidos !== undefined ? args.apellidos : participante.apellidos,
           
              fecha_nacimiento: args.fecha_nacimiento !== undefined ? args.fecha_nacimiento : participante.fecha_nacimiento
            });
          });
      }
    }
  }
});

module.exports = new GraphQLSchema({
  query: RootQuery,
  mutation: Mutation
});