package com.dh.projetoBackG4.dao.impl;

import com.dh.projetoBackG4.dao.ConfigJDBC;
import com.dh.projetoBackG4.dao.IDao;
import model.Endereco;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.logging.Logger;

public class ImplEnderecoDaoH2 implements IDao<Endereco> {

    //------PORQUE TIVE QUE COLOCAR O String.valueOf NO LOGGER?????------
    final static Logger log = Logger.getLogger(String.valueOf(ImplEnderecoDaoH2.class));

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

            statement.execute(SQLInsert, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                endereco.setidEndereco(resultSet.getInt("idEndereco"));

            }

        } catch (Exception e) {

            //------NÃO ESTOU CONSEGUINDO APLICAR "log.error" ???------
            log.info("Erro ao processar banco de dados");
            e.printStackTrace();

        }finally {
            log.info("Fechando conexão com banco de dados");
            connection.close();
        }

        return endereco;
    }

        @Override
    public Endereco update(Endereco endereco) throws SQLException {


        return null;
    }

    @Override
    public void delete(int T) {

    }

    @Override
    public Optional<Endereco> buscarPorId(int T) throws SQLException {
        return Optional.empty();
    }



}
