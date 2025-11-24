// com.sgb.biblioteca.model.ItemAcervo.java
package com.sgb.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import java.math.BigDecimal;
import lombok.Data; // Para getters, setters, toString, equals, hashCode
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class ItemAcervo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String isbn;
    private BigDecimal custoSubstituicao; // Usado como teto para a multa da Mídia Digital (Regra 6)

    // Construtores, Getters e Setters (omiti para simplificar, mas são obrigatórios)

    /**
     * MÉTODO ABSTRATO PARA POLIMORFISMO
     * Retorna o valor da multa por dia de atraso.
     */
    public abstract BigDecimal calcularMultaDiaria();

    /**
     * MÉTODO ABSTRATO PARA POLIMORFISMO
     * Define se o item é elegível para empréstimo (Regra 3: Revistas não podem).
     */
    public abstract boolean isEmprestavel();

    // Outros getters e setters (ex: getTitulo(), getCustoSubstituicao())
}