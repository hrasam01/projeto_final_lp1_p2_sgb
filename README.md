# 📚 SGB – Sistema de Gerenciamento de Biblioteca
Projeto acadêmico para entrega final do segundo período na disciplina de linguagem de Progrmação 1 (LP1) da profesora Fernanda Nobrega

## 📖 Sobre o Projeto

O SGB – Sistema de Gerenciamento de Biblioteca é uma aplicação desenvolvida em Java Puro, mas que segue a mesma estrutura de camadas do Spring Boot com o objetivo de simular o funcionamento de uma biblioteca real, incluindo cadastro de usuários, gerenciamento do acervo, exemplares e empréstimos.

Este projeto foi criado com fins didáticos, utilizando conceitos fundamentais de:

* Programação orientada a objetos (POO)

* Herança, polimorfismo e abstração

* Arquitetura de camadas (Controller, Service, Repository, Model)

* Persistência com Spring Data JPA

* Banco de dados H2 em memória

* Regras de negócio típicas de bibliotecas (limite, prazos, atrasos, multas)

## 🛠️ Tecnologias Utilizadas

| Tecnologia        | Versão        |
| ----------------- | ------------- |
| Java              | 21            |
| Spring Boot       | 3.x           |
| Spring Data JPA   | 3.x           |
| H2 Database       | 2.x           |
| Maven             | Última versão |
| Lombok (opcional) | -             |

## 📦 Arquitetura do Projeto

A arquitetura segue o padrão MVC + camadas de negócio:

src/main/java/com.sgb.biblioteca
│
├── controller/     → Controladores REST
├── service/        → Regras de negócio
├── repository/     → Interfaces JPA
├── model/          → Entidades e heranças
└── SgbApplication  → Classe principal do Spring Boot

## 🔍 Estrutura das Entidades (Model)

### 👤 Usuários (Herança)

* Usuario (abstrata)

* Aluno

* Professor

* Funcionario

Cada um com regras e características próprias.

### 📚 Itens do Acervo (Herança)


* ItemAcervo (abstrata)

* Livro

* Revista

* MidiaDigital


### 🏷️ Outras Entidades

* Exemplar → Representa uma cópia física do item

* Emprestimo → Registra o empréstimo entre Usuário + Exemplar

* (Opcional) Reserva

* (Opcional) Multa

## 🤖 Camada de Serviço (Service)

"EmprestimoService"

Contém toda a lógica da biblioteca:

* verificar disponibilidade de exemplares

* checar se o usuário está apto

* criar empréstimos

* calcular atraso

* calcular multas

* realizar devolução


"SimulacaoService"

Cria dados iniciais e executa cenários automáticos para testar regras:

* cria usuários fictícios

* adiciona livros e exemplares

* executa empréstimos

* verifica retorno e atrasos

Esse service é ideal para validação de regras durante o desenvolvimento.

## 🗃️ Repositórios (Repository)

Para salvar os dados estamos usando arrays ao invés de usar banco de dados.

## ▶️ Como Executar o Projeto

1. Pré-requisitos

* Java 21 instalado

* IDE recomendada: IntelliJ / Eclipse / VS Code

2. Clonar o repositório
```
git clone https://github.com/hrasam01/projeto_final_lp1_p2_sgb.git
cd scr/
```

3. Rodar a aplicação
```
Main.java
```

Quando a aplicação inicia, o "SimulacaoService" roda automaticamente e imprime resultados no console.

## 📊 Fluxo de Funcionamento

1. Sistema inicia

2. Dados de exemplo são criados

3. Usuários e itens são carregados

4. Regras de empréstimo são testadas

5. O console exibe todo o fluxo de ações

Isso permite validar toda a lógica antes mesmo de criar telas ou endpoints. 

## 🧪 Testes

É possível criar testes unitários para:

* regras de empréstimo

* cálculo de atraso

* operações de repositório

A estrutura já está pronta para receber testes JUnit.

## 📌 Backlog do Projeto (Resumo)

### ✔️ Implementado

* Estrutura base do projeto

* Entidades e heranças

* Repositórios JPA

* Serviço de empréstimo

* Simulação automática

* Configuração do H2

* Controllers base

### 📅 Em Desenvolvimento

* Endpoints REST completos

* Reserva de livros

* Multas ao final de devolução

* Tela frontend ou mobile


## 📄 Licença

Projeto de caráter **educacional**, sem fins comerciais.

## 👨‍💻 Criadores

- Hrasam Hussem
- Joalis Batista
