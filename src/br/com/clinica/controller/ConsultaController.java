package br.com.clinica.controller;

import br.com.clinica.connectionfactory.ConnectionFactory;
import br.com.clinica.interfaces.GenericInterfaceController;
import br.com.clinica.model.dao.AgendamentoDAO;
import br.com.clinica.model.dao.ConsultaDAO;
import br.com.clinica.model.tabela.ConsultaModeloTabela;
import br.com.clinica.model.objetos.Agendamento;
import br.com.clinica.model.objetos.Consulta;
import br.com.clinica.view.telas.TelaConsulta;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import net.sf.jasperreports.view.JRViewer;

public class ConsultaController implements GenericInterfaceController<Consulta> {

    private TelaConsulta view;
    private AgendamentoDAO repositorioAgendamento;
    private ConsultaDAO repositorioConsulta;

    public ConsultaController(TelaConsulta view) {
        this.view = view;
    }

    public void buscarAgendamento() {
        this.repositorioAgendamento = new AgendamentoDAO();
        try {
            Agendamento agendamento = repositorioAgendamento.listarPorCPF(this.view.getTfCPF().getText());

            this.view.getTfNomePaciente().setText(toUpperFirstCase(agendamento.getPaciente().getNome()));
            this.view.getTfNomeMedico().setText(toUpperFirstCase(agendamento.getMedico().getNome()));
            this.view.getTfData().setText(new SimpleDateFormat("dd/MM/yyyy").format(agendamento.getData()));
            this.view.getTfIdade().setText(String.valueOf(agendamento.getPaciente().getIdade()));
            this.view.getTfSexo().setText(toUpperFirstCase(agendamento.getPaciente().getSexo()));
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Agendamento não encontrado! Verifique se o pagamento foi efetuado ou o CPF está incorreto.");

        }

    }

    public String toUpperFirstCase(String string) {
        char[] arr = string.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);

    }

    @Override
    public void salvar() {
        this.repositorioConsulta = new ConsultaDAO();
        this.repositorioAgendamento = new AgendamentoDAO();

        Consulta consulta = new Consulta();

        String cpf = this.view.getTfCPF().getText();

        Agendamento agendamento = repositorioAgendamento.listarPorCPF(cpf);

        consulta.setAgendamento(agendamento);
        consulta.setLaudoMedico(this.view.getTextAreaoLaudoMedico().getText());
        consulta.setPrescricao(this.view.getTextAreaPrescricao().getText());

        repositorioConsulta.cadastrar(consulta);

        atualizarTabela();
    }

    public void btnRelatorio() {

        try {

            if (this.view.getTabelaConsulta().getSelectedRow() != - 1) {
                String id = String.valueOf(String.valueOf(this.view.getTabelaConsulta().getValueAt(this.view.getTabelaConsulta().getSelectedRow(), 0)));
                HashMap<String, Object> dados = new HashMap();
                
                 ImageIcon gto = new ImageIcon(getClass().getResource("/br/com/clinica/model/modelorelatorio/imagem.png"));
                dados.put("id", id);
                dados.put("imagem", gto.getImage());
                dados.put("id", id);
                
                InputStream relatorio = getClass().getResourceAsStream("/br/com/clinica/model/modelorelatorio/Consulta.jrxml");
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

    @Override
    public void editar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void bloquearCampos(boolean bloquear) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void limparCampos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listarCidadesPorEstado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void btnAlterar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void btnExcluir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void btnCancelar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void btnPesquisar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizarTabela() {
        ConsultaDAO repositorio = new ConsultaDAO();
        ArrayList<Consulta> lista = repositorio.listarTodos();
        ConsultaModeloTabela modelo = new ConsultaModeloTabela(lista);
        this.view.getTabelaConsulta().setModel(modelo);
    }

    @Override
    public void atualizarTabela(List<Consulta> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
