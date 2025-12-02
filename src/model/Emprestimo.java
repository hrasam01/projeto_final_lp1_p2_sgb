package model;


import java.time.LocalDate;


public class Emprestimo {
    private String id;
    private Usuario usuario;
    private Exemplar exemplar;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    private double multaCalculada = 0.0;


    public Emprestimo(String id, Usuario usuario, Exemplar exemplar, LocalDate dataEmprestimo, LocalDate dataPrevistaDevolucao) {
        this.id = id;
        this.usuario = usuario;
        this.exemplar = exemplar;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }


    public String getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public Exemplar getExemplar() { return exemplar; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public LocalDate getDataPrevistaDevolucao() { return dataPrevistaDevolucao; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }
    public void setDataDevolucao(LocalDate d) { this.dataDevolucao = d; }
    public double getMultaCalculada() { return multaCalculada; }
    public void setMultaCalculada(double multa) { this.multaCalculada = multa; }


    @Override
    public String toString() {
        return "Emprestimo{" +
                "id='" + id + '\'' +
                ", usuario=" + usuario.getNome() +
                ", exemplar=" + exemplar.getId() +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataPrevistaDevolucao=" + dataPrevistaDevolucao +
                ", dataDevolucao=" + dataDevolucao +
                ", multaCalculada=" + multaCalculada +
                '}';
    }
}