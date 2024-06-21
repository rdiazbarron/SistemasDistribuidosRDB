const {Sequelize} = require ('sequelize');

const sequelize = new Sequelize('bd_ccbol', 'root', '', {
    host: 'localhost',
  dialect: 'mysql'
});
//faztcode
const Participante = sequelize.define('Participante', {
    carnet: {
      type: Sequelize.STRING,
      allowNull: false,
      primaryKey: true
    },

    nombres: {
      type: Sequelize.STRING,
      allowNull: false
    },

    apellidos: {
      type: Sequelize.STRING,
      allowNull: false
    },

    fecha_nacimiento: {
        type: Sequelize.DATE,
        allowNull: false
    },
    carrera: {
      type: Sequelize.STRING,
      allowNull: false
    },
    
  }, {
    tableName: 'participantes',
    timestamps: false
  });
  
  sequelize.sync();
  
  module.exports = { sequelize, Participante };