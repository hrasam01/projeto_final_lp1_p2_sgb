// com.sgb.biblioteca.repository.EmprestimoRepository.java
package com.sgb.biblioteca.repository;

import com.sgb.biblioteca.model.Emprestimo;
import com.sgb.biblioteca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SimulacaoService {

    // Adicione a injeção aqui:
    @Autowired
    private EmprestimoRepository emprestimoRepository; // Resolvendo o 'cannot find symbol variable emprestimoRepository'

    // ... o restante do código
}

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    // Método para buscar todos os empréstimos ativos (não devolvidos) de um usuário
    List<Emprestimo> findByUsuarioAndDevolvidoFalse(Usuario usuario);

    // Método para contar quantos empréstimos ativos um usuário possui (Regra 1: Limite)
    long countByUsuarioAndDevolvidoFalse(Usuario usuario);

    // Método para listar todos os empréstimos que estão em atraso (para Relatórios e Multas)
    List<Emprestimo> findByDevolvidoFalseAndDataPrevistaDevolucaoBefore(java.time.LocalDate dataAtual);
}