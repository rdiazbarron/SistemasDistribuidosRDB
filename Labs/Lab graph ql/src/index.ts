import 'reflect-metadata';
import { createConnection } from 'typeorm';
import { ApolloServer } from 'apollo-server';
import typeDefs from './schema';
import resolvers from './resolvers';
import { Registro } from './entity/Registro';

createConnection({
  type: 'mysql',
  database: 'database.mysql',
  entities: [Registro],
  synchronize: true,
  logging: false,
}).then(async connection => {
  const server = new ApolloServer({ typeDefs, resolvers });

  server.listen().then(({ url }) => {
    console.log(`🚀 Server ready at ${url}`);
  });
}).catch(error => console.log(error));
