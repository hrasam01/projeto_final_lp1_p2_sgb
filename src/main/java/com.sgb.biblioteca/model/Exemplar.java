// com.sgb.biblioteca.model.Exemplar.java
package com.sgb.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data; // Para getters, setters, toString, equals, hashCode
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Exemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // O ID de tombo (registro físico) é útil para rastreamento no mundo real
    private String codigoTombo;

    // Status que indica se o exemplar pode ser emprestado (Regra 4)
    private boolean disponivel = true;

    /* * RELACIONAMENTO: Muitos Exemplares pertencem a um único ItemAcervo.
     * Ex: 5 Exemplares (cópias) do Livro "Dom Casmurro".
     */
    @ManyToOne
    @JoinColumn(name = "item_acervo_id", nullable = false)
    private ItemAcervo itemAcervo;

    // Construtores, Getters e Setters (omiti para simplificar, mas são obrigatórios)

    // Exemplo de getters e setters essenciais:

    public Long getId() {
        return id;
    }

    public ItemAcervo getItemAcervo() {
        return itemAcervo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    // ... e os demais getters/setters
}