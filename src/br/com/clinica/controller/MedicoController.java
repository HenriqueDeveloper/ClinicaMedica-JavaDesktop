package br.com.clinica.controller;

import br.com.clinica.model.dao.MedicoDAO;
import br.com.clinica.interfaces.GenericInterfaceController;
import br.com.clinica.model.tabela.MedicoModeloTabela;
import br.com.clinica.model.util.validacoes.MedicoValidar;
import br.com.clinica.model.objetos.Cidade;
import br.com.clinica.model.objetos.Endereco;
import br.com.clinica.model.objetos.Medico;
import br.com.clinica.view.telas.TelaMedico;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

public class MedicoController implements GenericInterfaceController<Medico> {

    private TelaMedico view;
    private MedicoDAO repositorio;

    public MedicoController(TelaMedico view) {
        this.view = view;

    }

    @Override
    public void salvar() {

        Medico medico = new Medico();
        MedicoValidar validar = new MedicoValidar();
        Endereco endereco = new Endereco();

        medico.setContato(view.getTfContato().getText());
        medico.setCpf(view.getTfCpf().getText());
        medico.setCrm(view.getTfCrm().getText());
        medico.setDiaAtendimento(view.getcDia().getSelectedItem().toString());
        medico.setEspecialidade(view.getcEspecialidade().getSelectedItem().toString());
        medico.setNome(view.getTfNome().getText());
        medico.setIdade(Integer.parseInt(view.getTfIdade().getText()));
        if (view.getrMasculino().isSelected()) {
            medico.setSexo(view.getrMasculino().getText());

        } else {
            medico.setSexo(view.getrFeminino().getText());
        }
        endereco.setBairro(view.getTfBairro().getText());
        endereco.setCep(view.getTfCep().getText());
        endereco.setCidade(view.getCbCidade().getSelectedItem().toString());
        endereco.setNumero(Integer.parseInt(view.getTfNumero().getText()));
        endereco.setRua(view.getTfRua().getText());
        endereco.setUf(view.getCbEstado().getSelectedItem().toString());
        medico.setEndereco(endereco);

        validar.validarMedico(medico, "salvar");

        atualizarTabela();
    }

    @Override
    public void editar() {
        Medico medico = new Medico();
        MedicoValidar validar = new MedicoValidar();
        Endereco endereco = new Endereco();

        int linha = view.getTabelaMedico().getSelectedRow();
        long codigo = Long.parseLong(String.valueOf(view.getTabelaMedico().getValueAt(linha, 0)));

        medico.setId(codigo);
        medico.setCrm(view.getTfCrm().getText());
        medico.setEspecialidade(view.getcEspecialidade().getSelectedItem().toString());
        medico.setDiaAtendimento(view.getcDia().getSelectedItem().toString());
        medico.setContato(view.getTfContato().getText());
        medico.setNome(view.getTfNome().getText());
        medico.setIdade(Integer.parseInt(view.getTfIdade().getText()));
        medico.setCpf(view.getTfCpf().getText());
        if (view.getrMasculino().isSelected()) {
            medico.setSexo(view.getrMasculino().getText());
        } else {
            medico.setSexo(view.getrFeminino().getText());
        }
        endereco.setUf(view.getCbEstado().getSelectedItem().toString());
        endereco.setCidade(view.getCbCidade().getSelectedItem().toString());
        endereco.setCep(view.getTfCep().getText());
        medico.setDiaAtendimento(view.getcDia().getSelectedItem().toString());
        endereco.setBairro(view.getTfBairro().getText());
        endereco.setRua(view.getTfRua().getText());
        endereco.setNumero(Integer.parseInt(view.getTfNumero().getText()));
        medico.setEndereco(endereco);

        validar.validarMedico(medico, "alterar");
        atualizarTabela();

        /* this.modelo.setValueAt(this.view.getTfNome().getText(), linha, 1);
        this.modelo.setValueAt(this.view.getTfIdade().getText(), linha, 2);
        this.modelo.setValueAt(this.view.getrMasculino().isSelected()
                ? this.view.getrMasculino().getText() : this.view.getrFeminino().getText(), linha, 3);
        this.modelo.setValueAt(this.view.getTfCpf().getText(), linha, 4);
        this.modelo.setValueAt(this.view.getTfCrm().getText(), linha, 5);
        this.modelo.setValueAt(this.view.getcEspecialidade().getSelectedItem(), linha, 6);
        this.modelo.setValueAt(this.view.getcDia().getSelectedItem(), linha, 7);
        this.modelo.setValueAt(this.view.getTfContato().getText(), linha, 8);
        this.modelo.setValueAt(this.view.getCbEstado().getSelectedItem(), linha, 9);
        this.modelo.setValueAt(this.view.getTfCep().getText(), linha, 1);
        this.modelo.setValueAt(this.view.getCbCidade().getSelectedItem(), linha, 10);
        this.modelo.setValueAt(this.view.getTfBairro().getText(), linha, 11);
        this.modelo.setValueAt(this.view.getTfRua().getText(), linha, 12);
        this.modelo.setValueAt(this.view.getTfNumero().getText(), linha, 13);*/
    }

    @Override
    public void bloquearCampos(boolean controle) {

        view.getTfBairro().setEnabled(!controle);
        view.getcEspecialidade().setEnabled(!controle);
        view.getcDia().setEnabled(!controle);
        view.getTfCep().setEnabled(!controle);
        view.getTfContato().setEnabled(!controle);
        view.getTfCpf().setEnabled(!controle);
        view.getTfCrm().setEnabled(!controle);
        view.getTfIdade().setEnabled(!controle);
        view.getTfNome().setEnabled(!controle);
        view.getTfNumero().setEnabled(!controle);
        view.getTfRua().setEnabled(!controle);
        view.getCbCidade().setEnabled(!controle);
        view.getCbEstado().setEnabled(!controle);
        view.getrFeminino().setEnabled(!controle);
        view.getrMasculino().setEnabled(!controle);

    }

    @Override
    public void limparCampos() {

        view.getTfBairro().setText("");
        view.getTfCep().setText("");
        view.getTfContato().setText("");
        view.getTfCpf().setText("");
        view.getTfCrm().setText("");
        view.getTfIdade().setText("");
        view.getTfNome().setText("");
        view.getTfNumero().setText("");
        view.getTfRua().setText("");
    }

    @Override
    public void listarCidadesPorEstado() {
        MedicoDAO repositorio = new MedicoDAO();
        String uf = view.getCbEstado().getSelectedItem().toString();

        ArrayList<Cidade> cidades = repositorio.listarCidadesPorUF(uf);

        view.getCbCidade().removeAllItems();

        for (Cidade cidade : cidades) {

            view.getCbCidade().addItem(cidade.getCidade());
        }

    }

    @Override
    public void btnAlterar() {

        if (this.view.getTabelaMedico().getSelectedRow() != -1) {
            this.view.setFuncao("alterar");

            this.bloquearCampos(false);

            int linha = view.getTabelaMedico().getSelectedRow();
            long codigo = Long.parseLong(String.valueOf(view.getTabelaMedico().getValueAt(linha, 0)));
            MedicoDAO repositorio = new MedicoDAO();
            Medico medico = repositorio.listarPorId(codigo);
            view.getTfCpf().setEnabled(false);
            view.getTfCrm().setText(medico.getCrm());
            view.getcEspecialidade().getSelectedItem();
            view.getcDia().getSelectedItem();
            view.getTfContato().setText(medico.getContato());
            view.getTfNome().setText(medico.getNome());
            view.getTfIdade().setText(String.valueOf(medico.getIdade()));
            view.getTfCpf().setText(medico.getCpf());
            view.getTfBairro().setText(medico.getEndereco().getBairro());
            view.getTfCep().setText(medico.getEndereco().getCep());
            view.getTfNumero().setText(String.valueOf(medico.getEndereco().getNumero()));
            view.getTfRua().setText(medico.getEndereco().getRua());
            view.getCbEstado().setSelectedItem(medico.getEndereco().getUf());
            view.getCbCidade().setSelectedItem(medico.getEndereco().getCidade());
            view.getcDia().setSelectedItem(medico.getDiaAtendimento());
            view.getcEspecialidade().setSelectedItem(medico.getEspecialidade());
            
             if(medico.getSexo().equals("Masculino")){
                this.view.getrMasculino().setSelected(true);
            }
             if(medico.getSexo().equals("Feminino")){
                this.view.getrFeminino().setSelected(true);
            }


        } else {
            JOptionPane.showMessageDialog(null, "Escolha um médico");
        }

    }

    @Override
    public void btnExcluir() {

        if (this.view.getTabelaMedico().getSelectedRow() != -1) {
            long codigo = Long.parseLong(String.valueOf(view.getTabelaMedico().getValueAt(view.getTabelaMedico().getSelectedRow(), 0)));
            MedicoDAO repositorio = new MedicoDAO();
            Medico medico = repositorio.listarPorId(codigo);
            medico.setId(codigo);

            switch (JOptionPane.showConfirmDialog(null, " Tem certeza que deseja excluir ? ", "Confirmar exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {

                case 0:
                    repositorio.excluir(medico);
                    atualizarTabela();

                    break;

                case 1:
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Escolha um paciente");
        }

    }

    @Override
    public void btnPesquisar() {

        MedicoDAO repositorio = new MedicoDAO();
        String cpf = view.getTfPesquisarCpf().getText();
        Medico medico = repositorio.listarPorCPF(cpf);

        if (medico == null) {
            JOptionPane.showMessageDialog(null, "CPF inválido!");
        } else {
            atualizarTabela(Arrays.asList(medico));
        }
    }

    @Override
    public void atualizarTabela() {
        this.repositorio = new MedicoDAO();
        List<Medico> medicos = repositorio.listarTodos();
        MedicoModeloTabela modelo = new MedicoModeloTabela(medicos);
        this.view.getTabelaMedico().setModel(modelo);
    }

    @Override
    public void atualizarTabela(List<Medico> medicos) {
        MedicoModeloTabela modelo = new MedicoModeloTabela(medicos);
        this.view.getTabelaMedico().setModel(modelo);
    }

    @Override
    public void btnCancelar() {
        atualizarTabela();
    }
}
