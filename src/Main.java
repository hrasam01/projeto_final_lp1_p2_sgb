// Main.java
import controller.*;
import model.*;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        UsuarioController uc = new UsuarioController();
        ItemController ic = new ItemController();
        ExemplarController ec = new ExemplarController();
        EmprestimoController ecEmp = new EmprestimoController();
        ReservaController rc = new ReservaController();


// criar usuarios
        Aluno a1 = new Aluno("U1", "Maria", "Engenharia");
        Professor p1 = new Professor("U2", "Dr. João", "Matemática");
        Funcionario f1 = new Funcionario("U3", "Carlos", "Biblioteca");


        uc.cadastrarUsuario(a1);
        uc.cadastrarUsuario(p1);
        uc.cadastrarUsuario(f1);


// criar items
        Livro l1 = new Livro("ISBN1", "Java Básico", "Autor A", "Editora X");
        Revista r1 = new Revista("ISBN2", "Revista Ciência", "Vários", 12);
        MidiaDigital m1 = new MidiaDigital("ISBN3", "Curso de BD", "Prof Y", "mp4");


        ic.cadastrarItem(l1);
        ic.cadastrarItem(r1);
        ic.cadastrarItem(m1);


// criar exemplares
        Exemplar ex1 = new Exemplar("E1", l1);
        Exemplar ex2 = new Exemplar("E2", l1);
        Exemplar ex3 = new Exemplar("E3", r1);


        ec.cadastrarExemplar(ex1);
        ec.cadastrarExemplar(ex2);
        ec.cadastrarExemplar(ex3);


// registrar emprestimo - no prazo
        ecEmp.registrarEmprestimo("EMP1", a1, ex1, LocalDate.now());


// registrar emprestimo - professor (prazo maior)
        ecEmp.registrarEmprestimo("EMP2", p1, ex2, LocalDate.now());


// simular devolução atrasada: criar emprestimo antigo
        LocalDate dataAntiga = LocalDate.now().minusDays(40);
        Exemplar ex4 = new Exemplar("E4", l1);
        ec.cadastrarExemplar(ex4);
        ecEmp.registrarEmprestimo("EMP3", a1, ex4, dataAntiga);


// devolução atrasada (hoje)
        ecEmp.registrarDevolucao("EMP3", LocalDate.now());


// mostrar usuarios e multas
        System.out.println("--- Usuarios ---");
        for (Usuario u : uc.listarUsuarios()) System.out.println(u);


// reservar um item
        Reserva reserva = new Reserva("R1", p1, l1);
        rc.criarReserva(reserva);


        System.out.println("--- Emprestimos ---");
        for (Emprestimo e : ecEmp.listarEmprestimos()) System.out.println(e);


        System.out.println("--- Reservas ---");
        for (Reserva r : rc.listarReservas()) System.out.println(r);


        System.out.println("Simulação finalizada.");
    }
}