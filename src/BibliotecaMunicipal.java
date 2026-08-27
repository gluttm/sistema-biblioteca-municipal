import java.util.Scanner;

public class BibliotecaMunicipal {
    static final int MAX_LIVROS = 100;
    static final int MAX_UTILIZADORES = 100;
    static final int MAX_EMPRESTIMOS = 300;

    static int[] livroIds = new int[MAX_LIVROS];
    static String[] livroTitulos = new String[MAX_LIVROS];
    static String[] livroAutores = new String[MAX_LIVROS];
    static int[] livroAnos = new int[MAX_LIVROS];
    static int[] livroQuantidades = new int[MAX_LIVROS];
    static int[] livroTotalEmprestimos = new int[MAX_LIVROS];
    static int totalLivros = 0;

    static int[] utilizadorIds = new int[MAX_UTILIZADORES];
    static String[] utilizadorNomes = new String[MAX_UTILIZADORES];
    static int totalUtilizadores = 0;

    // Matriz de emprestimos: coluna 0 = id utilizador, 1 = id livro, 2 = estado (1 activo, 0 devolvido)
    static int[][] emprestimos = new int[MAX_EMPRESTIMOS][3];
    static int totalEmprestimos = 0;

    static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        carregarDadosIniciais();

        int opcao;
        do {
            mostrarMenu();
            opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1:
                    registarLivro();
                    break;
                case 2:
                    registarUtilizador();
                    break;
                case 3:
                    listarLivros();
                    break;
                case 4:
                    pesquisarLivro();
                    break;
                case 5:
                    efectuarEmprestimo();
                    break;
                case 6:
                    registarDevolucao();
                    break;
                case 7:
                    mostrarEstatisticas();
                    break;
                case 0:
                    System.out.println("Sistema encerrado. Obrigado!");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        } while (opcao != 0);

        entrada.close();
    }

    static void mostrarMenu() {
        System.out.println();
        System.out.println("===== BIBLIOTECA MUNICIPAL =====");
        System.out.println("1 - Registar livro");
        System.out.println("2 - Registar utilizador");
        System.out.println("3 - Listar catalogo");
        System.out.println("4 - Pesquisar livro por titulo ou autor");
        System.out.println("5 - Efectuar emprestimo");
        System.out.println("6 - Registar devolucao");
        System.out.println("7 - Estatisticas");
        System.out.println("0 - Sair");
    }

    static void carregarDadosIniciais() {
        adicionarLivro(1, "Algoritmos e Logica de Programacao", "UnISCED", 2021, 4);
        adicionarLivro(2, "Java para Iniciantes", "Carlos Silva", 2020, 2);
        adicionarLivro(3, "Estruturas de Dados", "Ana Mussagy", 2019, 3);

        adicionarUtilizador(1, "Valentim");
        adicionarUtilizador(2, "Maria");
    }

    static void registarLivro() {
        if (totalLivros >= MAX_LIVROS) {
            System.out.println("Nao e possivel registar mais livros.");
            return;
        }

        int id = lerInteiro("ID do livro: ");
        if (procurarLivroPorId(id) != -1) {
            System.out.println("Ja existe um livro com este ID.");
            return;
        }

        System.out.print("Titulo: ");
        String titulo = entrada.nextLine();
        System.out.print("Autor: ");
        String autor = entrada.nextLine();
        int ano = lerInteiro("Ano de publicacao: ");
        int quantidade = lerInteiro("Quantidade disponivel: ");

        if (titulo.isBlank() || autor.isBlank() || ano <= 0 || quantidade < 0) {
            System.out.println("Dados invalidos. O livro nao foi registado.");
            return;
        }

        adicionarLivro(id, titulo, autor, ano, quantidade);
        System.out.println("Livro registado com sucesso.");
    }

    static void registarUtilizador() {
        if (totalUtilizadores >= MAX_UTILIZADORES) {
            System.out.println("Nao e possivel registar mais utilizadores.");
            return;
        }

        int id = lerInteiro("ID do utilizador: ");
        if (procurarUtilizadorPorId(id) != -1) {
            System.out.println("Ja existe um utilizador com este ID.");
            return;
        }

        System.out.print("Nome do utilizador: ");
        String nome = entrada.nextLine();

        if (nome.isBlank()) {
            System.out.println("Nome invalido.");
            return;
        }

        adicionarUtilizador(id, nome);
        System.out.println("Utilizador registado com sucesso.");
    }

    static void listarLivros() {
        if (totalLivros == 0) {
            System.out.println("Nao existem livros registados.");
            return;
        }

        System.out.println();
        System.out.println("ID | Titulo | Autor | Ano | Disponivel");
        for (int i = 0; i < totalLivros; i++) {
            System.out.println(livroIds[i] + " | " + livroTitulos[i] + " | " + livroAutores[i]
                    + " | " + livroAnos[i] + " | " + livroQuantidades[i]);
        }
    }

    static void pesquisarLivro() {
        System.out.print("Digite parte do titulo ou autor: ");
        String termo = entrada.nextLine().toLowerCase();
        boolean encontrou = false;

        for (int i = 0; i < totalLivros; i++) {
            if (livroTitulos[i].toLowerCase().contains(termo) || livroAutores[i].toLowerCase().contains(termo)) {
                System.out.println(livroIds[i] + " | " + livroTitulos[i] + " | " + livroAutores[i]
                        + " | " + livroAnos[i] + " | Disponivel: " + livroQuantidades[i]);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum livro encontrado.");
        }
    }

    static void efectuarEmprestimo() {
        if (totalEmprestimos >= MAX_EMPRESTIMOS) {
            System.out.println("Nao e possivel registar mais emprestimos.");
            return;
        }

        int idUtilizador = lerInteiro("ID do utilizador: ");
        int posUtilizador = procurarUtilizadorPorId(idUtilizador);
        if (posUtilizador == -1) {
            System.out.println("Utilizador nao encontrado.");
            return;
        }

        int idLivro = lerInteiro("ID do livro: ");
        int posLivro = procurarLivroPorId(idLivro);
        if (posLivro == -1) {
            System.out.println("Livro nao encontrado.");
            return;
        }

        if (livroQuantidades[posLivro] <= 0) {
            System.out.println("Nao ha exemplares disponiveis deste livro.");
            return;
        }

        emprestimos[totalEmprestimos][0] = idUtilizador;
        emprestimos[totalEmprestimos][1] = idLivro;
        emprestimos[totalEmprestimos][2] = 1;
        totalEmprestimos++;

        livroQuantidades[posLivro]--;
        livroTotalEmprestimos[posLivro]++;

        System.out.println("Emprestimo registado para " + utilizadorNomes[posUtilizador] + ".");
    }

    static void registarDevolucao() {
        int idUtilizador = lerInteiro("ID do utilizador: ");
        int idLivro = lerInteiro("ID do livro: ");

        for (int i = 0; i < totalEmprestimos; i++) {
            if (emprestimos[i][0] == idUtilizador && emprestimos[i][1] == idLivro && emprestimos[i][2] == 1) {
                emprestimos[i][2] = 0;
                int posLivro = procurarLivroPorId(idLivro);
                if (posLivro != -1) {
                    livroQuantidades[posLivro]++;
                }
                System.out.println("Devolucao registada com sucesso.");
                return;
            }
        }

        System.out.println("Nao foi encontrado emprestimo activo para estes dados.");
    }

    static void mostrarEstatisticas() {
        int requisitadosActualmente = 0;
        for (int i = 0; i < totalEmprestimos; i++) {
            if (emprestimos[i][2] == 1) {
                requisitadosActualmente++;
            }
        }

        System.out.println("Total de livros requisitados actualmente: " + requisitadosActualmente);
        System.out.println("Total historico de emprestimos: " + totalEmprestimos);

        int posMaisEmprestado = -1;
        int maior = 0;
        for (int i = 0; i < totalLivros; i++) {
            if (livroTotalEmprestimos[i] > maior) {
                maior = livroTotalEmprestimos[i];
                posMaisEmprestado = i;
            }
        }

        if (posMaisEmprestado == -1) {
            System.out.println("Ainda nao houve emprestimos.");
        } else {
            System.out.println("Livro mais emprestado: " + livroTitulos[posMaisEmprestado]
                    + " (" + maior + " emprestimo(s))");
        }
    }

    static void adicionarLivro(int id, String titulo, String autor, int ano, int quantidade) {
        livroIds[totalLivros] = id;
        livroTitulos[totalLivros] = titulo;
        livroAutores[totalLivros] = autor;
        livroAnos[totalLivros] = ano;
        livroQuantidades[totalLivros] = quantidade;
        livroTotalEmprestimos[totalLivros] = 0;
        totalLivros++;
    }

    static void adicionarUtilizador(int id, String nome) {
        utilizadorIds[totalUtilizadores] = id;
        utilizadorNomes[totalUtilizadores] = nome;
        totalUtilizadores++;
    }

    static int procurarLivroPorId(int id) {
        for (int i = 0; i < totalLivros; i++) {
            if (livroIds[i] == id) {
                return i;
            }
        }
        return -1;
    }

    static int procurarUtilizadorPorId(int id) {
        for (int i = 0; i < totalUtilizadores; i++) {
            if (utilizadorIds[i] == id) {
                return i;
            }
        }
        return -1;
    }

    static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = entrada.nextLine();
            try {
                return Integer.parseInt(texto.replace("\uFEFF", "").trim());
            } catch (NumberFormatException erro) {
                System.out.println("Valor invalido. Digite um numero inteiro.");
            }
        }
    }
}
