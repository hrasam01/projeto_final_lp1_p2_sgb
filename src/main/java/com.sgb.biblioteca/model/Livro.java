// com.sgb.biblioteca.model.Livro.java
package com.sgb.biblioteca.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.math.BigDecimal;
import lombok.Data; // Para getters, setters, toString, equals, hashCode
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("LIVRO")
public class Livro extends ItemAcervo {

    // Regra 6: Multa de R$2,00 por dia.
    @Override
    public BigDecimal calcularMultaDiaria() {
        return new BigDecimal("2.00");
    }

    // Regra 3: Livros são emprestáveis.
    @Override
    public boolean isEmprestavel() {
        return true;
    }
}