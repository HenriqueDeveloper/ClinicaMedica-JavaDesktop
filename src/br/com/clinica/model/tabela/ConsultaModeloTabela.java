package br.com.clinica.model.tabela;

import br.com.clinica.model.objetos.Consulta;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ConsultaModeloTabela extends AbstractTableModel {

    private List<Consulta> consultas;
    private String[] nomeColuna = {"ID", "Nome Paciente", "Prescrição"};

    public ConsultaModeloTabela(List<Consulta> consultas) {
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
    public Object getValueAt(int linha, int coluna) {

        switch (coluna) {
            case 0:
                return this.consultas.get(linha).getId();
            case 1:
                return this.consultas.get(linha).getAgendamento().getPaciente().getNome();
            case 2:
                return this.consultas.get(linha).getPrescricao();

        }
        return null;
    }

}
