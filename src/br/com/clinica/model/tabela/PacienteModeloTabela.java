package br.com.clinica.model.tabela;

import br.com.clinica.model.objetos.Paciente;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PacienteModeloTabela extends AbstractTableModel {

    private List<Paciente> pacientes;
    private String[] nomeColuna = {"ID", "Nome", "Idade", "CPF", "Sexo"};

    public PacienteModeloTabela(List<Paciente> pacientes) {
        this.pacientes = pacientes;
        

    }

    @Override
    public String getColumnName(int column) {
        return nomeColuna[column];
    }

    @Override
    public int getRowCount() {
        return this.pacientes.size();
    }

    @Override
    public int getColumnCount() {
        return this.nomeColuna.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {

            case 0:
                return pacientes.get(rowIndex).getId();

            case 1:
                return pacientes.get(rowIndex).getNome();
            case 2:
                return pacientes.get(rowIndex).getIdade();
            case 3:
                return pacientes.get(rowIndex).getCpf();
            case 4:
                return pacientes.get(rowIndex).getSexo();

        }
        return null;
    }

    @Override
    public void setValueAt(Object valor, int rowIndex, int columnIndex) {

        switch (columnIndex) {

            case 1:
                pacientes.get(rowIndex).setNome((String) valor);
                break;
            case 2:
                pacientes.get(rowIndex).setIdade(Integer.parseInt((String) valor));
                break;

            case 3:
                pacientes.get(rowIndex).setCpf(((String) valor));
                break;

            case 4:
                pacientes.get(rowIndex).setSexo((String) valor);
                break;

        }
         fireTableRowsUpdated(rowIndex, rowIndex);

    }

}
