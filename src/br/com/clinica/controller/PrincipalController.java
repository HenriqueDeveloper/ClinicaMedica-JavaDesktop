package br.com.clinica.controller;

import br.com.clinica.view.telas.TelaAgendamento;
import br.com.clinica.view.telas.TelaEmail;
import br.com.clinica.view.telas.TelaFuncionario;
import br.com.clinica.view.telas.TelaLogin;
import br.com.clinica.view.telas.TelaMedico;
import br.com.clinica.view.telas.TelaPaciente;
import br.com.clinica.view.telas.TelaConsulta;
import br.com.clinica.view.telas.TelaPagamento;
import br.com.clinica.view.telas.TelaPrincipal;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class PrincipalController {

    private TelaPrincipal view;

    public PrincipalController(TelaPrincipal view) {
        this.view = view;
    }

    public void btnPaciente() {

        TelaPaciente telapaciente = new TelaPaciente();

        this.view.getjDesktopPane1().add(telapaciente);
        telapaciente.setVisible(true);
        bloquearBotoes(true);

        telapaciente.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                view.getBtnMedico().setEnabled(false);
                view.getBtnAgendamento().setEnabled(true);
                view.getBtnFuncionario().setEnabled(false);
                view.getBtnPaciente().setEnabled(true);
                view.getBtnConsulta().setEnabled(false);
                view.getBtnSuporte().setEnabled(true);
                view.getBtnDeslogar().setEnabled(true);
                view.getBtnPagamento().setEnabled(true);

                if (view.getLabelNome().getText().equals("Administrador")) {
                    view.getBtnMedico().setEnabled(true);
                    view.getBtnAgendamento().setEnabled(true);
                    view.getBtnFuncionario().setEnabled(true);
                    view.getBtnPaciente().setEnabled(true);
                    view.getBtnConsulta().setEnabled(true);
                    view.getBtnDeslogar().setEnabled(true);
                    view.getBtnPagamento().setEnabled(true);

                }
            }

        });

    }

    public void btnMedico() {
        TelaMedico telamedico = new TelaMedico();
        this.view.getjDesktopPane1().add(telamedico);
        telamedico.setVisible(true);
        bloquearBotoes(true);

        telamedico.addInternalFrameListener(new InternalFrameAdapter() {

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                view.getBtnPaciente().setEnabled(false);
                view.getBtnConsulta().setEnabled(false);
                view.getBtnAgendamento().setEnabled(false);
                view.getBtnPagamento().setEnabled(false);

                if (view.getLabelNome().getText().equals("Administrador")) {
                    view.getBtnMedico().setEnabled(true);
                    view.getBtnAgendamento().setEnabled(true);
                    view.getBtnFuncionario().setEnabled(true);
                    view.getBtnPaciente().setEnabled(true);
                    view.getBtnConsulta().setEnabled(true);
                    view.getBtnSuporte().setEnabled(true);
                    view.getBtnDeslogar().setEnabled(true);
                    view.getBtnPagamento().setEnabled(true);

                }

            }

        });
    }

    public void btnFuncionario() {

        TelaFuncionario tela = new TelaFuncionario();
        this.view.getjDesktopPane1().add(tela);
        tela.setVisible(true);
        bloquearBotoes(true);

        tela.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                view.getBtnMedico().setEnabled(true);
                view.getBtnAgendamento().setEnabled(false);
                view.getBtnFuncionario().setEnabled(true);
                view.getBtnPaciente().setEnabled(false);
                view.getBtnConsulta().setEnabled(false);
                view.getBtnDeslogar().setEnabled(true);
                view.getBtnPagamento().setEnabled(true);

                if (view.getLabelNome().getText().equals("Administrador")) {
                    view.getBtnMedico().setEnabled(true);
                    view.getBtnAgendamento().setEnabled(true);
                    view.getBtnFuncionario().setEnabled(true);
                    view.getBtnPaciente().setEnabled(true);
                    view.getBtnConsulta().setEnabled(true);
                    view.getBtnSuporte().setEnabled(true);
                    view.getBtnDeslogar().setEnabled(true);
                    view.getBtnPagamento().setEnabled(true);

                }

            }

        });

    }

    public void btnAgendamento() {
        TelaAgendamento tela = new TelaAgendamento();
        this.view.getjDesktopPane1().add(tela);
        tela.setVisible(true);
        bloquearBotoes(true);

        tela.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {

                view.getBtnMedico().setEnabled(false);
                view.getBtnAgendamento().setEnabled(true);
                view.getBtnFuncionario().setEnabled(false);
                view.getBtnPaciente().setEnabled(true);
                view.getBtnConsulta().setEnabled(false);
                view.getBtnSuporte().setEnabled(true);
                view.getBtnDeslogar().setEnabled(true);
                view.getBtnPagamento().setEnabled(true);

                if (view.getLabelNome().getText().equals("Administrador")) {
                    view.getBtnMedico().setEnabled(true);
                    view.getBtnAgendamento().setEnabled(true);
                    view.getBtnFuncionario().setEnabled(true);
                    view.getBtnPaciente().setEnabled(true);
                    view.getBtnConsulta().setEnabled(true);
                    view.getBtnDeslogar().setEnabled(true);
                    view.getBtnPagamento().setEnabled(true);

                }
            }

        });

    }

    public void btnConsulta() {
        TelaConsulta tela = new TelaConsulta();
        this.view.getjDesktopPane1().add(tela);
        tela.setVisible(true);
        bloquearBotoes(true);
        tela.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                view.getBtnPaciente().setEnabled(false);
                view.getBtnMedico().setEnabled(false);
                view.getBtnConsulta().setEnabled(true);
                view.getBtnFuncionario().setEnabled(false);
                view.getBtnAgendamento().setEnabled(false);
                view.getBtnSuporte().setEnabled(true);
                view.getBtnDeslogar().setEnabled(true);
                view.getBtnPagamento().setEnabled(false);

                if (view.getLabelNome().getText().equals("Administrador")) {
                    view.getBtnMedico().setEnabled(true);
                    view.getBtnAgendamento().setEnabled(true);
                    view.getBtnFuncionario().setEnabled(true);
                    view.getBtnPaciente().setEnabled(true);
                    view.getBtnConsulta().setEnabled(true);
                    view.getBtnDeslogar().setEnabled(true);
                    view.getBtnPagamento().setEnabled(true);

                }
            }

        });
    }

    public void btnEmail() {
        TelaEmail tela = new TelaEmail();
        this.view.getjDesktopPane1().add(tela);
        tela.setVisible(true);
        bloquearBotoes(true);

        tela.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {

                if (view.getLabelNome().getText().equals("Funcionário")) {
                    view.getBtnPaciente().setEnabled(true);
                    view.getBtnMedico().setEnabled(false);
                    view.getBtnConsulta().setEnabled(false);
                    view.getBtnFuncionario().setEnabled(false);
                    view.getBtnAgendamento().setEnabled(true);
                    view.getBtnSuporte().setEnabled(true);
                    view.getBtnDeslogar().setEnabled(true);
                    view.getBtnPagamento().setEnabled(true);
                    view.getBtnPagamento().setEnabled(true);
                } else if (view.getLabelNome().getText().equals("Médico")) {
                    view.getBtnPaciente().setEnabled(false);
                    view.getBtnMedico().setEnabled(false);
                    view.getBtnConsulta().setEnabled(true);
                    view.getBtnFuncionario().setEnabled(false);
                    view.getBtnAgendamento().setEnabled(false);
                    view.getBtnSuporte().setEnabled(true);
                    view.getBtnDeslogar().setEnabled(true);
                    view.getBtnPagamento().setEnabled(false);

                } else {
                    view.getBtnMedico().setEnabled(true);
                    view.getBtnAgendamento().setEnabled(true);
                    view.getBtnFuncionario().setEnabled(true);
                    view.getBtnPaciente().setEnabled(true);
                    view.getBtnConsulta().setEnabled(true);
                    view.getBtnSuporte().setEnabled(true);
                    view.getBtnDeslogar().setEnabled(true);
                    view.getBtnPagamento().setEnabled(true);

                }
            }

        });

    }

    public void btnPagamento() {
        TelaPagamento tela = new TelaPagamento();
        this.view.getjDesktopPane1().add(tela);
        tela.setVisible(true);
        bloquearBotoes(true);

        tela.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {

                if (view.getLabelNome().getText().equals("Funcionário")) {
                    view.getBtnPaciente().setEnabled(true);
                    view.getBtnMedico().setEnabled(false);
                    view.getBtnConsulta().setEnabled(false);
                    view.getBtnFuncionario().setEnabled(false);
                    view.getBtnAgendamento().setEnabled(true);
                    view.getBtnSuporte().setEnabled(true);
                    view.getBtnDeslogar().setEnabled(true);
                    view.getBtnPagamento().setEnabled(false);
                    view.getBtnPagamento().setEnabled(true);
                } else if (view.getLabelNome().getText().equals("Médico")) {
                    view.getBtnPaciente().setEnabled(false);
                    view.getBtnMedico().setEnabled(false);
                    view.getBtnConsulta().setEnabled(false);
                    view.getBtnFuncionario().setEnabled(false);
                    view.getBtnAgendamento().setEnabled(true);
                    view.getBtnSuporte().setEnabled(true);
                    view.getBtnDeslogar().setEnabled(true);
                    view.getBtnPagamento().setEnabled(false);

                } else {
                    view.getBtnMedico().setEnabled(true);
                    view.getBtnAgendamento().setEnabled(true);
                    view.getBtnFuncionario().setEnabled(true);
                    view.getBtnPaciente().setEnabled(true);
                    view.getBtnConsulta().setEnabled(true);
                    view.getBtnSuporte().setEnabled(true);
                    view.getBtnDeslogar().setEnabled(true);
                    view.getBtnPagamento().setEnabled(true);

                }
            }

        });

    }

    public void btnDeslogar() {

        switch (JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deslogar ? ", "Deslogar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {

            case 0:
                this.view.setVisible(false);
                TelaLogin tela = new TelaLogin();
                tela.setVisible(true);
                break;

        }

    }

    public void bloquearBotoes(boolean opcao) {
        this.view.getBtnPaciente().setEnabled(!opcao);
        this.view.getBtnMedico().setEnabled(!opcao);
        this.view.getBtnConsulta().setEnabled(!opcao);
        this.view.getBtnFuncionario().setEnabled(!opcao);
        this.view.getBtnAgendamento().setEnabled(!opcao);
        this.view.getBtnSuporte().setEnabled(!opcao);
        this.view.getBtnDeslogar().setEnabled(!opcao);
        this.view.getBtnPagamento().setEnabled(!opcao);
    }

}
