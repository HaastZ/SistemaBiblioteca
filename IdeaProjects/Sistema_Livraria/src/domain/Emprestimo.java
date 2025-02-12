package domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Emprestimo {
    private static int count = 1;
    private int id;
    private Livro livro;
    private Usuario usuario;
    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataDevolucao;

    public Emprestimo(Usuario usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
        this.id = count++;
        this.dataEmprestimo = LocalDateTime.now();
        this.dataDevolucao = dataEmprestimo.plusDays(20);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDateTime getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDateTime getDataDevolucao() {
        return dataDevolucao;
    }

    public Livro getLivro() {
        return livro;
    }

    public void RealizarEmprestimo(){
        if(livro.getDisponivel()){
            livro.setDisponivel(false);
            usuario.setEmprestimos(this);
            System.out.println("O livro "+livro.getTitulo() + " foi emprestado para "+usuario.getNome());
        }
        else System.out.println("O livro "+livro.getTitulo()+" com o id "+livro.getId()+" já está emprestado");
    }
}
