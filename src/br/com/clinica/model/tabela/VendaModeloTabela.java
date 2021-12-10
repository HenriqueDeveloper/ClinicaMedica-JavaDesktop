/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.clinica.model.tabela;

import br.com.clinica.model.objetos.Pagamento;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Henrique
 */
public class VendaModeloTabela extends AbstractTableModel {

    private List<Pagamento> pagamentos;
    private String[] nomeColunas = {"ID", "Paciente", "CPF", "Valor", "Pagamento", "Parcelas", "Bandeira"};

    public VendaModeloTabela(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;

    }

    @Override
    public int getRowCount() {
        return pagamentos.size();
    }

    @Override
    public int getColumnCount() {
        return nomeColunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return nomeColunas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {
            case 0:
                return this.pagamentos.get(rowIndex).getId();
            case 1:
                return this.pagamentos.get(rowIndex).getAgendamento().getPaciente().getNome();
            case 2:
                return this.pagamentos.get(rowIndex).getAgendamento().getPaciente().getCpf();
            case 3:
                return this.pagamentos.get(rowIndex).getValor();
            case 4:
                return this.pagamentos.get(rowIndex).getTipoPagamento();
            case 5:
                return this.pagamentos.get(rowIndex).getParcelamento();
            case 6:
                return this.pagamentos.get(rowIndex).getBandeira();

        }
        return null;
    }

}
