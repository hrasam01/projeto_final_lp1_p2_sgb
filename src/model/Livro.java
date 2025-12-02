package model;


public final class Livro extends ItemAcervo {
    private String editora;


    public Livro(String isbn, String titulo, String autor, String editora) {
        super(isbn, titulo, autor);
        this.editora = editora;
    }


    public String getEditora() { return editora; }
    public void setEditora(String editora) { this.editora = editora; }


    @Override
    public String toString() {
        return super.toString() + " Livro{editora='" + editora + "'}";
    }
}