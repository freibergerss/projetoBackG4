package com.dh.projetoBackG4.dao;

import java.sql.SQLException;
import java.util.Optional;

public interface IDao <T>{
    void delete (int T);
    Optional<T> buscarPorId(int T) throws SQLException;
    public T create (T t) throws SQLException;
    public T update (T t) throws SQLException;
}
