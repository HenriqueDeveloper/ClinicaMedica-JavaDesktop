package br.com.clinica.model.tabela;

import br.com.clinica.model.objetos.Medico;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class MedicoModeloTabela extends AbstractTableModel {

    private List<Medico> medicos;
    private String[] nomeColuna = {"ID", "Nome", "CPF", "Sexo", "Especialidade",
        "Dia Atendimento"};

    public MedicoModeloTabela(List<Medico> medicos) {
        this.medicos = medicos;
    }

    @Override
    public int getRowCount() {
        return this.medicos.size();
    }

    @Override
    public int getColumnCount() {
        return nomeColuna.length;
    }

    @Override
    public String getColumnName(int column) {
        return this.nomeColuna[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {
            case 0:
                return medicos.get(rowIndex).getId();
            case 1:
                return medicos.get(rowIndex).getNome();
            case 2:
                return medicos.get(rowIndex).getCpf();
            case 3:
                return medicos.get(rowIndex).getSexo();
            case 4:
                return medicos.get(rowIndex).getEspecialidade();
            case 5:
                return medicos.get(rowIndex).getDiaAtendimento();

        }
        return null;
    }

    @Override
    public void setValueAt(Object valor, int rowIndex, int columnIndex) {

        switch (columnIndex) {
            case 1:
                medicos.get(rowIndex).setNome((String) valor);
                break;
            case 2:
                medicos.get(rowIndex).setIdade(Integer.parseInt((String) valor));
                break;
            case 3:
                medicos.get(rowIndex).setSexo((String) valor);
                break;

            case 4:
                medicos.get(rowIndex).setCpf((String) valor);
                break;

            case 5:
                medicos.get(rowIndex).setCrm((String) valor);
                break;

            case 6:
                medicos.get(rowIndex).setEspecialidade((String) valor);
                break;
            case 7:
                medicos.get(rowIndex).setDiaAtendimento((String) valor);
                break;
            case 8:
                medicos.get(rowIndex).setContato((String) valor);
                break;

            case 9:
                medicos.get(rowIndex).getEndereco().setUf((String) valor);
                break;
            case 10:
                medicos.get(rowIndex).getEndereco().setCep((String) valor);
                break;
            case 11:
                medicos.get(rowIndex).getEndereco().setCidade((String) valor);
                break;
            case 12:
                medicos.get(rowIndex).getEndereco().setBairro((String) valor);
                break;
            case 13:
                medicos.get(rowIndex).getEndereco().setRua((String) valor);
                break;
            case 14:
                medicos.get(rowIndex).getEndereco().setNumero(Integer.parseInt((String) valor));
                break;

        }
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

}
