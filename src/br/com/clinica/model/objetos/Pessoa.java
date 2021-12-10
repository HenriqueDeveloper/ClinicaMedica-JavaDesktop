package br.com.clinica.model.objetos;

import javax.swing.JOptionPane;

public class Pessoa {

    private long id;
    private String nome;
    private int idade;
    private String cpf;
    private String sexo;
    private Endereco endereco;

    public Pessoa() {
    }

    public Pessoa(long id, String nome, int idade, String cpf, String sexo, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.sexo = sexo;
        this.endereco = endereco;
    }

    public Pessoa(String nome, int idade, String cpf, String sexo, Endereco endereco) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.sexo = sexo;
        this.endereco = endereco;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        if (nome.length() < 5) {
            JOptionPane.showMessageDialog(null, "Insira um nome com no mínimo 5 caracteres");
            return;
        } else {

            this.nome = nome;

        }
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf.length() < 11) {
            JOptionPane.showMessageDialog(null, "Insira um CPF válido com 11 caracteres");
            return;
        } else {
            this.cpf = cpf;

        }
    }
    

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
