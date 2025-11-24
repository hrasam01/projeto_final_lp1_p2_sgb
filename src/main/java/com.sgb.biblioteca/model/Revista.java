// com.sgb.biblioteca.model.Revista.java
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
@DiscriminatorValue("REVISTA")
public class Revista extends ItemAcervo {

    // Regra 6: Embora não seja emprestável, precisa implementar a multa por ser subclasse.
    @Override
    public BigDecimal calcularMultaDiaria() {
        return new BigDecimal("2.00");
    }

    // Regra 3: Revistas NÃO podem ser emprestadas (apenas consulta local).
    @Override
    public boolean isEmprestavel() {
        return false;
    }
}