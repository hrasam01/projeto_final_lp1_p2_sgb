package service;


import model.*;
import repository.InMemoryRepository;


import java.util.List;


public class UsuarioService {
    private InMemoryRepository<Usuario> repo = new InMemoryRepository<>();


    public void cadastrarUsuario(Usuario u) { repo.salvar(u); }
    public List<Usuario> listarUsuarios() { return repo.listar(); }
    public Usuario buscarPorId(String id) {
        for (Usuario u : repo.listar()) if (u.getId().equals(id)) return u;
        return null;
    }
    public void deletarUsuario(String id) {
        Usuario alvo = buscarPorId(id);
        if (alvo != null) repo.remover(alvo);
    }
}