package controller;


import model.*;
import service.EmprestimoService;
import service.ExemplarService;


import java.time.LocalDate;
import java.util.List;


public class EmprestimoController {
    private ExemplarService exemplarService = new ExemplarService();
    private EmprestimoService emprestimoService = new EmprestimoService(exemplarService);


    public void registrarEmprestimo(String id, Usuario usuario, Exemplar exemplar, LocalDate data) {
        emprestimoService.registrarEmprestimo(id, usuario, exemplar, data);
    }
    public List<Emprestimo> listarEmprestimos() { return emprestimoService.listarEmprestimos(); }
    public void registrarDevolucao(String idEmprestimo, LocalDate dataDevolucao) { emprestimoService.registrarDevolucao(idEmprestimo, dataDevolucao); }
}