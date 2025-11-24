// com.sgb.biblioteca.model.Professor.java
package com.sgb.biblioteca.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data; // Para getters, setters, toString, equals, hashCode
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("PROFESSOR")
public class Professor extends Usuario {

    // Regra 1: Professor pode pegar 5 livros por 15 dias.
    @Override
    public int getLimiteEmprestimo() {
        return 5;
    }

    @Override
    public int getPrazoDevolucaoEmDias() {
        return 15;
    }
}