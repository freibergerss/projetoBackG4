package com.dh.projetoBackG4.model;

public class Dentista {
    private String nome;
    private String sobrenome;
    private int idDentista;

    public Dentista(String nome, String sobrenome, int idDentista) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idDentista = idDentista;
    }

    public Dentista(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
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

    public int getIdDentista() {
        return idDentista;
    }

    public void setIdDentista(int idDentista) {
        this.idDentista = idDentista;
    }
}

