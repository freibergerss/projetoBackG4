package model;

import java.time.LocalDate;
import java.util.Date;

public class Paciente {

    private String nome;
    private String sobrenome;
    private String rg;
    private String endereco;
    private LocalDate dataCadastro;

    public Paciente() {

    }

    public Paciente(String nome, String sobrenome, String rg, String endereco, LocalDate dataCadastro) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.rg = rg;
        this.endereco = endereco;
        this.dataCadastro = dataCadastro;
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
