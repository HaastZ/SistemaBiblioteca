package domain;

import java.time.LocalDate;

public class Autor {
    private static int count = 1;
    private int id;
    private String nome;
    private LocalDate dataNascimento;

    public Autor(String nome, LocalDate dataNascimento) {
        Objects.requireNonNull(nome, "Você não pode criar um usuario sem nome"); // Não pode passar valores nulos
        Objects.requireNonNull(dataNascimento, "Você não pode criar um usuario sem data de nascimento"); // Não pode passar valores nulos
        this.id = count++;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }
}
