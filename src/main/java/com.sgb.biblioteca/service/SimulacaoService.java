// com.sgb.biblioteca.service.SimulacaoService.java
package com.sgb.biblioteca.service;

import com.sgb.biblioteca.model.*;
import com.sgb.biblioteca.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.time.LocalDate;
import java.math.BigDecimal;

@Service
public class SimulacaoService {

    private final UsuarioRepository usuarioRepository;
    private final ItemAcervoRepository itemAcervoRepository;
    private final ExemplarRepository exemplarRepository;
    private final EmprestimoService emprestimoService; // Injeta o Service principal

    // Injeção via construtor
    public SimulacaoService(UsuarioRepository usuarioRepository,
                            ItemAcervoRepository itemAcervoRepository,
                            ExemplarRepository exemplarRepository,
                            EmprestimoService emprestimoService) {
        this.usuarioRepository = usuarioRepository;
        this.itemAcervoRepository = itemAcervoRepository;
        this.exemplarRepository = exemplarRepository;
        this.emprestimoService = emprestimoService;
    }

    // O uso de @Transactional garante que todas as operações sejam tratadas como uma única unidade.
    @Transactional
    public void executarSimulacao() {
        // 1. CARREGAR DADOS DE TESTE
        System.out.println("\n[SETUP] 1. Criando Usuários (Herança & Polimorfismo)...");
        Usuario aluno = criarUsuario(new Aluno(), "A123", "Joao Aluno");
        Usuario professor = criarUsuario(new Professor(), "P456", "Maria Professor");
        Usuario funcionario = criarUsuario(new Funcionario(), "F789", "Joalis Funcionario");

        System.out.println("\n[SETUP] 2. Criando Itens de Acervo (Herança & Polimorfismo)...");
        ItemAcervo livro1 = criarItem(new Livro(), "O Polimorfismo", "A. OO", "111");
        ItemAcervo livro2 = criarItem(new Livro(), "O Encapsulamento", "B. Spring", "222");
        ItemAcervo midia = criarItem(new MidiaDigital(), "CD de Aulas", "C. Tech", "333");
        ItemAcervo revista = criarItem(new Revista(), "Revista Científica", "D. Data", "444");

        System.out.println("\n[SETUP] 3. Criando Exemplares (Estoque - Abstração)...");
        // Cria 3 cópias do Livro 1
        criarExemplares(livro1, 3);
        // Cria 1 cópia da Mídia
        criarExemplares(midia, 1);

        // ---- 4. FLUXO DE TESTE (DEMONSTRAÇÃO DE REGRAS) ----

        // T1: Empréstimo BEM SUCEDIDO (Aluno - Livro)
        tentarEmprestimo(aluno.getId(), livro1.getId(), "OK (Empréstimo Aluno)");

        // T2: Empréstimo BEM SUCEDIDO (Professor - Mídia Digital)
        tentarEmprestimo(professor.getId(), midia.getId(), "OK (Empréstimo Professor)");

        // T3: Empréstimo MAL SUCEDIDO (Revista - Regra 3)
        tentarEmprestimo(aluno.getId(), revista.getId(), "FALHA (Item não emprestável)");

        // T4: Empréstimo BEM SUCEDIDO (Aluno pega 2º livro)
        tentarEmprestimo(aluno.getId(), livro2.getId(), "OK (2º Livro Aluno)");

        // T5: Empréstimo MAL SUCEDIDO (Aluno atinge limite - Regra 1)
        tentarEmprestimo(aluno.getId(), livro1.getId(), "FALHA (Limite de empréstimo atingido)");

        // T6: DEVOLUÇÃO EM DIA (Professor)
        Emprestimo emprestimoProfessor = emprestimoRepository.findByUsuarioAndDevolvidoFalse(professor).get(0);
        tentarDevolucao(emprestimoProfessor.getId(), false, "OK (Devolução em dia)");

        // T7: Empréstimo BEM SUCEDIDO (Professor pode pegar de novo)
        tentarEmprestimo(professor.getId(), livro1.getId(), "OK (Professor pega item devolvido)");

        // SIMULA ATRASO
        System.out.println("\n[TESTE] ** Atrasando o Empréstimo do Aluno em 5 dias para multa... **");
        Emprestimo emprestimoAlunoAtraso = emprestimoRepository.findByUsuarioAndDevolvidoFalse(aluno).get(0);
        // Força a data prevista de devolução para 5 dias atrás, simulando atraso.
        emprestimoAlunoAtraso.setDataPrevistaDevolucao(LocalDate.now().minusDays(5));
        emprestimoRepository.save(emprestimoAlunoAtraso);

        // T8: DEVOLUÇÃO EM ATRASO (Aluno - Regra 6 & 2)
        // Isso deve bloquear o aluno e calcular multa de 5 dias * R$2,00 = R$10,00
        tentarDevolucao(emprestimoAlunoAtraso.getId(), true, "OK (Devolução em Atraso & Bloqueio)");

        // T9: Empréstimo MAL SUCEDIDO (Aluno Bloqueado - Regra 2)
        tentarEmprestimo(aluno.getId(), livro2.getId(), "FALHA (Usuário Bloqueado)");
    }

    // Métodos utilitários (omiti corpo para concisão)
    private <T extends Usuario> Usuario criarUsuario(T usuario, String matricula, String nome) {
        usuario.setMatricula(matricula);
        usuario.setNome(nome);
        return usuarioRepository.save(usuario);
    }

    private <T extends ItemAcervo> ItemAcervo criarItem(T item, String titulo, String autor, String isbn) {
        item.setTitulo(titulo);
        item.setAutor(autor);
        item.setIsbn(isbn);
        item.setCustoSubstituicao(new BigDecimal("100.00")); // Valor para Multa de Mídia
        return itemAcervoRepository.save(item);
    }

    private void criarExemplares(ItemAcervo item, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            Exemplar exemplar = new Exemplar();
            exemplar.setItemAcervo(item);
            exemplar.setCodigoTombo(item.getIsbn() + "-" + (i + 1));
            exemplarRepository.save(exemplar);
        }
    }

    // Utilitário para envolver a chamada do Service em um try-catch para demonstração
    private void tentarEmprestimo(Long uId, Long iId, String teste) {
        try {
            emprestimoService.realizarEmprestimo(uId, iId);
            System.out.println(" ✅ TESTE: " + teste);
        } catch (RuntimeException e) {
            System.err.println(" ❌ TESTE: " + teste + " - ERRO: " + e.getMessage());
        }
    }

    // Utilitário para envolver a chamada do Service em um try-catch para demonstração
    private void tentarDevolucao(Long empId, boolean esperaMulta, String teste) {
        try {
            emprestimoService.devolver(empId);
            if (esperaMulta) {
                System.out.println(" ✅ TESTE: " + teste + " - Multa e Bloqueio Aplicados.");
            } else {
                System.out.println(" ✅ TESTE: " + teste);
            }
        } catch (RuntimeException e) {
            System.err.println(" ❌ TESTE: " + teste + " - ERRO: " + e.getMessage());
        }
    }
}