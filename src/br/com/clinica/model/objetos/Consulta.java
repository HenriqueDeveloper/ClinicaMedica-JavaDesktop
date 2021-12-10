package br.com.clinica.model.objetos;

public class Consulta {

    private long id;
    private Agendamento agendamento;
    private double pesoPaciente;
    private String laudoMedico;
    private String prescricao;

    public Consulta() {
    }

    public Consulta(Agendamento agendamento, double pesoPaciente, String laudoMedico, String prescricao) {
        this.agendamento = agendamento;
        this.pesoPaciente = pesoPaciente;
        this.laudoMedico = laudoMedico;
        this.prescricao = prescricao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public double getPesoPaciente() {
        return pesoPaciente;
    }

    public void setPesoPaciente(double pesoPaciente) {
        this.pesoPaciente = pesoPaciente;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }

    public String getLaudoMedico() {
        return laudoMedico;
    }

    public void setLaudoMedico(String laudoMedico) {
        this.laudoMedico = laudoMedico;
    }

}
