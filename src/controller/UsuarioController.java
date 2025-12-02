package controller;


import model.Usuario;
import service.UsuarioService;


import java.util.List;


public class UsuarioController {
    private UsuarioService usuarioService = new UsuarioService();


    public void cadastrarUsuario(Usuario u) { usuarioService.cadastrarUsuario(u); }
    public List<Usuario> listarUsuarios() { return usuarioService.listarUsuarios(); }
    public void deletarUsuario(String id) { usuarioService.deletarUsuario(id); }
    public Usuario buscarPorId(String id) { return usuarioService.buscarPorId(id); }
}