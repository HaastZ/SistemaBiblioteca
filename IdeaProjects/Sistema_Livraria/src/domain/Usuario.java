package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id;
    private static int count = 1;
    private String nome;
    private String email;
    private List<Emprestimo> emprestimos;
    private LocalDate dataNascimento;

    public Usuario(String nome, String email, LocalDate dataNascimento) {
        Objects.requireNonNull(nome, "Você não pode criar um usuario sem nome"); // Não pode passar valores nulos
        Objects.requireNonNull(email, "Você não pode criar um usuario sem email"); // Não pode passar valores nulos
        Objects.requireNonNull(dataNascimento, "Você não pode criar um usuario sem data de nascimento"); // Não pode passar valores nulos
        this.dataNascimento = dataNascimento;
        this.id = count++;
        this.nome = nome;
        this.email = email;
        this.emprestimos = new ArrayList<>();
    }

    public LocalDate getDataNascimento() {return dataNascimento;}

    public int getId() {return id;}

    public List<Emprestimo> getEmprestimos() {return emprestimos;}

    public void setEmprestimos(Emprestimo emprestimo) {this.emprestimos.add(emprestimo);}

    public String getNome() {return nome;}

    public String getEmail() {return email;}
}
