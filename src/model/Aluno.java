package model;


import enums.Classificacao;


public final class Aluno extends Usuario {
    private String curso;


    public Aluno(String id, String nome, String curso) {
        super(id, nome, Classificacao.Aluno);
        this.curso = curso;
    }


    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }


    @Override
    public void quemSouEu() { System.out.println(Classificacao.Aluno); }


    @Override
    public String toString() {
        return super.toString() + " Aluno{curso='" + curso + "'}";
    }
}