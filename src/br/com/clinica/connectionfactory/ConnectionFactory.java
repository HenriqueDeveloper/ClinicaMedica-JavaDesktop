package br.com.clinica.connectionfactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConnectionFactory {

    public Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1/clinica", "root", "root");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Erro ao conectar ao banco de dados! " + e);
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
            ex.printStackTrace();
            return null;
        }

    }

}
