package com.dh.projetoBackG4.dao;

import model.Paciente;

import java.sql.SQLException;
import java.util.Optional;

public interface IDao <T>{
    T create(T t) throws SQLException;
    T update(T t) throws SQLException;
    void deletePaciente(int idPaciente);

    Optional<Paciente> buscarPorId(int idPaciente) throws SQLException;





}
