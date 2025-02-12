package domain;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Biblioteca {
    private List<Livro> livros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void AdicionarLivro(Livro livro){
        livros.add(livro);
    }

    public void AdicionarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void PegarLivroEmprestado(Scanner scanner, Usuario usuario){
        System.out.println("Qual livro você quer pegar emprestado?");
        String livroEmprestado = scanner.nextLine();
        boolean encontrado = false;
        for (Livro livro : livros){
            if(livroEmprestado.equalsIgnoreCase(livro.getTitulo())){
                Emprestimo emprestimo = new Emprestimo(usuario, livro);
                emprestimo.RealizarEmprestimo();
            }
        }
        if(!encontrado){
            System.out.println("Livro não encontrado, tente novamente.");
        }
    }

    public void ConsultarHistoricoDeEmprestimosDoUsuario(Scanner scanner){
        System.out.println("Qual é o id do usuário que você quer consultar os empréstimos?");
        int idConsulta = scanner.nextInt();
        boolean existe = false;
        for (Usuario usuario : usuarios){
            if(idConsulta == usuario.getId()){
                for (Emprestimo emprestimoUsuarios : usuario.getEmprestimos()){
                    System.out.println("Usuario: "+usuario.getNome());
                    System.out.println("Livro emprestado: "+emprestimoUsuarios.getLivro().getTitulo());
                    System.out.println("Data do empréstimo: "+emprestimoUsuarios.getDataEmprestimo().format(formatter));
                    System.out.println("Data da devolução: "+emprestimoUsuarios.getDataDevolucao().format(formatter));
                    System.out.println("----------");
                    existe = true;
                }
                System.out.println("Total de livros emprestados para o usuario "+usuario.getNome()+": "+usuario.getEmprestimos().size());
            }
        }
        if(!existe){
            System.out.println("ID de usuário não encontrado.");
        }
    }

    public void BuscarLivroPorTitulo(Scanner scanner){
        System.out.println("Qual livro você quer buscar?");
        scanner.nextLine();
        String buscaLivro = scanner.nextLine();
        boolean encontrado = false;
        for (Livro livro : livros){
            if(buscaLivro.equalsIgnoreCase(livro.getTitulo().trim())){
                System.out.println(livro.getTitulo());
                System.out.println("Autor: "+livro.getAutor().getNome());
                System.out.println("Status de disponibilidade: "+livro.getDisponivel());
                System.out.println("ID: "+livro.getId());
                encontrado = true;
                break;
            }
        }
        if(!encontrado){
            System.out.println("Livro não encontrado!");
        }
    }

    public void ListarLivrosDisponiveis(){
        System.out.println("Quantidade de livros cadastrados: "+livros.size());
        System.out.println("Livros disponíveis: ");
        for (Livro livro : livros){
            if(livro.getDisponivel()){
                System.out.println("ID: "+livro.getId());
                System.out.println("Titulo: "+livro.getTitulo());
                System.out.println("Autor: "+livro.getAutor().getNome());
                System.out.println("Data de Cadastro: "+livro.getDataCadastro().format(formatter));
                System.out.println("----------");
            }
        }
    }
    public List<Livro> getLivros() {
        return livros;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

}
