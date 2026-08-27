# Trabalho de Campo: Sistema de Biblioteca Municipal

## Introdução

O presente trabalho foi desenvolvido no âmbito da disciplina de Introdução a Algoritmos e Programação. A proposta consiste na criação de um sistema simples, baseado em consola, para apoiar a Biblioteca Municipal na gestão do inventário de livros e no controlo de empréstimos e devoluções.

O sistema foi implementado em linguagem Java e utiliza estruturas de dados em memória, principalmente arrays e uma matriz, conforme solicitado no enunciado. A solução não usa base de dados externa, porque o objectivo principal é aplicar conceitos introdutórios de algoritmos, estruturas de repetição, condições, funções/métodos e armazenamento de dados em vectores.

## Objectivo geral

Desenvolver um programa em Java, baseado em consola, capaz de gerir livros, utilizadores, empréstimos, devoluções e estatísticas de uma biblioteca municipal.

## Objectivos específicos

- Registar livros no catálogo da biblioteca.
- Permitir a consulta e pesquisa de livros por título ou autor.
- Registar utilizadores da biblioteca.
- Efectuar empréstimos apenas quando o utilizador e o livro existem.
- Diminuir a quantidade disponível quando ocorre um empréstimo.
- Registar devoluções e repor a quantidade disponível.
- Apresentar estatísticas sobre livros requisitados e livro mais emprestado.
- Aplicar arrays e matrizes como base de dados simulada em memória.

## Referencial teórico

Segundo o Manual de Algoritmo e Introdução a Programação da UnISCED, todo programa informático tem na sua base um algoritmo, isto é, uma sequência lógica de passos para resolver um problema. No mesmo manual, o algoritmo é apresentado como um procedimento que recebe dados de entrada, processa esses dados e produz uma saída.

No caso deste trabalho, as entradas são os dados inseridos pelo bibliotecário, como o ID do livro, título, autor, ano, quantidade disponível, ID do utilizador e operações de empréstimo ou devolução. O processamento acontece por meio de estruturas condicionais, ciclos de repetição e métodos. As saídas são as mensagens apresentadas na consola, como listagens, resultados de pesquisa, confirmações e estatísticas.

O manual também apresenta os vectores, ou arrays, como estruturas que armazenam dados do mesmo tipo usando índices. Por isso, foram usados arrays para guardar IDs, títulos, autores, anos, quantidades e nomes de utilizadores. Para representar o histórico dos empréstimos, foi usada uma matriz de inteiros, pois cada linha representa um empréstimo e cada coluna guarda uma informação específica: ID do utilizador, ID do livro e estado do empréstimo.

## Metodologia de pesquisa e desenvolvimento

A metodologia utilizada foi pesquisa bibliográfica e desenvolvimento incremental.

Na pesquisa bibliográfica, foi consultado o manual da disciplina para compreender os conceitos de algoritmo, entrada, processamento, saída, arrays, matrizes e fundamentos de Java.

No desenvolvimento incremental, o programa foi criado por partes. Primeiro foi definido o menu principal. Depois foram implementadas as funções de registo de livros e utilizadores. Em seguida foram adicionadas as consultas, os empréstimos, as devoluções e, por fim, as estatísticas. Esta abordagem foi escolhida porque facilita a verificação de cada funcionalidade antes de passar para a seguinte.

## Desenvolvimento

O programa principal encontra-se no ficheiro `src/BibliotecaMunicipal.java`. A classe usa constantes para definir a capacidade máxima de livros, utilizadores e empréstimos:

```java
static final int MAX_LIVROS = 100;
static final int MAX_UTILIZADORES = 100;
static final int MAX_EMPRESTIMOS = 300;
```

Os livros são guardados em arrays paralelos:

```java
static int[] livroIds = new int[MAX_LIVROS];
static String[] livroTitulos = new String[MAX_LIVROS];
static String[] livroAutores = new String[MAX_LIVROS];
static int[] livroAnos = new int[MAX_LIVROS];
static int[] livroQuantidades = new int[MAX_LIVROS];
```

Os empréstimos são guardados numa matriz:

```java
static int[][] emprestimos = new int[MAX_EMPRESTIMOS][3];
```

Cada linha da matriz representa um empréstimo. A coluna 0 guarda o ID do utilizador, a coluna 1 guarda o ID do livro e a coluna 2 guarda o estado do empréstimo, sendo 1 para activo e 0 para devolvido.

### Menu principal

Ao executar o programa, o utilizador visualiza o seguinte menu:

```text
===== BIBLIOTECA MUNICIPAL =====
1 - Registar livro
2 - Registar utilizador
3 - Listar catálogo
4 - Pesquisar livro por título ou autor
5 - Efectuar empréstimo
6 - Registar devolução
7 - Estatísticas
0 - Sair
```

### Registo de livros

Nesta opção, o bibliotecário informa o ID, título, autor, ano de publicação e quantidade disponível. O sistema verifica se já existe um livro com o mesmo ID e impede duplicação.

### Consulta e pesquisa

A consulta mostra todos os livros registados. A pesquisa permite procurar por parte do título ou por parte do nome do autor, facilitando a localização de obras no catálogo.

### Gestão de empréstimos

Para efectuar um empréstimo, o sistema pede o ID do utilizador e o ID do livro. Antes de registar, verifica se o utilizador existe, se o livro existe e se há quantidade disponível. Quando o empréstimo é confirmado, a quantidade do livro diminui em uma unidade.

### Devolução

Na devolução, o sistema procura um empréstimo activo com o mesmo ID de utilizador e ID de livro. Quando encontra, altera o estado para devolvido e aumenta novamente a quantidade disponível.

### Estatísticas

As estatísticas mostram o total de livros actualmente requisitados, o total histórico de empréstimos e o livro mais emprestado.

## Capturas de tela do aplicativo

Como o sistema é executado em consola, as capturas podem ser representadas pelos seguintes exemplos de execução.

### Tela inicial

```text
===== BIBLIOTECA MUNICIPAL =====
1 - Registar livro
2 - Registar utilizador
3 - Listar catálogo
4 - Pesquisar livro por título ou autor
5 - Efectuar empréstimo
6 - Registar devolução
7 - Estatísticas
0 - Sair
Escolha uma opção:
```

### Exemplo de catálogo

```text
ID | Título | Autor | Ano | Disponível
1 | Algoritmos e Lógica de Programação | UnISCED | 2021 | 4
2 | Java para Iniciantes | Carlos Silva | 2020 | 2
3 | Estruturas de Dados | Ana Mussagy | 2019 | 3
```

### Exemplo de empréstimo

```text
ID do utilizador: 1
ID do livro: 2
Empréstimo registado para Valentim.
```

### Exemplo de estatísticas

```text
Total de livros requisitados actualmente: 1
Total histórico de empréstimos: 1
Livro mais emprestado: Java para Iniciantes (1 empréstimo(s))
```

## Tratamento de erros

O sistema possui verificações para evitar erros comuns, como:

- Opção inválida no menu.
- Entrada numérica inválida.
- Registo de livro com ID repetido.
- Registo de utilizador com ID repetido.
- Empréstimo de livro inexistente.
- Empréstimo para utilizador inexistente.
- Empréstimo sem quantidade disponível.
- Devolução sem empréstimo activo.

## Link do repositório GitHub


```text
https://github.com/gluttm/sistema-biblioteca-municipal
```

## Conclusão

Com este trabalho foi possível desenvolver um sistema simples para a gestão de uma biblioteca municipal, aplicando conceitos estudados na disciplina de Introdução a Algoritmos e Programação. O programa usa entrada, processamento e saída de dados, estruturas condicionais, ciclos de repetição, métodos, arrays e matriz.

A solução atende às principais funcionalidades solicitadas: registo de livros, consulta do catálogo, pesquisa, gestão de utilizadores, empréstimos, devoluções e estatísticas. Apesar de ser um sistema simples e em memória, ele demonstra como os algoritmos podem resolver problemas reais de organização e controlo de informação.

## Referências bibliográficas

UNISCED. (2021). Manual de Algoritmo e Introdução a Programação. Beira: Universidade Aberta - Instituto Superior de Ciências e Educação a Distância.

Cormen, T. H., Leiserson, C. E., Rivest, R. L., & Stein, C. (2012). Algoritmos: Teoria e Prática. Rio de Janeiro: Elsevier.

Oracle. (s.d.). Java Documentation. Disponível em: https://docs.oracle.com/javase/
