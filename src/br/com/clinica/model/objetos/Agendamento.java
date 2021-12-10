package br.com.clinica.model.objetos;

import java.util.Date;

public class Agendamento {

    private long id;
    private Paciente paciente;
    private Medico medico;
    private String diaConsulta;
    private String descricao;
    private String tipoPagamento;
    private Date data;
    private String horario;
    private String pagamento;

    public Agendamento() {
    }

    public Agendamento(Paciente paciente, Medico medico, String diaConsulta, String descricao, String especialidadeMedico, Date data, String horario, String tipoPagamento, String pagamento) {
        this.paciente = paciente;
        this.medico = medico;
        this.diaConsulta = diaConsulta;
        this.descricao = descricao;
        this.data = data;
        this.horario = horario;
        this.tipoPagamento = tipoPagamento;
        this.pagamento = pagamento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getDiaConsulta() {
        return diaConsulta;
    }

    public void setDiaConsulta(String diaConsulta) {
        this.diaConsulta = diaConsulta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

}
