package com.dh.projetoBackG4.service;

import com.dh.projetoBackG4.dao.IDao;
import com.dh.projetoBackG4.dao.impl.ImplDentistaDaoH2;
import com.dh.projetoBackG4.model.Dentista;


import java.sql.SQLException;

public class DentistaService {

    public Dentista create(Dentista dentista) throws SQLException {
        IDao<Dentista> dentistaDao = new ImplDentistaDaoH2();
        return dentistaDao.create(dentista);
    }

    public Dentista update(Dentista dentista) throws SQLException, ClassNotFoundException {
        IDao<Dentista> dentistaDao = new ImplDentistaDaoH2();
        return dentistaDao.update(dentista);
    }
}
