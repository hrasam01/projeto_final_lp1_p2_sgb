// com.sgb.biblioteca.model.Emprestimo.java
package com.sgb.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import lombok.Data; // Para getters, setters, toString, equals, hashCode
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- Relacionamentos (Chaves Estrangeiras) ---

    /* * USUARIO (ManyToOne): Um Usuário pode ter muitos Empréstimos.
     * O status de bloqueio do usuário será verificado no Service antes de criar um Empréstimo.
     */
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    /* * EXEMPLAR (OneToOne): Um Exemplar só pode estar em um Empréstimo ativo por vez.
     * O status 'disponivel' do Exemplar será alterado no Service.
     */
    @OneToOne
    @JoinColumn(name = "exemplar_id", nullable = false)
    private Exemplar exemplar;

    // --- Datas (Base para Multa) ---

    private LocalDate dataEmprestimo = LocalDate.now(); // Data em que o empréstimo foi feito
    private LocalDate dataPrevistaDevolucao;          // Data calculada com base no tipo de Usuario
    private LocalDate dataDevolucaoReal;              // Data em que o item foi de fato devolvido

    // --- Status ---

    private boolean devolvido = false;

    // Construtores, Getters e Setters (omiti para simplificar, mas são obrigatórios)

    // --- MÉTODOS DE REGRA DE NEGÓCIO ESSENCIAIS ---

    /**
     * Calcula a Data Prevista de Devolução com base nas regras de Usuário.
     * Deve ser chamado no Service, ANTES de persistir.
     */
    public void calcularDataPrevistaDevolucao() {
        // Usa o método polimórfico da classe Usuario (Aluno, Professor, etc.)
        int prazo = this.usuario.getPrazoDevolucaoEmDias();
        this.dataPrevistaDevolucao = this.dataEmprestimo.plusDays(prazo);
    }

    /**
     * Calcula o valor da multa total, aplicando as regras de ItemAcervo.
     * Regras 5 e 6: Cálculo de atraso e valor por dia.
     * @return O valor total da multa.
     */
    public BigDecimal calcularMulta() {
        if (!isEmAtraso()) {
            return BigDecimal.ZERO;
        }

        // Calcula os dias de atraso
        long diasAtraso = ChronoUnit.DAYS.between(this.dataPrevistaDevolucao, this.dataDevolucaoReal);

        // Obtém o valor diário de multa (Polimorfismo: Livro/Midia/etc)
        BigDecimal multaDiaria = this.exemplar.getItemAcervo().calcularMultaDiaria();

        // Multiplica e retorna
        return multaDiaria.multiply(new BigDecimal(diasAtraso));
    }

    /**
     * Verifica se o empréstimo está em atraso.
     */
    public boolean isEmAtraso() {
        // Se a devolução não foi feita, usa a data atual para verificar o atraso
        LocalDate dataReferencia = (this.dataDevolucaoReal != null) ? this.dataDevolucaoReal : LocalDate.now();

        return dataReferencia.isAfter(this.dataPrevistaDevolucao);
    }

    // ... e os demais getters/setters
}