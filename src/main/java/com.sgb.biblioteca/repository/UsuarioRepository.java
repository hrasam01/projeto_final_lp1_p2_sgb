// com.sgb.biblioteca.repository.UsuarioRepository.java
package com.sgb.biblioteca.repository;

import com.sgb.biblioteca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Esta interface herda automaticamente métodos CRUD prontos: save, findById, findAll, delete, etc.
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Exemplo de um método customizado que pode ser útil no Service:
    // O Spring Data JPA cria o código SQL automaticamente baseado no nome do método!
    Usuario findByMatricula(String matricula);

    // Método para buscar usuários bloqueados (Regra de Negócio 2)
    long countByBloqueadoTrue();
}