// com.sgb.biblioteca.model.Aluno.java
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
@DiscriminatorValue("ALUNO")
public class Aluno extends Usuario {

    // Regra 1: Aluno pode pegar 3 livros por 7 dias.
    @Override
    public int getLimiteEmprestimo() {
        return 3;
    }

    @Override
    public int getPrazoDevolucaoEmDias() {
        return 7;
    }
}