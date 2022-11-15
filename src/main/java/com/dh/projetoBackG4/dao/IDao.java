package com.dh.projetoBackG4.dao;

import model.Dentista;

import java.sql.SQLException;

public interface IDao <T>{
    Dentista salvar(Dentista dentista) throws SQLException;

    public T create (T t) throws SQLException;
    public T update (T t) throws SQLException;

}
