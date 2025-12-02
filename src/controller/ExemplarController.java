package controller;


import model.Exemplar;
import service.ExemplarService;


import java.util.List;


public class ExemplarController {
    private ExemplarService exemplarService = new ExemplarService();


    public void cadastrarExemplar(Exemplar e) { exemplarService.cadastrarExemplar(e); }
    public List<Exemplar> listarExemplares() { return exemplarService.listarExemplares(); }
    public Exemplar buscarPorId(String id) { return exemplarService.buscarPorId(id); }
}