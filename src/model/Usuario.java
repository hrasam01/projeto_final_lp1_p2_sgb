package model;


import enums.Classificacao;


public abstract class Usuario {
    private String id;
    private String nome;
    private Classificacao classificacao;
    private boolean bloqueado = false;
    private double multaAcumulada = 0.0;


    public Usuario(String id, String nome, Classificacao classificacao) {
        this.id = id;
        this.nome = nome;
        this.classificacao = classificacao;
    }


    public String getId() { return id; }
    public void setId(String id) { this.id = id; }


    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }


    public Classificacao getClassificacao() { return classificacao; }
    public void setClassificacao(Classificacao classificacao) { this.classificacao = classificacao; }


    public boolean isBloqueado() { return bloqueado; }
    public void setBloqueado(boolean bloqueado) { this.bloqueado = bloqueado; }


    public double getMultaAcumulada() { return multaAcumulada; }
    public void addMulta(double valor) { this.multaAcumulada += valor; }
    public void clearMulta() { this.multaAcumulada = 0.0; }


    public abstract void quemSouEu();


    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", classificacao=" + classificacao +
                ", bloqueado=" + bloqueado +
                ", multaAcumulada=" + multaAcumulada +
                '}';
    }
}