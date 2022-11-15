package com.dh.projetoBackG4.dao.impl;

import com.dh.projetoBackG4.dao.ConfigJDBC;
import com.dh.projetoBackG4.dao.IDao;
import model.Paciente;

import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

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
        Connection connection = null;
//
        String SQLUpdate = "UPDATE pacientes SET nome = ?, sobrenome = ?, rg = ?, endereco = ?, dataCadastro = ? WHERE idPaciente = ?;";
//
//        PreparedStatement preparedStatement = connection.prepareStatement(SQLUpdate);
//        preparedStatement.setString(1, paciente.getNome());
//        preparedStatement.setString(2,paciente.getSobrenome());
//        preparedStatement.setString(3,paciente.getRg());
//        preparedStatement.setString(4,paciente.getEndereco());
//        preparedStatement.setDate(5,java.sql.Date.valueOf(paciente.getDataCadastro()));
//        preparedStatement.setInt(6, paciente.getIdPaciente());
//
//        String SQLUpdate = ("UPDATE pacientes SET nome = ?, sobrenome = ?, rg = ?, endereco = ? WHERE idPaciente = ?;",
//        paciente.getNome(), paciente.getSobrenome(),paciente.getRg(), paciente.getEndereco(), paciente.getDataCadastro());
//

        try{
            connection = getConnection();
//            Statement statement = connection.createStatement();

//            String SQLUpdate = "UPDATE pacientes SET nome = ?, sobrenome = ?, rg = ?, endereco = ?, dataCadastro = ? WHERE idPaciente = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQLUpdate);
            preparedStatement.setString(1, paciente.getNome());
            preparedStatement.setString(2,paciente.getSobrenome());
            preparedStatement.setString(3,paciente.getRg());
            preparedStatement.setString(4,paciente.getEndereco());
            preparedStatement.setDate(5,java.sql.Date.valueOf(paciente.getDataCadastro()));
            preparedStatement.setInt(6, paciente.getIdPaciente());

            preparedStatement.execute();

//            statement.execute(SQLUpdate);
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return paciente;
    }

    @Override
    public void deletePaciente(int idPaciente) {

    }

    @Override
    public Optional<Paciente> buscarPorId(int idPaciente) throws SQLException {
        Connection connection = null;
        String SQLBuscaPorId = "SELECT idPaciente, nome, sobrenome, rg, endereco, dataCadastro FROM pacientes WHERE idPaciente = ?";
        Paciente paciente = null;

        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLBuscaPorId);
            preparedStatement.setInt(1, idPaciente);
//            Statement statement = connection.createStatement();

            ResultSet resultset = preparedStatement.executeQuery();

            while (resultset.next()){
                int pKey = resultset.getInt("idPaciente");
                String nome = resultset.getString("nome");
                String sobrenome = resultset.getString("sobrenome");
                String rg = resultset.getString("rg");
                String endereco = resultset.getString("endereco");
                LocalDate data = resultset.getDate("dataCadastro").toLocalDate();

                paciente = new Paciente(pKey, nome, sobrenome, rg, endereco, data);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return Optional.ofNullable(paciente);
    }

}
