package com.dh.projetoBackG4.dao.impl;

import com.dh.projetoBackG4.dao.ConfigJDBC;
import com.dh.projetoBackG4.dao.IDao;
import model.Paciente;

import java.sql.*;

public class ImplPacienteDaoH2 implements IDao<Paciente> {

    private ConfigJDBC configJDBC;

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        configJDBC = new ConfigJDBC("org.h2.Driver","jdbc:h2:~/clinica;AUTO_SERVER=TRUE;INIT=RUNSCRIPT FROM 'create.sql'", "sa","");
        return configJDBC.getConnection();
    }


    @Override
    public Paciente create(Paciente paciente) throws SQLException {
        Connection connection = null;
//        String SQLInsert = String.format("INSERT INTO pacientes(nome, sobrenome, rg, endereco, dataCadastro) VALUES(?, ?, ?, ?, ?");

//        PreparedStatement preparedStatement = connection.prepareStatement(SQLInsert, Statement.RETURN_GENERATED_KEYS);
//        preparedStatement.setString(1, paciente.getNome());
//        preparedStatement.setString(2,paciente.getSobrenome());
//        preparedStatement.setString(3,paciente.getRg());
//        preparedStatement.setString(4,paciente.getEndereco());
//        preparedStatement.setDate(3,java.sql.Date.valueOf(paciente.getDataCadastro()));

        String SQLInsert = String.format("INSERT INTO pacientes(nome, sobrenome, rg, endereco, dataCadastro) VALUES('%s', '%s','%s','%s', '%s')",
        paciente.getNome(), paciente.getSobrenome(),paciente.getRg(), paciente.getEndereco(), paciente.getDataCadastro());

        try{
            connection = getConnection();
            Statement statement = connection.createStatement();

            statement.execute(SQLInsert, Statement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = statement.getGeneratedKeys();

            if(resultSet.next()) {
                paciente.setIdPaciente(resultSet.getInt("idPaciente"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
        }

        return paciente;
    }

    @Override
    public Paciente update(Paciente paciente) throws SQLException {
        return null;
    }
}
