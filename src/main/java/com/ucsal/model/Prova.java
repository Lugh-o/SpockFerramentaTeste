package com.ucsal.model;

public class Prova {
    private String aluno;
    private int acertos;
    private int erros;

    public Prova(String aluno, int acertos, int erros) {
        this.aluno = aluno;
        this.acertos = acertos;
        this.erros = erros;
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public int getAcertos() {
        return acertos;
    }

    public void setAcertos(int acertos) {
        this.acertos = acertos;
    }

    public int getErros() {
        return erros;
    }

    public void setErros(int erros) {
        this.erros = erros;
    }
}