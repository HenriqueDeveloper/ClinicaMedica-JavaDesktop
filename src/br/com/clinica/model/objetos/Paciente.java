package br.com.clinica.model.objetos;



public class Paciente extends Pessoa {

    public Paciente() {
    }

    public Paciente(String nome, int idade, String cpf, String sexo, Endereco endereco) {

        super(nome, idade, cpf, sexo, endereco);

    }
}
