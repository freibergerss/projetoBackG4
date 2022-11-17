package com.dh.projetoBackG4.dao.impl;

import com.dh.projetoBackG4.dao.ConfigJDBC;
import com.dh.projetoBackG4.dao.IDao;
import com.dh.projetoBackG4.model.Dentista;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Optional;

public class ImplDentistaDaoH2 implements IDao<Dentista> {

    final static Logger log = LoggerFactory.getLogger(ImplDentistaDaoH2.class);

    private ConfigJDBC configJDBC;

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        configJDBC = new ConfigJDBC("org.h2.Driver", "jdbc:h2:~/clinica;AUTO_SERVER=TRUE;INIT=RUNSCRIPT FROM 'create.sql'", "sa","");
        return configJDBC.getConnection();
    }

    @Override
    public Dentista create(Dentista dentista) throws SQLException {

        Connection connection = null;

        String SQLInsert = String.format("INSERT INTO dentistas(nome, sobrenome) VALUES ('%s', '%s')",
                dentista.getNome(), dentista.getSobrenome());

        try{
            log.info("Abrindo conexão com banco de dados");
            connection = getConnection();
            Statement statement = connection.createStatement();

            log.info("Cadastrando novo dentista");
            statement.execute(SQLInsert, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();

            if(resultSet.next()){
                dentista.setIdDentista(resultSet.getInt("idDentista"));
            }

        }catch(Exception e){

            log.error("Erro ao processar banco de dados");
            e.printStackTrace();

        }finally{
            log.info("Fechando conexão com banco de dados");
            connection.close();
        }

        return dentista;
    }

    @Override
    public Dentista update(Dentista dentista) throws SQLException {
        Connection connection = null;

        String UPDATE = "UPDATE dentistas SET nome=?, sobrenome=? WHERE idDentista = ?;";

        try{
            log.info("Abrindo conexão com banco de dados");
            connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, dentista.getNome());
            preparedStatement.setString(2, dentista.getSobrenome());
            preparedStatement.setInt(3, dentista.getIdDentista());

            log.info("Atualizando dados");
            preparedStatement.executeUpdate();

        }catch (Exception e){
            log.error("Erro ao processar banco de dados");
            e.printStackTrace();

        }finally{
            log.info("Fechando conexão com banco de dados");
            connection.close();
        }

        return null;
    }

    @Override
    public void delete(int idDentista) {
        System.out.println("Funcionalidade não disponível");
    }

    @Override
    public Optional<Dentista> buscarPorId(int idDentista) throws SQLException {

        Connection connection = null;

        String QUERY = "SELECT idDentista, nome, sobrenome FROM dentistas WHERE idDentista = ?";

        Dentista dentista = null;

        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setInt(1, idDentista);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int key = resultSet.getInt("idDentista");
                String nome = resultSet.getString("nome");
                String sobrenome = resultSet.getString("sobrenome");

                dentista = new Dentista(nome, sobrenome, key);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        //SERA QUE ADD UM FINALLY P/ FECHAR A CONEXÃO?

        return Optional.ofNullable(dentista);
    }

}
