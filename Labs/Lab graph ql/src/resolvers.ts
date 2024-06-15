import { getRepository } from 'typeorm';
import { Libro } from './entity/Libro';
import { Lector } from './entity/Lector';

const resolvers = {
  Query: {
    libros: async () => {
      const libroRepository = getRepository(Libro);
      return libroRepository.find();
    },
    libro: async (_: any, { id }: { id: string }) => {
      const libroRepository = getRepository(Libro);
      return libroRepository.findOne({ where: { id: parseInt(id, 10) } });
    },
    lectores: async () => {
      const lectorRepository = getRepository(Lector);
      return lectorRepository.find({ relations: ["librosPrestados"] });
    },
    lector: async (_: any, { id }: { id: string }) => {
      const lectorRepository = getRepository(Lector);
      return lectorRepository.findOne({ where: { id: parseInt(id, 10) }, relations: ["librosPrestados"] });
    },
  },
  Mutation: {
    agregarLibro: async (_: any, { titulo, autor }: { titulo: string; autor: string }) => {
      const libroRepository = getRepository(Libro);
      const nuevoLibro = libroRepository.create({ titulo, autor });
      return libroRepository.save(nuevoLibro);
    },
    agregarLector: async (_: any, { nombre }: { nombre: string }) => {
      const lectorRepository = getRepository(Lector);
      const nuevoLector = lectorRepository.create({ nombre });
      nuevoLector.librosPrestados = []; // Inicializa la relación aquí
      return lectorRepository.save(nuevoLector);
    },
    prestarLibro: async (_: any, { idLector, idLibro }: { idLector: string; idLibro: string }) => {
      const libroRepository = getRepository(Libro);
      const lectorRepository = getRepository(Lector);

      const libro = await libroRepository.findOne({ where: { id: parseInt(idLibro, 10) } });
      const lector = await lectorRepository.findOne({ where: { id: parseInt(idLector, 10) }, relations: ["librosPrestados"] });

      if (libro && lector && libro.disponible) {
        libro.disponible = false;
        lector.librosPrestados.push(libro);
        await libroRepository.save(libro);
        await lectorRepository.save(lector);
        return libro;
      }
      throw new Error('No se puede prestar el libro');
    },
    devolverLibro: async (_: any, { idLector, idLibro }: { idLector: string; idLibro: string }) => {
      const libroRepository = getRepository(Libro);
      const lectorRepository = getRepository(Lector);

      const libro = await libroRepository.findOne({ where: { id: parseInt(idLibro, 10) } });
      const lector = await lectorRepository.findOne({ where: { id: parseInt(idLector, 10) }, relations: ["librosPrestados"] });

      if (libro && lector) {
        libro.disponible = true;
        lector.librosPrestados = lector.librosPrestados.filter(lib => lib.id !== libro.id);
        await libroRepository.save(libro);
        await lectorRepository.save(lector);
        return libro;
      }
      throw new Error('No se puede devolver el libro');
    },
  },
};

export default resolvers;
