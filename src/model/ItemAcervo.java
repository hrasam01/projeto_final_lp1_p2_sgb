package model;


public abstract class ItemAcervo {
    private String isbn; // ou identificador
    private String titulo;
    private String autor;


    public ItemAcervo(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
    }


    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }


    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }


    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }


    @Override
    public String toString() {
        return "ItemAcervo{" +
                "isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                '}';
    }
}