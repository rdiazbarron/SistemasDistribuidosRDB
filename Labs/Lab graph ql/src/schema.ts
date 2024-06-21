import { gql } from 'apollo-server';

const typeDefs = gql`
  type Registro {
    id: ID!
    fecha: String!
    temperatura: Int!
  }

  type Query {
    registros: [Registro]
    registro(id: ID!): Registro
  }

  type Mutation {
    agregarRegistro(fecha: String!, temperatura: Int!): Registro
  }
`;

export default typeDefs;
