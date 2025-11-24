// com.sgb.biblioteca.model.Funcionario.java
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
@DiscriminatorValue("FUNCIONARIO")
public class Funcionario extends Usuario {

    // Regra 1: Funcionário pode ter um limite de empréstimo intermediário.
    @Override
    public int getLimiteEmprestimo() {
        return 4; // Ex: 4 itens
    }

    // Regra 1: Prazo de devolução maior que o aluno, mas menor que o professor.
    @Override
    public int getPrazoDevolucaoEmDias() {
        return 10; // Ex: 10 dias
    }
}