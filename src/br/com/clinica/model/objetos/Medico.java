package br.com.clinica.model.objetos;

import java.util.Objects;

public class Medico extends Pessoa {

    private String crm;
    private String especialidade;
    private String diaAtendimento;
    private String contato;
  

    public Medico() {
    }

    public Medico(String crm, String especialidade, String diaAtendimento, String contato, String nome, int idade, String cpf, String sexo, Endereco endereco) {
        super(nome, idade, cpf, sexo, endereco);
        this.crm = crm;
        this.especialidade = especialidade;
        this.diaAtendimento = diaAtendimento;

        this.contato = contato;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getDiaAtendimento() {
        return diaAtendimento;
    }

    public void setDiaAtendimento(String diaAtendiemnto) {
        this.diaAtendimento = diaAtendiemnto;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.especialidade);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Medico other = (Medico) obj;
        if (!Objects.equals(this.especialidade, other.especialidade)) {
            return false;
        }
        return true;
    }
    

}
