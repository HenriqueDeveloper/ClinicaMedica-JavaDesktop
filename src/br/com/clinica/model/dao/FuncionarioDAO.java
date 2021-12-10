package br.com.clinica.model.dao;

import br.com.clinica.connectionfactory.ConnectionFactory;
import br.com.clinica.interfaces.GenericInterfaceDAO;
import br.com.clinica.model.objetos.Cidade;
import br.com.clinica.model.objetos.Endereco;
import br.com.clinica.model.objetos.Funcionario;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Henrique
 */
public class FuncionarioDAO implements GenericInterfaceDAO<Funcionario> {

    private Connection con;

    public FuncionarioDAO() {

        con = new ConnectionFactory().getConnection();

    }

    @Override
    public void cadastrar(Funcionario funcionario) {

        try {
            String sql = "insert into funcionario ( nome, idade, cpf, sexo, uf, cep, cidade, bairro, rua, numero) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, funcionario.getNome());
            ps.setInt(2, funcionario.getIdade());
            ps.setString(3, funcionario.getCpf());
            ps.setString(4, funcionario.getSexo());
            ps.setString(5, funcionario.getEndereco().getUf());
            ps.setString(6, funcionario.getEndereco().getCep());
            ps.setString(7, funcionario.getEndereco().getCidade());
            ps.setString(8, funcionario.getEndereco().getBairro());
            ps.setString(9, funcionario.getEndereco().getRua());
            ps.setInt(10, funcionario.getEndereco().getNumero());

            ps.execute();
            con.close();

            JOptionPane.showMessageDialog(null, "Funcionário cadastardo com sucesso!");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, " Exception -> " + e);

        }

    }

    @Override
    public ArrayList<Funcionario> listarTodos() {
        try {
            String sql = "select * from funcionario";

            Statement stm = con.createStatement();
            stm.execute(sql);

            ResultSet resultado = stm.getResultSet();

            ArrayList<Funcionario> funcionarios = new ArrayList();

            while (resultado.next()) {

                Endereco endereco = new Endereco();
                Funcionario funcionario = new Funcionario();

                funcionario.setId(resultado.getLong("ID"));
                funcionario.setNome(resultado.getString("nome"));
                funcionario.setIdade(resultado.getInt("idade"));
                funcionario.setSexo(resultado.getString("sexo"));
                funcionario.setCpf(resultado.getString("cpf"));
                endereco.setUf(resultado.getString("uf"));
                endereco.setCep(resultado.getString("cep"));
                endereco.setCidade(resultado.getString("cidade"));
                endereco.setBairro(resultado.getString("bairro"));
                endereco.setRua(resultado.getString("rua"));
                endereco.setNumero(resultado.getInt("numero"));
                funcionario.setEndereco(endereco);

                funcionarios.add(funcionario);

            }

            return funcionarios;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Exception -> " + e);
            return null;
        }

    }

    @Override
    public void atualizar(Funcionario funcionario) {
        try {

            String sql = "update funcionario set nome = ?, idade = ?, sexo = ?, cpf = ?, "
                    + "uf = ?, cep = ?, cidade = ?, bairro = ?, rua = ?, numero = ? where id = ? ";

            PreparedStatement ps = con.prepareStatement(sql);
            Endereco endereco = funcionario.getEndereco();
            ps.setString(1, funcionario.getNome());
            ps.setInt(2, funcionario.getIdade());
            ps.setString(3, funcionario.getSexo());
            ps.setString(4, funcionario.getCpf());
            ps.setString(5, endereco.getUf());
            ps.setString(6, endereco.getCep());
            ps.setString(7, endereco.getCidade());
            ps.setString(8, endereco.getBairro());
            ps.setString(9, endereco.getRua());
            ps.setInt(10, endereco.getNumero());
            ps.setLong(11, funcionario.getId());
            ps.execute();
            con.close();

            JOptionPane.showMessageDialog(null, "Funcionário atualizado com sucesso!");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, " Exception -> " + e);
            System.out.println(e);
        }
    }

    @Override
    public void excluir(Funcionario funcionario) {
        try {

            String sql = "delete from funcionario where id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, funcionario.getId());
            ps.execute();
            con.close();

            JOptionPane.showMessageDialog(null, "Funcionário excluido com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Exception -> " + e);
        }

    }

    @Override
    public Funcionario listarPorId(long id) {
        try {

            String sql = "select * from funcionario where id = ? ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);

            ResultSet resultado = ps.executeQuery();

            ArrayList<Funcionario> funcionarios = new ArrayList();

            while (resultado.next()) {
                Endereco endereco = new Endereco();
                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultado.getLong("id"));
                funcionario.setNome(resultado.getString("nome"));
                funcionario.setIdade(resultado.getInt("idade"));
                funcionario.setSexo(resultado.getString("sexo"));
                funcionario.setCpf(resultado.getString("cpf"));
                endereco.setUf(resultado.getString("uf"));
                endereco.setCep(resultado.getString("cep"));
                endereco.setCidade(resultado.getString("cidade"));
                endereco.setBairro(resultado.getString("bairro"));
                endereco.setRua(resultado.getString("rua"));
                endereco.setNumero(resultado.getInt("numero"));
                funcionario.setEndereco(endereco);

                funcionarios.add(funcionario);

                return funcionario;

            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, " Exception -> " + e);

            return null;
        }
        return null;
    }

    @Override
    public Funcionario listarPorCPF(String cpf) {
        try {

            String sql = "select * from funcionario where cpf = ? ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cpf);

            ResultSet resultado = ps.executeQuery();

            ArrayList<Funcionario> funcionarios = new ArrayList();

            while (resultado.next()) {
                Endereco endereco = new Endereco();
                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultado.getLong("id"));
                funcionario.setNome(resultado.getString("nome"));
                funcionario.setIdade(resultado.getInt("idade"));
                funcionario.setSexo(resultado.getString("sexo"));
                funcionario.setCpf(resultado.getString("cpf"));
                endereco.setUf(resultado.getString("uf"));
                endereco.setCep(resultado.getString("cep"));
                endereco.setCidade(resultado.getString("cidade"));
                endereco.setBairro(resultado.getString("bairro"));
                endereco.setRua(resultado.getString("rua"));
                endereco.setNumero(resultado.getInt("numero"));
                funcionario.setEndereco(endereco);

                funcionarios.add(funcionario);
                
                return funcionario;
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

}
