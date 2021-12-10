package br.com.clinica.controller;

import br.com.clinica.connectionfactory.ConnectionFactory;
import br.com.clinica.model.dao.AgendamentoDAO;
import br.com.clinica.model.dao.PagamentoDAO;
import br.com.clinica.model.tabela.VendaModeloTabela;
import br.com.clinica.model.objetos.Agendamento;
import br.com.clinica.model.objetos.Pagamento;
import br.com.clinica.view.telas.TelaConsulta;
import br.com.clinica.view.telas.TelaPagamento;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JRViewer;

public class PagamentoController {

    private TelaPagamento view;
    private PagamentoDAO repositorioPagamento;
    private AgendamentoDAO repositorioAgendamento;
    private VendaModeloTabela modelo;
    private Pagamento pagamento;

    public PagamentoController(TelaPagamento view) {
        this.view = view;
    }

    public void btnPagamento() {

        double ppix, pdinheiro, totalpago, totalvenda, cartao, troco;
        this.repositorioAgendamento = new AgendamentoDAO();
        this.repositorioPagamento = new PagamentoDAO();

        ppix = Double.parseDouble(this.view.getTfPix().getText());
        pdinheiro = Double.parseDouble(this.view.getTfDinheiro().getText());
        cartao = Double.parseDouble(this.view.getLblPreco().getText().replace("R$", ""));
        totalvenda = Double.parseDouble(this.view.getLblPreco().getText().replace("R$", ""));

        totalpago = ppix + pdinheiro;

        troco = totalpago - totalvenda;
        this.view.getLblTotal().setText(String.valueOf(totalvenda));

        this.view.getTfTroco().setText("R$ " + troco);
        String nome = this.view.getcPacientes().getSelectedItem().toString();

        Agendamento agendamento = repositorioPagamento.listarEspecialidaPorNomePaciente(nome);

        pagamento = new Pagamento(agendamento, totalvenda, verificarMeioDePagamento());

        if (this.view.getcBandeira().getSelectedItem() != "Selecione") {
            totalpago = ppix + pdinheiro + cartao;
            pagamento = new Pagamento(agendamento, totalvenda, "Cartão", this.view.getcParcelas().getSelectedItem().toString(), this.view.getcBandeira().getSelectedItem().toString());
        }

        if (this.view.getcBandeira().getSelectedItem().toString().equals("Selecione")
                && (this.view.getTfDinheiro().getText().equals("0") && this.view.getTfPix().getText().equals("0"))) {
            JOptionPane.showMessageDialog(null, "Escolha uma forma de pagamento!");

        } else if (totalpago < totalvenda) {
            JOptionPane.showMessageDialog(null, "Valor abaixo do preço da consulta!");
            this.view.getTfTroco().setText("0");
        } else {

            repositorioPagamento.realizarPagamento(pagamento);
            repositorioAgendamento.atualizarResultadoPagamento(agendamento);
            atualizarTabela();
            this.view.getcPacientes().removeAllItems();

        }
        pacientesAgendados();
    }

    public String verificarMeioDePagamento() {

        if (Double.parseDouble(this.view.getTfDinheiro().getText()) != (0)) {
            return this.view.getLblDinheiro().getText();
        } else if (Double.parseDouble(this.view.getTfPix().getText()) != (0)) {
            return this.view.getLblPix().getText();
        }
        return null;
    }

    public void cPacientes() {

        try {
            this.repositorioPagamento = new PagamentoDAO();

            String nome = this.view.getcPacientes().getSelectedItem().toString();

            Agendamento agendamento = repositorioPagamento.listarEspecialidaPorNomePaciente(nome);

            this.view.getLblEspecialidade().setText(agendamento.getMedico().getEspecialidade());

            if (agendamento.getMedico().getEspecialidade().equals("Cardiologista")) {
                this.view.getLblPreco().setText("R$142");

            } else if (agendamento.getMedico().getEspecialidade().equals("Dermatologista")) {
                this.view.getLblPreco().setText("R$100");

            } else if (agendamento.getMedico().getEspecialidade().equals("Neurologista")) {
                this.view.getLblPreco().setText("R$300");
            } else if (agendamento.getMedico().getEspecialidade().equals("Infectologista")) {
                this.view.getLblPreco().setText("R$350");
            } else if (agendamento.getMedico().getEspecialidade().equals("Hematologista")) {
                this.view.getLblPreco().setText("R$250");
            }

            DecimalFormat formato = new DecimalFormat("0.0");
            double tresVezes = Double.parseDouble(this.view.getLblPreco().getText().replace("R$", "")) / 3;
            double duasVezes = Double.parseDouble(this.view.getLblPreco().getText().replace("R$", "")) / 2;
            double umaVez = Double.parseDouble(this.view.getLblPreco().getText().replace("R$", "")) / 1;

            this.view.getcParcelas().removeAllItems();
            this.view.getcParcelas().addItem("3x R$ " + formato.format(tresVezes));
            this.view.getcParcelas().addItem("2x R$ " + formato.format(duasVezes));
            this.view.getcParcelas().addItem("1x R$ " + formato.format(umaVez));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

    public void btnRelatorio() {
        try {

            if (this.view.getTblVendas().getSelectedRow() != - 1) {
                int id = Integer.parseInt(String.valueOf(this.view.getTblVendas().getValueAt(this.view.getTblVendas().getSelectedRow(), 0)));
                HashMap<String, Object> dados = new HashMap();
                ImageIcon gto = new ImageIcon(getClass().getResource("/br/com/clinica/model/modelorelatorio/imagem.png"));
                dados.put("id", id);
                dados.put("imagem", gto.getImage());

                InputStream relatorio = getClass().getResourceAsStream("/br/com/clinica/model/modelorelatorio/Pagamento.jrxml");

                JasperReport relatorioCompilado = JasperCompileManager.compileReport(relatorio);
                JasperPrint relatorioPrenchido = JasperFillManager.fillReport(relatorioCompilado, dados, new ConnectionFactory().getConnection());

                JFrame tela = new JFrame();
                tela.setSize(1000, 500);

                JRViewer view = new JRViewer(relatorioPrenchido);

                tela.getContentPane().add(view);
                tela.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Escolha uma linha da tabela");
            }
        } catch (JRException ex) {
            Logger.getLogger(TelaConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void pacientesAgendados() {
        this.repositorioAgendamento = new AgendamentoDAO();

        for (Agendamento agendamento : repositorioAgendamento.listarTodos()) {

            if (agendamento.getPagamento().equals("Aguardando")) {

                this.view.getcPacientes().addItem(agendamento.getPaciente().getNome());
            }
        }
    }

    public void atualizarTabela() {
        this.repositorioPagamento = new PagamentoDAO();
        List<Pagamento> pagamentos = repositorioPagamento.ListarPagamentos();
        this.modelo = new VendaModeloTabela(pagamentos);
        this.view.getTblVendas().setModel(modelo);
    }
}
