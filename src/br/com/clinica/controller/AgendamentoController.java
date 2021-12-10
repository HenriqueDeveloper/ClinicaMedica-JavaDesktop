package br.com.clinica.controller;

import br.com.clinica.model.dao.MedicoDAO;
import br.com.clinica.interfaces.GenericInterfaceController;
import br.com.clinica.model.dao.AgendamentoDAO;
import br.com.clinica.model.dao.PacienteDAO;
import br.com.clinica.model.tabela.AgendamentoModeloTabela;
import br.com.clinica.model.objetos.Agendamento;
import br.com.clinica.model.objetos.Medico;
import br.com.clinica.model.objetos.Paciente;
import br.com.clinica.view.telas.TelaAgendamento;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;

public class AgendamentoController implements GenericInterfaceController<Agendamento> {

    private TelaAgendamento view;
    private AgendamentoDAO repositorio;
    private AgendamentoModeloTabela modelo;

    public AgendamentoController(TelaAgendamento view) {
        this.view = view;

    }

    public void ativarCboxPaciente() {
        PacienteDAO repositorio = new PacienteDAO();

        ArrayList<Paciente> pacientes = repositorio.listarTodos();

        for (Paciente paciente : pacientes) {
            this.view.getcPaciente().addItem(paciente.getNome());
        }
    }

    public void ativarCboxEspecialidade() {
        MedicoDAO repositorio = new MedicoDAO();

        ArrayList<Medico> medicos = repositorio.listarTodos();

        Set<Medico> s = new HashSet<>(medicos);

        for (Medico medico : s) {
            this.view.getTfEspecialidade().addItem(medico.getEspecialidade());
        }
    }

    public void listarEspecialidade() {

        MedicoDAO repositorio = new MedicoDAO();

        String nome = this.view.getTfEspecialidade().getSelectedItem().toString();

        this.view.getcMedico().removeAllItems();
        for (Medico medico : repositorio.listarEspecialidadePorMedico(nome)) {
            this.view.getcMedico().addItem(medico.getNome());
        }

    }

    @Override
    public void editar() {

        try {

            Agendamento consulta = new Agendamento();

            int codigo = Integer.parseInt(String.valueOf(this.view.getTabelaConsulta()
                    .getValueAt(this.view.getTabelaConsulta().getSelectedRow(), 0)));

            consulta.setId(codigo);

            String dia = this.view.getcDia().getSelectedItem().toString();
            consulta.setDiaConsulta(dia);

            String dataCampo = this.view.getTfData().getText();

            SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
            Date novaData = data.parse(dataCampo);

            consulta.setData(novaData);

            String horario = this.view.getcHorario().getSelectedItem().toString();
            consulta.setHorario(horario);

            consulta.setDescricao(this.view.getTfDescricao().getText());

            repositorio.atualizar(consulta);

            atualizarTabela();
            centralizarCelulaTabela();

        } catch (ParseException ex) {
            Logger.getLogger(TelaAgendamento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void salvar() {
        try {

            AgendamentoDAO repositorio = new AgendamentoDAO();
            PacienteDAO repositorioPaciente = new PacienteDAO();
            MedicoDAO repositorioMedico = new MedicoDAO();
            Agendamento agendamento = new Agendamento();

            String nomeMedico = this.view.getcMedico().getSelectedItem().toString();
            String nomePaciente = this.view.getcPaciente().getSelectedItem().toString();

            Paciente paciente = repositorioPaciente.listarPorNome(nomePaciente);
            Medico medico = repositorioMedico.listarPorNome(nomeMedico);
            String dia = this.view.getcDia().getSelectedItem().toString();

            String dataCampo = this.view.getTfData().getText();
            String horarioCampo = this.view.getcHorario().getSelectedItem().toString();

            SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
            Date novaData = data.parse(dataCampo);

            agendamento.setDiaConsulta(dia);
            agendamento.setPaciente(paciente);
            agendamento.setMedico(medico);
            agendamento.setData(novaData);
            agendamento.setHorario(horarioCampo);

            agendamento.setDescricao(this.view.getTfDescricao().getText());
            repositorio.cadastrar(agendamento);

            atualizarTabela();
            centralizarCelulaTabela();
        } catch (Exception ex) {
            Logger.getLogger(TelaAgendamento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void btnAlterar() {

        this.view.setCondicao("alterar");

        AgendamentoDAO repositorio = new AgendamentoDAO();

        int codigo = Integer.parseInt(String.valueOf(this.view.getTabelaConsulta().
                getValueAt(this.view.getTabelaConsulta().getSelectedRow(), 0)));

        Agendamento consulta = repositorio.listarPorId(codigo);

        if (consulta == null) {
            bloquearCampos(true);
        } else {
            bloquearCampos(false);
        }

        this.view.getTfData().setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(consulta.getData().getTime())));

        this.view.getTfDescricao().setText(consulta.getDescricao());
        this.view.getcHorario().setSelectedItem(consulta.getHorario());
        this.view.getcDia().setSelectedItem(consulta.getDiaConsulta());

    }

    @Override
    public void btnExcluir() {

        if (this.view.getTabelaConsulta().getSelectedRow() != -1) {
            AgendamentoDAO repositorio = new AgendamentoDAO();

            int linha = this.view.getTabelaConsulta().getSelectedRow();
            long codigo = Long.parseLong(String.valueOf(this.view.getTabelaConsulta().getValueAt(linha, 0)));

            Agendamento consulta = repositorio.listarPorId(codigo);
            consulta.setId(codigo);

            switch (JOptionPane.showConfirmDialog(null, " Tem certeza que deseja excluir ? ", "Confirmar exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {

                case 0:

                    repositorio.excluir(consulta);
                    atualizarTabela();
                    centralizarCelulaTabela();

                    break;

                case 1:
                    break;
            }

        } else {
            JOptionPane.showMessageDialog(null, "Escolha uma consulta para excluir!");
        }

    }

    @Override
    public void bloquearCampos(boolean bloquear) {

        this.view.getcDia().setEnabled(!bloquear);
        this.view.getTfData().setEnabled(!bloquear);
        this.view.getTfDescricao().setEnabled(!bloquear);
        this.view.getcMedico().setEnabled(!bloquear);
        this.view.getcPaciente().setEnabled(!bloquear);
        this.view.getTfEspecialidade().setEnabled(!bloquear);
        this.view.getcHorario().setEnabled(!bloquear);

    }

    @Override
    public void btnPesquisar() {

        this.repositorio = new AgendamentoDAO();
        String cpf = this.view.getTfCPF().getText();

        Agendamento agendamento = repositorio.listarPorCPF(cpf);

        if (agendamento == null) {
            JOptionPane.showMessageDialog(null, "CPF inválido!");
        } else {

            atualizarTabela(Arrays.asList(agendamento));

        }
    }

    @Override
    public void limparCampos() {

    }

    @Override
    public void listarCidadesPorEstado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizarTabela() {
        this.repositorio = new AgendamentoDAO();
        List<Agendamento> consultas = repositorio.listarTodos();
        this.modelo = new AgendamentoModeloTabela(consultas);
        this.view.getTabelaConsulta().setModel(modelo);

    }

    @Override
    public void atualizarTabela(List<Agendamento> consultas
    ) {
        this.modelo = new AgendamentoModeloTabela(consultas);
        this.view.getTabelaConsulta().setModel(modelo);
    }

    @Override
    public void btnCancelar() {
        atualizarTabela();
    }

    public void centralizarCelulaTabela() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        this.view.getTabelaConsulta().getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

    }

}
