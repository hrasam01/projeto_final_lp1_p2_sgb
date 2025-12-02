package model;


public final class MidiaDigital extends ItemAcervo {
    private String formato; // pdf, mp3, mp4


    public MidiaDigital(String isbn, String titulo, String autor, String formato) {
        super(isbn, titulo, autor);
        this.formato = formato;
    }


    public String getFormato() { return formato; }
    public void setFormato(String formato) { this.formato = formato; }


    @Override
    public String toString() {
        return super.toString() + " MidiaDigital{formato='" + formato + "'}";
    }
}