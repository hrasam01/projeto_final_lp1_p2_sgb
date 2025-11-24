// com.sgb.biblioteca.repository.ExemplarRepository.java
package com.sgb.biblioteca.repository;

import com.sgb.biblioteca.model.Exemplar;
import com.sgb.biblioteca.model.ItemAcervo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ExemplarRepository extends JpaRepository<Exemplar, Long> {

    // Método para buscar todos os exemplares de um determinado item (Livro, Revista, etc.)
    List<Exemplar> findByItemAcervo(ItemAcervo itemAcervo);

    // Método crucial para a Regra 4 (Disponibilidade): Busca um exemplar disponível de um item específico
    Exemplar findFirstByItemAcervoAndDisponivelTrue(ItemAcervo itemAcervo);

}