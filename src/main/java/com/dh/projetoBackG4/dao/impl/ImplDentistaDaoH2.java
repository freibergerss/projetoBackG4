package com.dh.projetoBackG4.dao.impl;

import com.dh.projetoBackG4.dao.ConfigJDBC;
import com.dh.projetoBackG4.dao.IDao;
import model.Dentista;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ImplDentistaDaoH2 implements IDao<Dentista> {

    final static Logger log = LoggerFactory.getLogger(ImplDentistaDaoH2.class);

    private ConfigJDBC configJDBC;

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        configJDBC = new ConfigJDBC("org.h2.Driver", "jdbc:h2:~/odonto;AUTO_SERVER=TRUE;INIT=RUNSCRIPT FROM 'create.sql'", "g4","");
        return configJDBC.getConnection();
    }

    @Override
    public Dentista salvar(Dentista dentista) throws SQLException {
        Connection connection = null;
        String SQLInsert = String.format("INSERT INTO cadastro(nome, sobrenome, idDentista) VALUES ('%s', '%s', '%s')",
                dentista.getNome(), dentista.getSobrenome(), dentista.getIdDentista());

        try{
            log.info("Abrindo conexão com banco de dados");
            connection = getConnection();
            Statement statement = connection.createStatement();

            statement.execute(SQLInsert, Statement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = statement.getGeneratedKeys();

            if(resultSet.next()){
                dentista.setIdDentista(resultSet.getInt("id"));
            }

        }catch(Exception e){

            log.error("Erro no banco de dados");
            e.printStackTrace();

        }finally{
            log.info("Fechando a conexão com banco de dados");
            connection.close();
        }

        return dentista;
    }

    @Override
    public Dentista create(Dentista dentista) throws SQLException {
        return null;
    }

    @Override
    public Dentista update(Dentista dentista) throws SQLException {
        return null;
    }
}
