package br.com.clinica.model.tabela;

import br.com.clinica.model.objetos.Agendamento;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

public class AgendamentoModeloTabela extends AbstractTableModel {

    
    private List<Agendamento> consultas;
    private String[] nomeColuna = {"ID", "Nome Paciente", "Data", "Especialidade", "Pagamento"};

    public AgendamentoModeloTabela(List<Agendamento> consultas) {
        this.consultas = consultas;
    }

    
    @Override
    public int getRowCount() {
        return this.consultas.size();
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return this.consultas.get(rowIndex).getId();
            case 1:
                return this.consultas.get(rowIndex).getPaciente().getNome();
            case 2:
                return this.consultas.get(rowIndex).getData();
            case 3:
                return this.consultas.get(rowIndex).getMedico().getEspecialidade();
            case 4:
                return String.valueOf(this.consultas.get(rowIndex).getPagamento());
      

        }
        return null;

    }

    @Override
    public void setValueAt(Object valor, int rowIndex, int columnIndex) {

        switch (columnIndex) {

            case 3:
                this.consultas.get(rowIndex).setDiaConsulta((String) valor);
                break;
            case 4: {
                try {
                    this.consultas.get(rowIndex).setData(new SimpleDateFormat("dd/MM/yyyy").parse((String) valor));
                } catch (ParseException ex) {
                    Logger.getLogger(AgendamentoModeloTabela.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            break;

            case 6:
                this.consultas.get(rowIndex).setDescricao((String) valor);
                break;

        }
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

}
