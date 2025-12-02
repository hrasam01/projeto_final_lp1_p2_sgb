package service;


import model.*;
import repository.InMemoryRepository;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


public class EmprestimoService {
    private InMemoryRepository<Emprestimo> repo = new InMemoryRepository<>();
    private ExemplarService exemplarService;


    public EmprestimoService(ExemplarService exemplarService) {
        this.exemplarService = exemplarService;
    }


    // regras: prazos por tipo
    private int prazoPadrao(Usuario u) {
        if (u instanceof Professor) return 30;
        if (u instanceof Funcionario) return 7;
        return 14; // Aluno
    }


    public void registrarEmprestimo(String id, Usuario usuario, Exemplar exemplar, LocalDate dataEmprestimo) {
        if (usuario.isBloqueado()) {
            System.out.println("Usuário bloqueado: não pode emprestar.");
            return;
        }
        if (exemplar.isEmprestado()) {
            System.out.println("Exemplar já emprestado.");
            return;
        }
        int prazo = prazoPadrao(usuario);
        Emprestimo emp = new Emprestimo(id, usuario, exemplar, dataEmprestimo, dataEmprestimo.plusDays(prazo));
        exemplar.setEmprestado(true);
        repo.salvar(emp);
        System.out.println("Emprestimo registrado: " + emp);
    }


    public List<Emprestimo> listarEmprestimos() { return repo.listar(); }


    public void registrarDevolucao(String idEmprestimo, LocalDate dataDevolucao) {
        Emprestimo alvo = null;
        for (Emprestimo e : repo.listar()) if (e.getId().equals(idEmprestimo)) { alvo = e; break; }
        if (alvo == null) { System.out.println("Emprestimo não encontrado."); return; }


        alvo.setDataDevolucao(dataDevolucao);
        Exemplar ex = alvo.getExemplar();
        ex.setEmprestado(false);


        long diasAtraso = ChronoUnit.DAYS.between(alvo.getDataPrevistaDevolucao(), dataDevolucao);
        if (diasAtraso > 0) {
            double multa = calcularMulta(diasAtraso);
            alvo.setMultaCalculada(multa);
            alvo.getUsuario().addMulta(multa);
            System.out.println("Devolução com atraso: dias=" + diasAtraso + " multa=" + multa);
// bloqueia se multa alta
            if (alvo.getUsuario().getMultaAcumulada() > 50.0) {
                alvo.getUsuario().setBloqueado(true);
                System.out.println("Usuário bloqueado por multa acumulada.");
            }
        } else {
            System.out.println("Devolução no prazo. Obrigado!");
        }
    }


    private double calcularMulta(long diasAtraso) {
        double valorPorDia = 2.0; // regra simples
        return diasAtraso * valorPorDia;
    }
}