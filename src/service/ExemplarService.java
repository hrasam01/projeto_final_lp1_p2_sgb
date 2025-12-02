package service;


import model.*;
import repository.InMemoryRepository;


import java.util.List;


public class ExemplarService {
    private InMemoryRepository<Exemplar> repo = new InMemoryRepository<>();


    public void cadastrarExemplar(Exemplar e) { repo.salvar(e); }
    public List<Exemplar> listarExemplares() { return repo.listar(); }
    public Exemplar buscarPorId(String id) {
        for (Exemplar e : repo.listar()) if (e.getId().equals(id)) return e;
        return null;
    }
}