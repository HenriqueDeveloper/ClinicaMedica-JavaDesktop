
package br.com.clinica.model.util.validacoes;

import br.com.clinica.model.dao.FuncionarioDAO;
import br.com.clinica.model.objetos.Funcionario;
import javax.swing.JOptionPane;


public class FuncionarioValidar {
    
    private  FuncionarioDAO repositorio = new FuncionarioDAO();
    
    
     public void validareSalvarFuncionario(Funcionario funcionario, String condicao) {

        if (funcionario.getNome().length() < 5) {
            JOptionPane.showMessageDialog(null, "Insira um Funcionário com no mínimo 5 caracteres");
            return;
        } else if (funcionario.getCpf().length() < 11) {
            JOptionPane.showMessageDialog(null, "Insira um CPF válido com 11 caracteres");
            return;
        } else if (funcionario.getEndereco().getCep().length() < 7) {
            JOptionPane.showMessageDialog(null, "Insira um CPF válido com 7 caracteres");
            return;
        } else if (funcionario.getEndereco().getNumero() <= 0) {
            JOptionPane.showMessageDialog(null, "Insira um número válido");
            return;
        }
        if (condicao.equals("salvar")) {
            for (Funcionario funcionarioo : repositorio.listarTodos()) {

                if (funcionario.getCpf().equals(funcionarioo.getCpf())) {
                    JOptionPane.showMessageDialog(null, "CPF já cadastrado no banco de dados");
                    return;
                }
            }
            repositorio.cadastrar(funcionario);
        } else if (condicao.equals("alterar")) {
            repositorio.atualizar(funcionario);
        }

    }
    
}
