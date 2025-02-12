package domain.test;

import domain.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Biblioteca biblioteca = new Biblioteca();
        Usuario usuario1 = new Usuario("Vinicius", "vinicius@gmail.com", LocalDate.of(2003, Month.JULY, 9));
        biblioteca.AdicionarUsuario(usuario1);
        Usuario usuario2 = new Usuario("Rodrigo", "rodrigo@gmail.com", LocalDate.of(2001, Month.JULY, 26));
        biblioteca.AdicionarUsuario(usuario2);
        Usuario usuario3 = new Usuario("Rosangela", "rosangela@gmail.com", LocalDate.of(2000, Month.JULY, 20));
        biblioteca.AdicionarUsuario(usuario3);
        Autor vinicius = new Autor("Vinicius", LocalDate.of(1999, Month.JULY, 3));
        Livro livro1 = new Livro("Aprendendo a aprender", vinicius);
        biblioteca.AdicionarLivro(livro1);
        Autor rose = new Autor("Rose", LocalDate.of(1981, Month.JANUARY, 9));
        Livro livro2 = new Livro("O magico", rose);
        biblioteca.AdicionarLivro(livro2);
        Autor steven = new Autor("Steven", LocalDate.of(1978, Month.FEBRUARY, 10));
        Livro livro3 = new Livro("Clean Code", steven);
        biblioteca.AdicionarLivro(livro3);
        Autor amethist = new Autor("Amethist", LocalDate.of(1995, Month.NOVEMBER, 21));
        Livro livro4 = new Livro("Orientação á Objetos", amethist);
        biblioteca.AdicionarLivro(livro4);
        Autor billgates = new Autor("Bill Gates", LocalDate.of(1995, Month.NOVEMBER, 21));
        Livro livro5 = new Livro("Microsoft History", billgates);
        biblioteca.AdicionarLivro(livro5);
        boolean executando = true;
        int opcao;
        while(executando){
            Menu();
            try{
                opcao = scanner.nextInt();
                switch (opcao){
                    case 1:
                        biblioteca.ListarLivrosDisponiveis();
                        break;
                    case 2:
                        System.out.println("Qual é seu nome de Usuario?");
                        scanner.nextLine();
                        String nomeUsuario = scanner.nextLine();
                        for (Usuario usuario : biblioteca.getUsuarios()){
                            if (nomeUsuario.equalsIgnoreCase(usuario.getNome())){
                                biblioteca.PegarLivroEmprestado(scanner, usuario);
                            }
                        }
                        System.out.println("----------");
                        break;
                    case 3:
                        for (Usuario usuario : biblioteca.getUsuarios()){
                            System.out.println("Nome: "+usuario.getNome());
                            System.out.println("Email: "+usuario.getEmail());
                            System.out.println("Data de nascimento: "+usuario.getDataNascimento().format(formatter2));
                            System.out.println("----------");
                        }
                        break;
                    case 4:
                        biblioteca.ConsultarHistoricoDeEmprestimosDoUsuario(scanner);
                        break;
                    case 5:
                        Livro livroCriado = CriarLivro(scanner);
                        biblioteca.AdicionarLivro(livroCriado);
                        System.out.println("Livro criado com sucesso!");
                        break;
                    case 6:
                        biblioteca.BuscarLivroPorTitulo(scanner);
                        break;
                    case 7:
                        System.out.println("Saindo do programa...");
                        executando = false;
                        break;
                    default:
                        System.out.println("Opção inválida, tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Você digitou algo inválido, tente novamente com um número!");
                scanner.nextLine();
            } catch (Exception e){
                System.out.println("Ocorreu um erro inesperado. "+e.getMessage());
            }
        }
    }

    public static void Menu(){
        System.out.println("----------Sistema de Livraria----------");
        System.out.println("O que deseja fazer?");
        System.out.println("1 - Listar livros disponiveis para empréstimo");
        System.out.println("2 - Realizar empréstimo");
        System.out.println("3 - Listar Clientes Cadastrados");
        System.out.println("4 - Consultar histórico de empréstimos de um usuario");
        System.out.println("5 - Cadastrar um livro na biblioteca");
        System.out.println("6 - Buscar Livro por titulo");
        System.out.println("7 - Sair do programa");
    }

    public static Livro CriarLivro(Scanner scanner){
        System.out.println("Insira o nome do autor do livro");
        System.out.print("Nome do autor: ");
        String nomeAutor = scanner.next();
        System.out.println("Data de nascimento");
        System.out.print("Dia: ");
        int dia = scanner.nextInt();
        System.out.print("Mês: ");
        String mes = scanner.next();
        System.out.print("Ano: ");
        int ano = scanner.nextInt();
        Autor novoAutor = new Autor(nomeAutor, LocalDate.of(ano, CriarMesCorrespondente(mes), dia));
        System.out.println("Agora escreva o titulo do livro que você quer criar");
        scanner.nextLine();
        String nomeLivro = scanner.nextLine();
        Livro novoLivro = new Livro(nomeLivro, novoAutor);
        return novoLivro;
    }

    public static Month CriarMesCorrespondente(String mes){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM", new Locale("pt", "BR"));
        int numeroMesCorrespondente = Month.from(formatter.parse(mes)).getValue();
        Month mesCorrespondente = Month.of(numeroMesCorrespondente);
        return  mesCorrespondente;
    }
}