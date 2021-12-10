package br.com.clinica.model.dao;

import br.com.clinica.connectionfactory.ConnectionFactory;
import br.com.clinica.model.objetos.Agendamento;
import br.com.clinica.model.objetos.Medico;
import br.com.clinica.model.objetos.Paciente;
import br.com.clinica.model.objetos.Pagamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {

    private Connection con;

    public PagamentoDAO() {
        con = new ConnectionFactory().getConnection();
    }

    public void realizarPagamento(Pagamento pagamento) {

        String sql = "Insert into pagamento (id_agendamento, valor, tipo_pagamento, parcelamento, bandeira) values (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, pagamento.getAgendamento().getId());
            ps.setDouble(2, pagamento.getValor());
            ps.setString(3, pagamento.getTipoPagamento());
            ps.setString(4, pagamento.getParcelamento());
            ps.setString(5, pagamento.getBandeira());
            ps.execute();
            ps.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Pagamento efetuado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao realizar pagamento!" + e);
        }
    }

    public Agendamento listarEspecialidaPorNomePaciente(String nome) {
        String sql = "select a.id, p.nome, m.especialidade from agendamento a inner join paciente p,  "
                + "medico m where m.id = a.id_medico and a.id_paciente = p.id and p.nome = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nome);
            ps.execute();

            ResultSet resultado = ps.getResultSet();

            ArrayList<Agendamento> agendamentos = new ArrayList();

            while (resultado.next()) {
                Agendamento agendamento = new Agendamento();
                Medico medico = new Medico();
                Paciente paciente = new Paciente();
                agendamento.setId(resultado.getLong("id"));
                paciente.setNome(resultado.getString("nome"));
                medico.setEspecialidade(resultado.getString("especialidade"));
                agendamento.setPaciente(paciente);
                agendamento.setMedico(medico);
                agendamentos.add(agendamento);
                return agendamento;
            }

        } catch (SQLException e) {
        }
        return null;
    }

    public Agendamento listarEspecialidaPorIDPaciente(long id) {
        String sql = "select a.id, m.especialidade from agendamento a inner join "
                + " medico m on( m.id = a.id_medico) where a.id_paciente = ?  ";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ps.execute();

            ResultSet resultado = ps.getResultSet();

            ArrayList<Agendamento> agendamentos = new ArrayList();

            while (resultado.next()) {
                Agendamento agendamento = new Agendamento();
                Medico medico = new Medico();
                Paciente paciente = new Paciente();
                agendamento.setId(resultado.getLong("id"));
                paciente.setNome(resultado.getString("nome"));
                medico.setEspecialidade(resultado.getString("especialidade"));
                agendamento.setPaciente(paciente);
                agendamento.setMedico(medico);
                agendamentos.add(agendamento);
                return agendamento;
            }

        } catch (SQLException e) {
        }
        return null;
    }

    public List<Pagamento> ListarPagamentos() {

        String sql = "select p.id, pa.nome, pa.cpf, p.valor, p.tipo_pagamento, p.parcelamento, p.bandeira from pagamento p inner join "
                + "agendamento a, paciente pa where p.id_agendamento = a.id and pa.id = a.id_paciente";

        try {
            Statement stm = con.createStatement();
            stm.execute(sql);

            ResultSet resultado = stm.getResultSet();
            List<Pagamento> pagamentos = new ArrayList<>();

            while (resultado.next()) {
                Paciente paciente = new Paciente();
                Pagamento pagamento = new Pagamento();
                Agendamento agendamento = new Agendamento();
                pagamento.setId(resultado.getLong("id"));
                paciente.setNome(resultado.getString("nome"));
                paciente.setCpf(resultado.getString("cpf"));
                pagamento.setValor(resultado.getDouble("valor"));
                pagamento.setTipoPagamento(resultado.getString("tipo_pagamento"));
                pagamento.setParcelamento(resultado.getString("parcelamento"));
                pagamento.setBandeira(resultado.getString("bandeira"));
                agendamento.setPaciente(paciente);
                pagamento.setAgendamento(agendamento);

                pagamentos.add(pagamento);
            }
            return pagamentos;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao listar vendas " + e);
        }
        return null;
    }

}
