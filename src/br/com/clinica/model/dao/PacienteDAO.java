package br.com.clinica.model.dao;

import br.com.clinica.connectionfactory.ConnectionFactory;
import br.com.clinica.interfaces.GenericInterfaceDAO;
import br.com.clinica.model.objetos.Cidade;
import br.com.clinica.model.objetos.Endereco;
import br.com.clinica.model.objetos.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Statement;

public class PacienteDAO implements GenericInterfaceDAO<Paciente> {

    private Connection con;

    public PacienteDAO() {

        this.con = new ConnectionFactory().getConnection();
    }

    @Override
    public void cadastrar(Paciente paciente) {

        try {
            String sql = "insert into paciente ( nome, idade, cpf, sexo, uf, cep, cidade, bairro, rua, numero) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, paciente.getNome());
            ps.setInt(2, paciente.getIdade());
            ps.setString(3, paciente.getCpf());
            ps.setString(4, paciente.getSexo());
            ps.setString(5, paciente.getEndereco().getUf());
            ps.setString(6, paciente.getEndereco().getCep());
            ps.setString(7, paciente.getEndereco().getCidade());
            ps.setString(8, paciente.getEndereco().getBairro());
            ps.setString(9, paciente.getEndereco().getRua());
            ps.setInt(10, paciente.getEndereco().getNumero());

            ps.execute();
            con.close();

            JOptionPane.showMessageDialog(null, "Paciente cadastardo com sucesso!");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, " Exception -> " + e);

        }

    }

    @Override
    public ArrayList<Paciente> listarTodos() {

        try {
            String sql = "select * from paciente";

            Statement stm = con.createStatement();
            stm.execute(sql);

            ResultSet resultado = stm.getResultSet();

            ArrayList<Paciente> pacientes = new ArrayList();

            while (resultado.next()) {
                Endereco endereco = new Endereco();
                Paciente paciente = new Paciente();
                paciente.setId(resultado.getLong("ID"));
                paciente.setNome(resultado.getString("nome"));
                paciente.setIdade(resultado.getInt("idade"));
                paciente.setSexo(resultado.getString("sexo"));
                paciente.setCpf(resultado.getString("cpf"));
                endereco.setUf(resultado.getString("uf"));
                endereco.setCep(resultado.getString("cep"));
                endereco.setCidade(resultado.getString("cidade"));
                endereco.setBairro(resultado.getString("bairro"));
                endereco.setRua(resultado.getString("rua"));
                endereco.setNumero(resultado.getInt("numero"));
                paciente.setEndereco(endereco);

                pacientes.add(paciente);

            }

            return pacientes;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Exception -> " + e);
            return null;
        }

    }

    @Override
    public Paciente listarPorCPF(String cpf) {
        try {

            String sql = "select * from paciente where cpf = ? ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cpf);

            ResultSet resultado = ps.executeQuery();

            ArrayList<Paciente> pacientes = new ArrayList();

            while (resultado.next()) {
                Endereco endereco = new Endereco();
                Paciente paciente = new Paciente();

                paciente.setId(resultado.getLong("id"));
                paciente.setNome(resultado.getString("nome"));
                paciente.setIdade(resultado.getInt("idade"));
                paciente.setSexo(resultado.getString("sexo"));
                paciente.setCpf(resultado.getString("cpf"));
                endereco.setUf(resultado.getString("uf"));
                endereco.setCep(resultado.getString("cep"));
                endereco.setCidade(resultado.getString("cidade"));
                endereco.setBairro(resultado.getString("bairro"));
                endereco.setRua(resultado.getString("rua"));
                endereco.setNumero(resultado.getInt("numero"));
                paciente.setEndereco(endereco);

                pacientes.add(paciente);
                return paciente;
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, " Exception -> " + e);

            return null;
        }
        return null;

    }

    public Paciente listarPorNome(String nome) {
        try {

            String sql = "select * from paciente where nome = ? ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nome);

            ResultSet resultado = ps.executeQuery();

            ArrayList<Paciente> pacientes = new ArrayList();

            while (resultado.next()) {
                Endereco endereco = new Endereco();
                Paciente paciente = new Paciente();
                paciente.setId(resultado.getLong("id"));
                paciente.setNome(resultado.getString("nome"));
                paciente.setIdade(resultado.getInt("idade"));
                paciente.setSexo(resultado.getString("sexo"));
                paciente.setCpf(resultado.getString("cpf"));
                endereco.setUf(resultado.getString("uf"));
                endereco.setCep(resultado.getString("cep"));
                endereco.setCidade(resultado.getString("cidade"));
                endereco.setBairro(resultado.getString("bairro"));
                endereco.setRua(resultado.getString("rua"));
                endereco.setNumero(resultado.getInt("numero"));
                paciente.setEndereco(endereco);

                pacientes.add(paciente);
                return paciente;
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, " Exception -> " + e);

            return null;
        }
        return null;

    }

    @Override
    public Paciente listarPorId(long id) {
        try {

            String sql = "select * from paciente where id = ? ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);

            ResultSet resultado = ps.executeQuery();

            ArrayList<Paciente> pacientes = new ArrayList();

            while (resultado.next()) {
                Endereco endereco = new Endereco();
                Paciente paciente = new Paciente();
                paciente.setId(resultado.getLong("id"));
                paciente.setNome(resultado.getString("nome"));
                paciente.setIdade(resultado.getInt("idade"));
                paciente.setSexo(resultado.getString("sexo"));
                paciente.setCpf(resultado.getString("cpf"));
                endereco.setUf(resultado.getString("uf"));
                endereco.setCep(resultado.getString("cep"));
                endereco.setCidade(resultado.getString("cidade"));
                endereco.setBairro(resultado.getString("bairro"));
                endereco.setRua(resultado.getString("rua"));
                endereco.setNumero(resultado.getInt("numero"));
                paciente.setEndereco(endereco);

                pacientes.add(paciente);

                return paciente;

            }

        } catch (SQLException e) {

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

    @Override
    public void atualizar(Paciente paciente) {
        try {

            String sql = "update paciente set nome = ?, idade = ?, sexo = ?, cpf = ?, "
                    + "uf = ?, cep = ?, cidade = ?, bairro = ?, rua = ?, numero = ? where id = ? ";

            PreparedStatement ps = con.prepareStatement(sql);
            Endereco endereco = paciente.getEndereco();
            ps.setString(1, paciente.getNome());
            ps.setInt(2, paciente.getIdade());
            ps.setString(3, paciente.getSexo());
            ps.setString(4, paciente.getCpf());
            ps.setString(5, endereco.getUf());
            ps.setString(6, endereco.getCep());
            ps.setString(7, endereco.getCidade());
            ps.setString(8, endereco.getBairro());
            ps.setString(9, endereco.getRua());
            ps.setInt(10, endereco.getNumero());
            ps.setLong(11, paciente.getId());
            ps.execute();
            con.close();

            JOptionPane.showMessageDialog(null, "Paciente atualizado com sucesso!");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, " Exception -> " + e);
            System.out.println(e);
        }
    }

    @Override
    public void excluir(Paciente paciente) {

        try {

            String sql = "delete from paciente where id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, paciente.getId());
            ps.execute();
            con.close();

            JOptionPane.showMessageDialog(null, "Paciente excluido com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Exception -> " + e);
        }

    }

}
