// com.sgb.biblioteca.model.MidiaDigital.java
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
@DiscriminatorValue("MIDIA_DIGITAL")
public class MidiaDigital extends ItemAcervo {

    // Regra 6: Taxa de licença de R$5,00 por dia.
    @Override
    public BigDecimal calcularMultaDiaria() {
        return new BigDecimal("5.00");
    }

    // Regra 3: Mídias são emprestáveis.
    @Override
    public boolean isEmprestavel() {
        return true;
    }
}