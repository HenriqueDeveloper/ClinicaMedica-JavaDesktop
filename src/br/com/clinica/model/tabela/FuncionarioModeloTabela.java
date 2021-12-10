package br.com.clinica.model.tabela;

import br.com.clinica.model.objetos.Funcionario;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class FuncionarioModeloTabela extends AbstractTableModel {

    private List<Funcionario> funcionarios;
    private String[] nomeColuna = {"ID", "Nome", "Idade", "CPF", "Sexo"};

    public FuncionarioModeloTabela(List<Funcionario> func) {
        this.funcionarios = func;

    }

    @Override
    public int getRowCount() {
        return this.funcionarios.size();
    }

    @Override
    public int getColumnCount() {
        return this.nomeColuna.length;
    }

    @Override
    public String getColumnName(int column) {
        return this.nomeColuna[column];

    }

    @Override
    public Object getValueAt(int linha, int coluna) {

        if (coluna == 0) {
            return this.funcionarios.get(linha).getId();
        }
        if (coluna == 1) {
            return this.funcionarios.get(linha).getNome();
        }
        if (coluna == 2) {
            return this.funcionarios.get(linha).getIdade();
        }
        if (coluna == 3) {
            return this.funcionarios.get(linha).getCpf();
        }
        if (coluna == 4) {
            return this.funcionarios.get(linha).getSexo();
        } else {
            return null;
        }

    }

}
