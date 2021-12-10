/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.clinica.model.util.validacoes;

import br.com.clinica.model.dao.MedicoDAO;
import br.com.clinica.model.objetos.Medico;
import javax.swing.JOptionPane;

/**
 *
 * @author Henrique
 */
public class MedicoValidar {

    MedicoDAO repositorio = new MedicoDAO();

    public void validarMedico(Medico medico, String condicao) {

        if (medico.getNome().length() < 5) {
            JOptionPane.showMessageDialog(null, "Insira um paciente com no mínimo 5 caracteres");
            return;
        } else if (medico.getCpf().length() < 11) {
            JOptionPane.showMessageDialog(null, "Insira um CPF válido com 11 caracteres");
            return;
        } else if (medico.getEndereco().getCep().length() < 7) {
            JOptionPane.showMessageDialog(null, "Insira um CPF válido com 7 caracteres");
            return;
        } else if (medico.getEndereco().getNumero() <= 0) {
            JOptionPane.showMessageDialog(null, "Insira um número válido");
            return;
        /*}else if (medico.getContato().length() <= 16){
            JOptionPane.showMessageDialog(null, "Campo Vázio");
            return;
        }else if (medico.getContato().length() < 28){
            JOptionPane.showMessageDialog(null, "Insira um número de telefone válido!");
            return;*/
        }
        if (condicao.equals("salvar")) {
            for (Medico medicoo : repositorio.listarTodos()) {

                if (medico.getCpf().equals(medicoo.getCpf())) {
                    JOptionPane.showMessageDialog(null, "CPF já cadastrado no banco de dados");
                    return;
                }
            }
            repositorio.cadastrar(medico);
        } else if (condicao.equals("alterar")) {
            repositorio.atualizar(medico);
        }

    }

}
