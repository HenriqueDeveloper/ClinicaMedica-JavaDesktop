package br.com.clinica.model.objetos;

public class Funcionario extends Pessoa {

    public Funcionario() {
    }

    public Funcionario(String nome, int idade, String cpf, String sexo, Endereco endereco) {
        super(nome, idade, cpf, sexo, endereco);

    }
}
