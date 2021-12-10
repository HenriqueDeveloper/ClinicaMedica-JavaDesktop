package br.com.clinica.model.util.validacoes;

import br.com.clinica.model.dao.PacienteDAO;
import br.com.clinica.model.objetos.Paciente;
import javax.swing.JOptionPane;

public class PacienteValidar {

    private PacienteDAO repositorio;

    public void validarPaciente(Paciente paciente, String condicao) {

        this.repositorio = new PacienteDAO();

        if (paciente.getNome().length() < 5) {
            JOptionPane.showMessageDialog(null, "Insira um Funcionário com no mínimo 5 caracteres");
            return;
        } else if (paciente.getCpf().length() < 11) {
            JOptionPane.showMessageDialog(null, "Insira um CPF válido com 11 caracteres");
            return;
        } else if (paciente.getEndereco().getCep().length() < 7) {
            JOptionPane.showMessageDialog(null, "Insira um CEP válido com 7 caracteres");
            return;
        } else if (paciente.getEndereco().getNumero() <= 0) {
            JOptionPane.showMessageDialog(null, "Insira um número válido");
            return;
        }
        if (condicao.equals("salvar")) {
            for (Paciente pacientee : repositorio.listarTodos()) {

                if (paciente.getCpf().equals(pacientee.getCpf())) {
                    JOptionPane.showMessageDialog(null, "CPF já cadastrado no banco de dados");
                    return;
                }
            }
            repositorio.cadastrar(paciente);
        } else if (condicao.equals("alterar")) {
            repositorio.atualizar(paciente);
        }

    }

}
