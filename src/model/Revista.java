package model;


public final class Revista extends ItemAcervo {
    private int numero;


    public Revista(String isbn, String titulo, String autor, int numero) {
        super(isbn, titulo, autor);
        this.numero = numero;
    }


    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }


    @Override
    public String toString() {
        return super.toString() + " Revista{numero=" + numero + "}";
    }
}