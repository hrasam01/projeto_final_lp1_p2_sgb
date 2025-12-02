package repository;


import java.util.ArrayList;
import java.util.List;


public class InMemoryRepository<T> {
    protected List<T> itens = new ArrayList<>();


    public void salvar(T t) { itens.add(t); }
    public List<T> listar() { return itens; }
    public void remover(T t) { itens.remove(t); }
}