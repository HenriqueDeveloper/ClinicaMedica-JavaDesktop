package br.com.clinica.model.tabela;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Henrique
 */
public class PintarTabela extends JTable {
    
   

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {

        Component component = super.prepareRenderer(renderer, row, column);
        component.setBackground(Color.WHITE);
        component.setForeground(Color.BLACK);

        String valor = String.valueOf(getValueAt(row, column));
        if (valor.equals("Pago")) {
            component.setBackground(Color.GREEN);
            component.setForeground(Color.BLACK);
           
            
            
        }

        return component;
    }
    
  

}
