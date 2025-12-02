package model;


import model.Usuario;
import model.ItemAcervo;


public class Reserva {
    private String id;
    private Usuario usuario;
    private ItemAcervo item;


    public Reserva(String id, Usuario usuario, ItemAcervo item) {
        this.id = id;
        this.usuario = usuario;
        this.item = item;
    }


    public String getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public ItemAcervo getItem() { return item; }


    @Override
    public String toString() {
        return "Reserva{" + "id='" + id + '\'' + ", usuario=" + usuario.getNome() + ", item=" + item.getTitulo() + '}';
    }
}