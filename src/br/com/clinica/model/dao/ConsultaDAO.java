package br.com.clinica.model.dao;

import br.com.clinica.connectionfactory.ConnectionFactory;
import br.com.clinica.interfaces.GenericInterfaceDAO;
import br.com.clinica.model.objetos.Agendamento;
import br.com.clinica.model.objetos.Cidade;
import br.com.clinica.model.objetos.Consulta;
import br.com.clinica.model.objetos.Endereco;
import br.com.clinica.model.objetos.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ConsultaDAO implements GenericInterfaceDAO<Consulta> {
    
    private Connection con;
    
    public ConsultaDAO() {
        
        this.con = new ConnectionFactory().getConnection();
    }
    
    @Override
    public void cadastrar(Consulta consulta) {
        try {
            
            String sql = "insert into consulta (idAgendamento,laudoMedico, prescricao) values (?, ?, ?\r)";
            
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setLong(1, consulta.getAgendamento().getId());
            ps.setString(2, consulta.getLaudoMedico());
            ps.setString(3, consulta.getPrescricao());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Consulta efetuada com sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }
    
    @Override
    public ArrayList<Consulta> listarTodos() {
        try {
            String sql = "select c.id, p.nome as nome_paciente, p.idade,p.cpf,p.sexo,p.cidade, p.bairro, p.rua, p.numero, c.laudoMedico, c.prescricao from consulta c "
                    + "inner join paciente p, agendamento a where a.id = c.idAgendamento and "
                    + "a.id_paciente = p.id;";
            Statement stm = con.createStatement();
            stm.execute(sql);
            ResultSet resultado = stm.getResultSet();
            
            ArrayList<Consulta> consultas = new ArrayList();
            
            while (resultado.next()) {
                
                Consulta consulta = new Consulta();
                Paciente paciente = new Paciente();
                Agendamento agendamento = new Agendamento();
                Endereco endereco = new Endereco();
                
                consulta.setId(resultado.getLong("id"));
                paciente.setNome(resultado.getString("nome_paciente"));
                paciente.setIdade(resultado.getInt("idade"));
                paciente.setCpf(resultado.getString("cpf"));
                paciente.setSexo(resultado.getString("sexo"));
                endereco.setCidade(resultado.getString("cidade"));
                endereco.setBairro(resultado.getString("bairro"));
                endereco.setRua(resultado.getString("rua"));
                endereco.setNumero(resultado.getInt("numero"));
                paciente.setEndereco(endereco);
                agendamento.setPaciente(paciente);
                consulta.setAgendamento(agendamento);
                consulta.getAgendamento().setPaciente(paciente);
                consulta.setLaudoMedico(resultado.getString("laudoMedico"));
                consulta.setPrescricao(resultado.getString("prescricao"));
                
                consultas.add(consulta);
            }
            return consultas;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        
    }
    

    
    @Override
    public void atualizar(Consulta consulta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void excluir(Consulta consulta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public ArrayList<Cidade> listarCidadesPorUF(String uf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Consulta listarPorId(long id) {
       
         try {
            String sql = "select c.id, p.nome as nome_paciente, p.idade,p.cpf,p.sexo,p.cidade, p.bairro, p.rua, p.numero, c.laudoMedico, c.prescricao from consulta c "
                    + "inner join paciente p, agendamento a where a.id = c.idAgendamento and "
                    + "a.id_paciente = p.id and c.id = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            
            ResultSet resultado = ps.executeQuery();
            
            ArrayList<Consulta> lista = new ArrayList<>();
            
            while (resultado.next()) {
                
                Consulta consulta = new Consulta();
                Paciente paciente = new Paciente();
                Agendamento agendamento = new Agendamento();
                Endereco endereco = new Endereco();
                
                consulta.setId(resultado.getLong("id"));
                paciente.setNome(resultado.getString("nome_paciente"));
                paciente.setIdade(resultado.getInt("idade"));
                paciente.setCpf(resultado.getString("cpf"));
                paciente.setSexo(resultado.getString("sexo"));
                endereco.setCidade(resultado.getString("cidade"));
                endereco.setBairro(resultado.getString("bairro"));
                endereco.setRua(resultado.getString("rua"));
                endereco.setNumero(resultado.getInt("numero"));
                paciente.setEndereco(endereco);
                agendamento.setPaciente(paciente);
                consulta.setAgendamento(agendamento);
                consulta.getAgendamento().setPaciente(paciente);
                consulta.setLaudoMedico(resultado.getString("laudoMedico"));
                consulta.setPrescricao(resultado.getString("prescricao"));
                lista.add(consulta);
               
                return consulta;
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public Consulta listarPorCPF(String cpf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
