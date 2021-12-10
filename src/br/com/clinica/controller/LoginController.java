package br.com.clinica.controller;
import br.com.clinica.model.objetos.Login;


import br.com.clinica.view.telas.TelaLogin;
import br.com.clinica.view.telas.TelaPrincipal;
import static java.awt.Frame.MAXIMIZED_BOTH;
import javax.swing.JOptionPane;

public class LoginController {

    private TelaLogin view;

    public LoginController(TelaLogin view) {
        this.view = view;

    }

    public void autentificarLogin(Login login) {
        String senha = String.valueOf(this.view.getTfSenha().getPassword());
        login = new Login(this.view.getTfEmail().getText(), senha);

        TelaPrincipal tela = new TelaPrincipal();

        
        if (this.view.getTfEmail().getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Campo E-mail vázio!");
        } else if (senha.equals("")) {
            JOptionPane.showMessageDialog(null, "Campo Senha vázio!");
        }

        if (!this.view.getrMedico().isSelected() && !this.view.getrFuncionario().isSelected()
                && !this.view.getrAdministrador().isSelected()) {

            JOptionPane.showMessageDialog(null, "Selecione se o usuário é médico, funcionário ou adm.");

        } else if (login.getEmail().equals("medico@hotmail.com")
                && login.getSenha().equals("123") && this.view.getrMedico().isSelected()) {
            tela.setExtendedState(MAXIMIZED_BOTH);
            this.view.setVisible(false);
            tela.setVisible(true);
            tela.getBtnFuncionario().setEnabled(false);
            tela.getBtnMedico().setEnabled(false);
            tela.getBtnPaciente().setEnabled(false);
            tela.getBtnConsulta().setEnabled(true);
            tela.getBtnPagamento().setEnabled(false);
            tela.getBtnAgendamento().setEnabled(false);
            tela.getLabelNome().setText("Médico");
        } else if (login.getEmail().equals("funcionario@hotmail.com")
                && login.getSenha().equals("123") && this.view.getrFuncionario().isSelected()) {
            tela.setExtendedState(MAXIMIZED_BOTH);
            this.view.setVisible(false);
            tela.setVisible(true);
            tela.getBtnAgendamento().setEnabled(true);
            tela.getBtnPaciente().setEnabled(true);
            tela.getBtnFuncionario().setEnabled(false);
            tela.getBtnMedico().setEnabled(false);
            tela.getBtnConsulta().setEnabled(false);
            tela.getLabelNome().setText("Funcionário");

        } else if (login.getEmail().equals("adm@hotmail.com")
                && login.getSenha().equals("123") && this.view.getrAdministrador().isSelected()) {
            tela.setExtendedState(MAXIMIZED_BOTH);
            this.view.setVisible(false);
            tela.setVisible(true);
            tela.getLabelNome().setText("Administrador");

        } else {
            JOptionPane.showMessageDialog(null, "Login inválido!");
        }

    }
}
