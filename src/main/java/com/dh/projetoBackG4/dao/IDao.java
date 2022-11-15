package com.dh.projetoBackG4.dao;

import java.sql.SQLException;

public interface IDao <T>{
    public T create (T t) throws SQLException;
    public T update (T t) throws SQLException;

}
