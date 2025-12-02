package model;


import enums.Classificacao;


public final class Professor extends Usuario {
    private String especializacao;


    public Professor(String id, String nome, String especializacao) {
        super(id, nome, Classificacao.Professor);
        this.especializacao = especializacao;
    }


    public String getEspecializacao() { return especializacao; }
    public void setEspecializacao(String especializacao) { this.especializacao = especializacao; }


    @Override
    public void quemSouEu() { System.out.println(Classificacao.Professor); }


    @Override
    public String toString() {
        return super.toString() + " Professor{especializacao='" + especializacao + "'}";
    }
}