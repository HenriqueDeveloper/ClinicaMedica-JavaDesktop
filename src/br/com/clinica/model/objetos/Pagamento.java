package br.com.clinica.model.objetos;

public class Pagamento {

    private long id;
    private Agendamento agendamento;
    private double valor;
    private String tipoPagamento;
    private String parcelamento;
    private String bandeira;

    public Pagamento(Agendamento agendamento, double valor, String tipoPagamento) {
        this.agendamento = agendamento;
        this.valor = valor;
        this.tipoPagamento = tipoPagamento;
    }

    public Pagamento(Agendamento agendamento, double valor, String tipoPagamento, String parcelamento, String bandeira) {
        this.agendamento = agendamento;
        this.valor = valor;
        this.tipoPagamento = tipoPagamento;
        this.parcelamento = parcelamento;
        this.bandeira = bandeira;
    }

    public Pagamento() {
    }
    
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public String getParcelamento() {
        return parcelamento;
    }

    public void setParcelamento(String parcelamento) {
        this.parcelamento = parcelamento;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

}
