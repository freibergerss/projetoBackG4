package com.dh.projetoBackG4.model;

import java.time.LocalDate;
import java.util.Date;

public class Paciente {

    private int idPaciente;
    private String nome;
    private String sobrenome;
    private String rg;
    private String endereco;
    private LocalDate dataCadastro;

    public Paciente() {

    }

    public Paciente(int id, String nome, String sobrenome, String rg, String endereco, LocalDate dataCadastro) {
        this.idPaciente = idPaciente;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.rg = rg;
        this.endereco = endereco;
        this.dataCadastro = dataCadastro;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getRg() {
        return rg;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
