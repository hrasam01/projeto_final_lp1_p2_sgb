// com.sgb.biblioteca.repository.ItemAcervoRepository.java
package com.sgb.biblioteca.repository;

import com.sgb.biblioteca.model.ItemAcervo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemAcervoRepository extends JpaRepository<ItemAcervo, Long> {

    // Método customizado para buscar um item pelo ISBN (código de catálogo)
    ItemAcervo findByIsbn(String isbn);

}