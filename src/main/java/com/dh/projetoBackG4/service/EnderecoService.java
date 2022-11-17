package com.dh.projetoBackG4.service;

import com.dh.projetoBackG4.dao.IDao;
import com.dh.projetoBackG4.dao.impl.ImplEnderecoDaoH2;
import com.dh.projetoBackG4.model.Endereco;

import java.sql.SQLException;

public class EnderecoService {

    public Endereco create(Endereco endereco) throws SQLException{
        IDao<Endereco> enderecoDao = new ImplEnderecoDaoH2();
        return enderecoDao.create(endereco);
    }

    public Endereco update(Endereco endereco) throws SQLException, ClassNotFoundException {
        IDao<Endereco> enderecoDao = new ImplEnderecoDaoH2();
        return enderecoDao.update(endereco);
    }
}
