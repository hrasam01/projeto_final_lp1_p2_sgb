package service;


import model.*;
import repository.InMemoryRepository;


import java.util.List;


public class ItemService {
    private InMemoryRepository<ItemAcervo> repo = new InMemoryRepository<>();


    public void cadastrarItem(ItemAcervo item) { repo.salvar(item); }
    public List<ItemAcervo> listarItems() { return repo.listar(); }
    public ItemAcervo buscarPorIsbn(String isbn) {
        for (ItemAcervo it : repo.listar()) if (it.getIsbn().equals(isbn)) return it;
        return null;
    }
    public void deletarItem(String isbn) {
        ItemAcervo alvo = buscarPorIsbn(isbn);
        if (alvo != null) repo.remover(alvo);
    }
}