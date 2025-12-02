package model;


import enums.Classificacao;


public final class Funcionario extends Usuario {
    private String setor;


    public Funcionario(String id, String nome, String setor) {
        super(id, nome, Classificacao.Funcionario);
        this.setor = setor;
    }


    public String getSetor() { return setor; }
    public void setSetor(String setor) { this.setor = setor; }


    @Override
    public void quemSouEu() { System.out.println(Classificacao.Funcionario); }


    @Override
    public String toString() {
        return super.toString() + " Funcionario{setor='" + setor + "'}";
    }
}