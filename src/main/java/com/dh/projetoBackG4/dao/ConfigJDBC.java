package com.dh.projetoBackG4.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigJDBC {

    private String jdbcDriver;
    private String dbUrl;
    private String user;
    private String password;

    public ConfigJDBC(String jdbcDriver, String dbUrl, String user, String password) {
        this.jdbcDriver = jdbcDriver;
        this.dbUrl = dbUrl;
        this.user = user;
        this.password = password;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName(jdbcDriver);

        return DriverManager.getConnection(dbUrl, user, password);
    }
}
