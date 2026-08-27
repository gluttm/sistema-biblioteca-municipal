# Sistema de Biblioteca Municipal

Trabalho de Campo da disciplina de Introdução a Algoritmos e Programação.

## Descrição

Este projecto implementa um sistema simples de consola, em linguagem Java, para gerir livros, utilizadores, empréstimos, devoluções e estatísticas de uma biblioteca municipal.

O programa usa uma base de dados simulada em memória, com vectores/arrays para guardar livros e utilizadores, e uma matriz para guardar o histórico dos empréstimos.

## Funcionalidades

- Registar livros com ID, título, autor, ano e quantidade disponível.
- Registar utilizadores.
- Listar o catálogo de livros.
- Pesquisar livros por título ou autor.
- Efectuar empréstimos a utilizadores registados.
- Registar devoluções.
- Mostrar estatísticas do total de livros requisitados e do livro mais emprestado.

## Requisitos

- Java JDK 11 ou superior.
- Terminal ou consola.

## Como executar

Compile o programa:

```bash
javac src/BibliotecaMunicipal.java
```

Execute:

```bash
java -cp src BibliotecaMunicipal
```

## Estrutura do projecto

```text
.
+-- src/
|   +-- BibliotecaMunicipal.java
+-- README.md
+-- DOCUMENTACAO_TECNICA.md
```

