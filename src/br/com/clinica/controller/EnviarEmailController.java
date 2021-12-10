package br.com.clinica.controller;

import br.com.clinica.view.telas.TelaEmail;
import javax.swing.JOptionPane;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

public class EnviarEmailController {

    private TelaEmail view;

    public EnviarEmailController(TelaEmail view) {
        this.view = view;

    }

    public void enviarEmail() {
        String senha = String.valueOf(this.view.getTfSenha().getPassword());

        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(this.view.getTfEmail().getText(), senha));
        email.setSSLOnConnect(true);

        try {
            email.setFrom(this.view.getTfEmail().getText());
            email.setSubject(this.view.getTfAssunto().getText());
            email.setMsg(this.view.getTfTexto().getText());
            email.addTo("henrique.henrique.soares3@gmail.com");
            email.send();

            JOptionPane.showMessageDialog(null, "E-mail enviado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }

    }
}
