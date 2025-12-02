package model;


public class Exemplar {
    private String id; // identificador do exemplar
    private ItemAcervo item;
    private boolean emprestado = false;


    public Exemplar(String id, ItemAcervo item) {
        this.id = id;
        this.item = item;
    }


    public String getId() { return id; }
    public void setId(String id) { this.id = id; }


    public ItemAcervo getItem() { return item; }
    public void setItem(ItemAcervo item) { this.item = item; }


    public boolean isEmprestado() { return emprestado; }
    public void setEmprestado(boolean emprestado) { this.emprestado = emprestado; }


    @Override
    public String toString() {
        return "Exemplar{" + "id='" + id + '\'' + ", item=" + item.getTitulo() + ", emprestado=" + emprestado + '}';
    }
}