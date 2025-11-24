// com.sgb.biblioteca.service.EmprestimoService.java
package com.sgb.biblioteca.service;

import com.sgb.biblioteca.model.*;
import com.sgb.biblioteca.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.math.BigDecimal;

@Service
public class EmprestimoService {

    // Injeção de dependências (Autoconection com o banco de dados via JPA)
    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ExemplarRepository exemplarRepository;

    // Construtor para injeção (melhor prática do Spring)
    public EmprestimoService(EmprestimoRepository emprestimoRepository,
                             UsuarioRepository usuarioRepository,
                             ExemplarRepository exemplarRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioRepository = usuarioRepository;
        this.exemplarRepository = exemplarRepository;
    }

    // A partir daqui, criaremos os métodos que implementam as Regras de Negócio.

    // ... (próximos métodos)
    // Dentro da classe EmprestimoService...

    /**
     * Regra de Negócio 5: Realiza a validação e criação do empréstimo.
     */
    public Emprestimo realizarEmprestimo(Long usuarioId, Long itemAcervoId) {
        // 1. Busca e valida Usuário
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        // Regra 2: Checar se o usuário está BLOQUEADO
        if (usuario.isBloqueado()) {
            throw new RuntimeException("Usuário bloqueado! Multas pendentes ou itens em atraso.");
        }

        // 2. Checa limite de empréstimos ativos (Regra 1)
        long emprestimosAtivos = emprestimoRepository.countByUsuarioAndDevolvidoFalse(usuario);
        if (emprestimosAtivos >= usuario.getLimiteEmprestimo()) {
            // Usa o método polimórfico getLimiteEmprestimo()
            throw new RuntimeException("Limite de empréstimo atingido para a categoria " +
                    usuario.getClass().getSimpleName() + ".");
        }

        // 3. Busca o ItemAcervo (para validação de tipo e busca de exemplar)
        ItemAcervo itemAcervo = exemplarRepository.findById(itemAcervoId)
                .orElseThrow(() -> new RuntimeException("Item de Acervo não encontrado."));

        // Regra 3: Checar se o item é emprestável (Polimorfismo: Revistas não são!)
        if (!itemAcervo.isEmprestavel()) {
            throw new RuntimeException(itemAcervo.getTitulo() + " é apenas para consulta local.");
        }

        // 4. Busca um Exemplar disponível (Regra 4 - Estoque)
        Exemplar exemplar = exemplarRepository.findFirstByItemAcervoAndDisponivelTrue(itemAcervo);
        if (exemplar == null) {
            throw new RuntimeException("Nenhum exemplar disponível para " + itemAcervo.getTitulo());
        }

        // --- 5. CRIAÇÃO DA TRANSAÇÃO ---
        Emprestimo novoEmprestimo = new Emprestimo();
        novoEmprestimo.setUsuario(usuario);
        novoEmprestimo.setExemplar(exemplar);

        // Usa o método da entidade que aplica o prazo polimórfico (Regra 1)
        novoEmprestimo.calcularDataPrevistaDevolucao();

        // Regra 4: Atualiza o status do Exemplar (baixa no estoque)
        exemplar.setDisponivel(false);
        exemplarRepository.save(exemplar);

        // Salva o empréstimo
        return emprestimoRepository.save(novoEmprestimo);
    }

    // Dentro da classe EmprestimoService...

    /**
     * Regra de Negócio 6: Realiza a devolução, calcula a multa e atualiza o status.
     */
    public Emprestimo devolver(Long emprestimoId) {
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado."));

        if (emprestimo.isDevolvido()) {
            throw new RuntimeException("Este item já foi devolvido.");
        }

        // 1. Marca datas e status
        emprestimo.setDataDevolucaoReal(LocalDate.now());
        emprestimo.setDevolvido(true);

        // 2. Libera o Exemplar (retorno ao estoque)
        Exemplar exemplar = emprestimo.getExemplar();
        exemplar.setDisponivel(true);
        exemplarRepository.save(exemplar);

        // 3. Cálculo de Multa (Polimorfismo)
        if (emprestimo.isEmAtraso()) {
            // Multa calculada dentro da entidade Emprestimo, usando o Polimorfismo do ItemAcervo
            BigDecimal multaDevida = emprestimo.calcularMulta();

            // **IMPLEMENTAÇÃO PENDENTE:** Aqui o sistema deveria gerar uma entidade 'Multa'
            // e, se for o caso, BLOQUEAR o usuário (Regra 2)

            // Simulação de Bloqueio (Regra 2):
            Usuario usuario = emprestimo.getUsuario();
            usuario.setBloqueado(true); // Bloqueia o usuário por atraso
            usuarioRepository.save(usuario);

            System.out.println("\n*** ALERTA DE MULTA E BLOQUEIO ***");
            System.out.println("Usuário " + usuario.getNome() + " bloqueado! Multa total: R$ " + multaDevida);
            System.out.println("*********************************\n");
            // Em um projeto real, salvaríamos a multa no banco.
        }

        return emprestimoRepository.save(emprestimo);
    }
}