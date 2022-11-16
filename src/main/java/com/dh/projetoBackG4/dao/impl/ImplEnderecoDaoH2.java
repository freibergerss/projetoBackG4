package com.dh.projetoBackG4.dao.impl;

import com.dh.projetoBackG4.dao.ConfigJDBC;
import com.dh.projetoBackG4.dao.IDao;
import com.dh.projetoBackG4.model.Endereco;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Optional;


public class ImplEnderecoDaoH2 implements IDao<Endereco> {

    final static Logger log = LoggerFactory.getLogger(ImplEnderecoDaoH2.class);

    private ConfigJDBC configJDBC;

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        configJDBC = new ConfigJDBC("org.h2.Driver","jdbc:h2:~/clinica;AUTO_SERVER=TRUE;INIT=RUNSCRIPT FROM 'create.sql'", "sa","");
        return configJDBC.getConnection();
    }

    @Override
    public Endereco create(Endereco endereco) throws SQLException {

        Connection connection = null;

        String SQLInsert = String.format("INSERT INTO endereco(rua, numero, bairro, cidade, estado) VALUES('%s', '%s', '%s', '%s', '%s')", endereco.getRua(), endereco.getNumero(), endereco.getBairro(), endereco.getCidade(), endereco.getEstado());

        try {
            log.info("Abrindo conexão com banco de dados");
            connection = getConnection();
            Statement statement = connection.createStatement();

            log.info("Cadastrando novo endereço");
            statement.execute(SQLInsert, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                endereco.setidEndereco(resultSet.getInt("idEndereco"));

            }

        } catch (Exception e) {

            log.error("Erro ao processar banco de dados");
            e.printStackTrace();

        }finally {
            log.info("Fechando conexão com banco de dados");
            connection.close();
        }

        return endereco;
    }

        @Override
    public Endereco update(Endereco endereco) throws SQLException{
        Connection connection = null;

        String UPDATE = "UPDATE endereco SET rua=?, numero=?, bairro=?, cidade=?, estado=? WHERE idEndereco = ?;";

        try{
            log.info("Abrindo conexão com banco de dados");
            connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, endereco.getRua());
            preparedStatement.setInt(2, endereco.getNumero());
            preparedStatement.setString(3, endereco.getBairro());
            preparedStatement.setString(4, endereco.getCidade());
            preparedStatement.setString(5, endereco.getEstado());
            preparedStatement.setInt(6, endereco.getidEndereco());

            log.info("Atualizando dados");
            preparedStatement.executeUpdate();

        }catch(Exception e){
            log.error("Erro ao processar banco de dados");
            e.printStackTrace();

        }finally {
            log.info("Fechando conexão com banco de dados");
            connection.close();
        }

        return endereco;
    }


    @Override
    public void delete(int idEndereco) {
        System.out.println("Funcionalidade não disponível");
    }


    @Override
    public Optional<Endereco> buscarPorId(int idEndereco) throws SQLException {

        Connection connection = null;

        String QUERY = "SELECT idEndereco, rua, numero, bairro, cidade, estado FROM endereco WHERE idEndereco = ?";

        Endereco endereco = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setInt(1, idEndereco);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int key = resultSet.getInt("idEndereco");
                String rua = resultSet.getString("rua");
                Integer numero = resultSet.getInt("numero");
                String bairro = resultSet.getString("bairro");
                String cidade = resultSet.getString("cidade");
                String estado = resultSet.getString("estado");

                endereco = new Endereco(key, rua, numero, bairro, cidade, estado);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //SERA QUE ADD UM FINALLY P/ FECHAR A CONEXÃO?

        return Optional.ofNullable(endereco);
    }

}
