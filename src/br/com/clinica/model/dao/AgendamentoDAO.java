package br.com.clinica.model.dao;

import br.com.clinica.connectionfactory.ConnectionFactory;
import br.com.clinica.interfaces.GenericInterfaceDAO;
import br.com.clinica.model.objetos.Cidade;
import br.com.clinica.model.objetos.Medico;
import br.com.clinica.model.objetos.Agendamento;
import br.com.clinica.model.objetos.Paciente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class AgendamentoDAO implements GenericInterfaceDAO<Agendamento> {

    private Connection con;

    public AgendamentoDAO() {

        this.con = new ConnectionFactory().getConnection();
    }

    @Override
    public void cadastrar(Agendamento consulta) {

        try {
            String sql = "insert into agendamento (id_paciente, id_medico, dia, data_consulta, horario, descricao, situacao_pagamento) "
                    + "values (?, ?, ?, ?, ?, ?, ?) ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, consulta.getPaciente().getId());
            ps.setLong(2, consulta.getMedico().getId());
            ps.setString(3, consulta.getDiaConsulta());
            ps.setDate(4, new Date(consulta.getData().getTime()));
            ps.setString(5, consulta.getHorario());
            ps.setString(6, consulta.getDescricao());
            ps.setString(7, "Aguardando");

            ps.execute();
            con.close();

            JOptionPane.showMessageDialog(null, "Agendamento efetuado com sucesso!");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, " Exception -> " + e);
            e.printStackTrace();

        }

    }

    @Override
    public ArrayList<Agendamento> listarTodos() {

        try {
            String sql = "select a.id,p.nome as nome_paciente,m.id as idMedico, m.nome as nome_medico, m.especialidade as especialidade, a.dia, a.data_consulta,"
                    + " a.descricao, a.situacao_pagamento  from agendamento a inner join medico m , paciente p where a.id_medico = m.id and a.id_Paciente = p.id;";

            Statement stm = con.createStatement();
            stm.execute(sql);

            ResultSet resultado = stm.getResultSet();

            ArrayList<Agendamento> consultas = new ArrayList();

            while (resultado.next()) {

                Agendamento consulta = new Agendamento();
                Paciente paciente = new Paciente();
                Medico medico = new Medico();
                consulta.setId(resultado.getLong("id"));
                medico.setId(resultado.getLong("idMedico"));
                paciente.setNome(resultado.getString("nome_paciente"));
                medico.setNome(resultado.getString("nome_medico"));
                medico.setEspecialidade(resultado.getString("especialidade"));
                consulta.setDiaConsulta(resultado.getString("dia"));
                consulta.setData(resultado.getDate("data_consulta"));
                consulta.setDescricao(resultado.getString("descricao"));
                consulta.setPagamento(resultado.getString("situacao_pagamento"));
                consulta.setPaciente(paciente);
                consulta.getPaciente().setNome(paciente.getNome());
                consulta.setMedico(medico);
                consulta.getMedico().setNome(medico.getNome());

                consultas.add(consulta);

            }

            return consultas;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Exception -> " + e);
            return null;
        }

    }

    @Override
    public void atualizar(Agendamento consulta) {
        try {
            String sql = "update agendamento set dia = ?, data_consulta = ?, horario = ?,  descricao= ? where id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, consulta.getDiaConsulta());
            ps.setDate(2, new Date(consulta.getData().getTime()));
            ps.setString(3, consulta.getHorario());
            ps.setString(4, consulta.getDescricao());
            ps.setLong(5, consulta.getId());
            ps.execute();
            con.close();

            JOptionPane.showMessageDialog(null, "Agendamento atualizado com sucesso!");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, " Exception -> " + e);
        }
    }

    public void atualizarResultadoPagamento(Agendamento agendamento) {
        try {
            String sql = "update agendamento set situacao_pagamento = ? where id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "Pago");
            ps.setLong(2, agendamento.getId());
            ps.execute();
            con.close();

        } catch (SQLException e) {
             e.printStackTrace();
            JOptionPane.showMessageDialog(null, " Exception -> " + e);
        }
    }

    @Override
    public void excluir(Agendamento consulta) {
        try {
            String sql = "delete from agendamento where id = ? ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, consulta.getId());
            ps.execute();
            con.close();

            JOptionPane.showMessageDialog(null, "Agendamento excluído com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Exception -> " + e);
        }
    }

    @Override
    public ArrayList<Cidade> listarCidadesPorUF(String uf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Agendamento listarPorId(long id) {

        try {
            String sql = "select a.id, p.nome as nome_paciente, p.cpf as cpf_paciente, p.idade,p.sexo, m.nome as nome_medico, m.especialidade as especialidade,  a.dia, a.data_consulta, a.horario,a.descricao from agendamento a inner join"
                    + " paciente p, medico m where a.id_medico = m.id and a.id_paciente = p.id and a.id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ps.execute();

            ResultSet resultado = ps.getResultSet();

            while (resultado.next()) {
                Agendamento consulta = new Agendamento();
                Paciente paciente = new Paciente();
                Medico medico = new Medico();
                consulta.setId(resultado.getLong("id"));
                paciente.setNome(resultado.getString("nome_paciente"));
                paciente.setCpf(resultado.getString("cpf_paciente"));
                paciente.setIdade(resultado.getInt("idade"));
                paciente.setSexo(resultado.getString("sexo"));
                medico.setNome(resultado.getString("nome_medico"));
                medico.setEspecialidade(resultado.getString("especialidade"));
                consulta.setDiaConsulta(resultado.getString("dia"));
                consulta.setData(resultado.getDate("data_consulta"));
                consulta.setHorario(resultado.getString("horario"));
                consulta.setDescricao(resultado.getString("descricao"));
                consulta.setPaciente(paciente);
                consulta.setMedico(medico);

                return consulta;

            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, " Exception -> " + e);
        }
        return null;
    }

    @Override
    public Agendamento listarPorCPF(String cpf) {

        try {
            String sql = "select a.id, p.nome as nome_paciente, p.cpf as cpf_paciente, p.idade,p.sexo, "
                    + "m.nome as nome_medico, m.especialidade as especialidade,  a.dia, a.data_consulta, "
                    + "a.descricao, a.situacao_pagamento from pagamento pa  inner join paciente p, medico "
                    + "m, agendamento a where a.id_medico = m.id and a.id_paciente = p.id and pa.id_agendamento ="
                    + " a.id and p.cpf = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cpf);
            ps.execute();

            ResultSet resultado = ps.getResultSet();

            while (resultado.next()) {
                Agendamento consulta = new Agendamento();
                Paciente paciente = new Paciente();
                Medico medico = new Medico();
                consulta.setId(resultado.getInt("id"));
                paciente.setNome(resultado.getString("nome_paciente"));
                paciente.setCpf(resultado.getString("cpf_paciente"));
                paciente.setIdade(resultado.getInt("idade"));
                paciente.setSexo(resultado.getString("sexo"));
                medico.setNome(resultado.getString("nome_medico"));
                medico.setEspecialidade(resultado.getString("especialidade"));
                consulta.setDiaConsulta(resultado.getString("dia"));
                consulta.setData(resultado.getDate("data_consulta"));
                consulta.setDescricao(resultado.getString("descricao"));
                consulta.setPagamento(resultado.getString("situacao_pagamento"));
                consulta.setPaciente(paciente);
                consulta.setMedico(medico);

                return consulta;

            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, " Exception -> " + e);
        }
        return null;
    }

}
