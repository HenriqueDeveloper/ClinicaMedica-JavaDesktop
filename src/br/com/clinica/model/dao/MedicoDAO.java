package br.com.clinica.model.dao;

import br.com.clinica.connectionfactory.ConnectionFactory;
import br.com.clinica.interfaces.GenericInterfaceDAO;
import br.com.clinica.model.objetos.Cidade;
import br.com.clinica.model.objetos.Endereco;
import br.com.clinica.model.objetos.Login;
import br.com.clinica.model.objetos.Medico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MedicoDAO implements GenericInterfaceDAO<Medico> {

    private Connection con;

    public MedicoDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    @Override
    public void cadastrar(Medico medico) {
        try {
            String sql = "insert into medico (crm, especialidade, dia_Atendimento, contato, nome, idade, "
                    + "cpf, sexo, uf, cep, cidade, bairro, rua, numero) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, medico.getCrm());
            ps.setString(2, medico.getEspecialidade());
            ps.setString(3, medico.getDiaAtendimento());
            ps.setString(4, medico.getContato());
            ps.setString(5, medico.getNome());
            ps.setInt(6, medico.getIdade());
            ps.setString(7, medico.getCpf());
            ps.setString(8, medico.getSexo());
            ps.setString(9, medico.getEndereco().getUf());
            ps.setString(10, medico.getEndereco().getCep());
            ps.setString(11, medico.getEndereco().getCidade());
            ps.setString(12, medico.getEndereco().getBairro());
            ps.setString(13, medico.getEndereco().getRua());
            ps.setInt(14, medico.getEndereco().getNumero());
            ps.execute();
            con.close();

            JOptionPane.showMessageDialog(null, "Médico Cadastrado Com Sucesso!");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, " Exception -> " + e);

        }
    }

    @Override
    public ArrayList<Medico> listarTodos() {

        try {
            String sql = "select * from medico";

            Statement stm = con.createStatement();
            stm.execute(sql);

            ResultSet resultado = stm.getResultSet();

            ArrayList<Medico> medicos = new ArrayList();

            while (resultado.next()) {

                Medico medico = new Medico();
                Endereco endereco = new Endereco();
                Login login = new Login();

                medico.setId(resultado.getLong("id"));
                medico.setNome(resultado.getString("nome"));
                medico.setCpf(resultado.getString("cpf"));
                medico.setContato(resultado.getString("contato"));
                medico.setDiaAtendimento(resultado.getString("dia_Atendimento"));
                medico.setEspecialidade(resultado.getString("especialidade"));
                medico.setCrm(resultado.getString("crm"));
                medico.setIdade(resultado.getInt("idade"));
                medico.setSexo(resultado.getString("sexo"));
                endereco.setBairro(resultado.getString("bairro"));
                endereco.setCep(resultado.getString("cep"));
                endereco.setCidade(resultado.getString("cidade"));
                endereco.setNumero(resultado.getInt("numero"));
                endereco.setRua(resultado.getString("rua"));
                endereco.setUf(resultado.getString("uf"));

                medico.setEndereco(endereco);

                medicos.add(medico);

            }

            return medicos;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Exception -> " + e);
            return null;
        }
    }

    @Override
    public void atualizar(Medico medico) {

        try {
            String sql = "update medico set crm = ?, especialidade = ?, dia_Atendimento = ?, contato = ?, nome = ?, "
                    + "idade = ?, cpf = ?, sexo = ?, uf = ?, cep = ?, cidade = ?, bairro = ?, rua = ?, numero = ? where id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, medico.getCrm());
            ps.setString(2, medico.getEspecialidade());
            ps.setString(3, medico.getDiaAtendimento());
            ps.setString(4, medico.getContato());
            ps.setString(5, medico.getNome());
            ps.setInt(6, medico.getIdade());
            ps.setString(7, medico.getCpf());
            ps.setString(8, medico.getSexo());
            ps.setString(9, medico.getEndereco().getUf());
            ps.setString(10, medico.getEndereco().getCep());
            ps.setString(11, medico.getEndereco().getCidade());
            ps.setString(12, medico.getEndereco().getBairro());
            ps.setString(13, medico.getEndereco().getRua());
            ps.setInt(14, medico.getEndereco().getNumero());
            ps.setLong(15, medico.getId());
            ps.execute();
            con.close();

            JOptionPane.showMessageDialog(null, "Médico Atualizado Com Sucesso!");
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, " Exception -> " + e);

        }

    }

    @Override
    public void excluir(Medico medico) {
        try {
            String sql = "delete from medico where id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, medico.getId());
            ps.execute();
            con.close();

            JOptionPane.showMessageDialog(null, "Médico Excluído Com Sucesso!");
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, " Exception -> " + e);
        }
    }

    @Override
    public Medico listarPorId(long id) {
        try {
            String sql = "select * from medico where id = ? ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);

            ResultSet resultado = ps.executeQuery();

            ArrayList<Medico> medicos = new ArrayList();

            if (resultado.next()) {
                Medico medico = new Medico();
                Endereco endereco = new Endereco();

                medico.setNome(resultado.getString("nome"));
                medico.setCpf(resultado.getString("cpf"));
                medico.setContato(resultado.getString("contato"));
                medico.setDiaAtendimento(resultado.getString("dia_Atendimento"));
                medico.setEspecialidade(resultado.getString("especialidade"));
                medico.setCrm(resultado.getString("crm"));
                medico.setIdade(resultado.getInt("idade"));
                medico.setSexo(resultado.getString("sexo"));
                endereco.setBairro(resultado.getString("bairro"));
                endereco.setCep(resultado.getString("cep"));
                endereco.setCidade(resultado.getString("cidade"));
                endereco.setNumero(resultado.getInt("numero"));
                endereco.setRua(resultado.getString("rua"));
                endereco.setUf(resultado.getString("uf"));
                medico.setEndereco(endereco);

                medicos.add(medico);

                return medico;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Exception -> " + e);
            return null;
        }
        return null;

    }

    @Override
    public Medico listarPorCPF(String cpf) {
        try {
            String sql = "select * from medico where cpf = ? ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cpf);

            ResultSet resultado = ps.executeQuery();

            ArrayList<Medico> medicos = new ArrayList();

            if (resultado.next()) {
                Medico medico = new Medico();
                Endereco endereco = new Endereco();
                medico.setId(resultado.getLong("id"));
                medico.setNome(resultado.getString("nome"));
                medico.setCpf(resultado.getString("cpf"));
                medico.setContato(resultado.getString("contato"));
                medico.setDiaAtendimento(resultado.getString("dia_Atendimento"));
                medico.setEspecialidade(resultado.getString("especialidade"));
                medico.setCrm(resultado.getString("crm"));
                medico.setIdade(resultado.getInt("idade"));
                medico.setSexo(resultado.getString("sexo"));
                endereco.setBairro(resultado.getString("bairro"));
                endereco.setCep(resultado.getString("cep"));
                endereco.setCidade(resultado.getString("cidade"));
                endereco.setNumero(resultado.getInt("numero"));
                endereco.setRua(resultado.getString("rua"));
                endereco.setUf(resultado.getString("uf"));
                medico.setEndereco(endereco);

                medicos.add(medico);

                return medico;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Exception -> " + e);
            return null;
        }
        return null;

    }

    public Medico listarPorNome(String nome) {
        try {
            String sql = "select * from medico where nome = ? ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nome);

            ResultSet resultado = ps.executeQuery();

            ArrayList<Medico> medicos = new ArrayList();

            if (resultado.next()) {
                Medico medico = new Medico();
                Endereco endereco = new Endereco();
                medico.setId(resultado.getLong("id"));
                medico.setNome(resultado.getString("nome"));
                medico.setCpf(resultado.getString("cpf"));
                medico.setContato(resultado.getString("contato"));
                medico.setDiaAtendimento(resultado.getString("dia_Atendimento"));
                medico.setEspecialidade(resultado.getString("especialidade"));
                medico.setCrm(resultado.getString("crm"));
                medico.setIdade(resultado.getInt("idade"));
                medico.setSexo(resultado.getString("sexo"));
                endereco.setBairro(resultado.getString("bairro"));
                endereco.setCep(resultado.getString("cep"));
                endereco.setCidade(resultado.getString("cidade"));
                endereco.setNumero(resultado.getInt("numero"));
                endereco.setRua(resultado.getString("rua"));
                endereco.setUf(resultado.getString("uf"));
                medico.setEndereco(endereco);

                medicos.add(medico);

                return medico;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Exception -> " + e);
            return null;
        }
        return null;

    }

    @Override
    public ArrayList<Cidade> listarCidadesPorUF(String uf) {
        try {

            String sql = "select cidade from cidades where estado = ? ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, uf);

            ResultSet resultado = ps.executeQuery();

            ArrayList<Cidade> cidades = new ArrayList();

            while (resultado.next()) {

                Cidade cidade = new Cidade();
                cidade.setCidade(resultado.getString("cidade"));

                cidades.add(cidade);

            }

            return cidades;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, " Exception -> " + e);

            return null;
        }

    }

    public List<Medico> listarEspecialidadePorMedico(String nome) {
        try {

            String sql = "select nome from medico where especialidade = ? ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nome);

            ResultSet resultado = ps.executeQuery();

            List<Medico> medicos = new ArrayList<>();

            while (resultado.next()) {

                Medico medico = new Medico();
                medico.setNome(resultado.getString("nome"));
                medicos.add(medico);

            }
            return medicos;
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, " Exception -> " + e);

            return null;
        }
    }
}
