package controller;


import model.ItemAcervo;
import service.ItemService;


import java.util.List;


public class ItemController {
    private ItemService itemService = new ItemService();


    public void cadastrarItem(ItemAcervo item) { itemService.cadastrarItem(item); }
    public List<ItemAcervo> listarItems() { return itemService.listarItems(); }
    public ItemAcervo buscarPorIsbn(String isbn) { return itemService.buscarPorIsbn(isbn); }
    public void deletarItem(String isbn) { itemService.deletarItem(isbn); }
}