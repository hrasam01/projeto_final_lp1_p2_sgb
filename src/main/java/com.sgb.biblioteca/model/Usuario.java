// com.sgb.biblioteca.model.Usuario.java
package com.sgb.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data; // Para getters, setters, toString, equals, hashCode
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Estratégia recomendada para herança simples em JPA
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String matricula;
    private boolean bloqueado = false; // Encapsulamento de status de bloqueio

    // Construtores, Getters e Setters (omiti para simplificar, mas são obrigatórios)

    /**
     * MÉTODO ABSTRATO PARA POLIMORFISMO
     * Define o limite máximo de livros que um usuário pode emprestar.
     */
    public abstract int getLimiteEmprestimo();

    /**
     * MÉTODO ABSTRATO PARA POLIMORFISMO
     * Define o prazo (em dias) para devolução.
     */
    public abstract int getPrazoDevolucaoEmDias();

    // Outros getters e setters (ex: getId(), getNome(), isBloqueado(), setBloqueado())
}