// com.sgb.biblioteca.SgbApplication.java
package com.sgb.biblioteca;

import com.sgb.biblioteca.service.SimulacaoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SgbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SgbApplication.class, args);
    }

    /**
     * Define um Bean que será executado assim que a aplicação iniciar.
     * Injeta o SimulacaoService para rodar a demonstração.
     */
    @Bean
    public CommandLineRunner runSimulacao(SimulacaoService simulacaoService) {
        return args -> {
            System.out.println("----------------------------------------------");
            System.out.println("          SGB: INICIANDO DEMONSTRAÇÃO         ");
            System.out.println("----------------------------------------------");
            simulacaoService.executarSimulacao();
            System.out.println("----------------------------------------------");
            System.out.println("          DEMONSTRAÇÃO CONCLUÍDA              ");
            System.out.println("----------------------------------------------");
        };
    }
}