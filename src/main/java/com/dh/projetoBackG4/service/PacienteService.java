package com.dh.projetoBackG4.service;

import com.dh.projetoBackG4.dao.IDao;
import com.dh.projetoBackG4.dao.impl.ImplPacienteDaoH2;
import com.dh.projetoBackG4.model.Paciente;

import java.sql.SQLException;

public class PacienteService {

    public Paciente create(Paciente paciente) throws SQLException{
        IDao<Paciente> pacienteDao = new ImplPacienteDaoH2();
        return pacienteDao.create(paciente);
    }

    public Paciente update(Paciente paciente) throws SQLException{
        IDao<Paciente> pacienteDao = new ImplPacienteDaoH2();
        return pacienteDao.update(paciente);
    }
}
