import { getRepository } from 'typeorm';
import { Registro } from './entity/Registro';

const resolvers = {
  Query: {
    registros: async () => {
      const registroRepository = getRepository(Registro);
      return registroRepository.find();
    },
    registro: async (_: any, { id }: { id: string }) => {
      const registroRepository = getRepository(Registro);
      return registroRepository.findOne({ where: { id: parseInt(id, 10) } });
    },
  },
  Mutation: {
    agregarRegistro: async (_: any, { fecha, temperatura }: { fecha: string; temperatura: number }) => {
      const registroRepository = getRepository(Registro);
      const nuevoRegistro = registroRepository.create({ fecha: new Date(fecha), temperatura });
      return registroRepository.save(nuevoRegistro);
    },
  },
};

export default resolvers;
