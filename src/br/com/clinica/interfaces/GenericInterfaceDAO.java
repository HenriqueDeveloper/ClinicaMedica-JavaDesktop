/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.clinica.interfaces;

import br.com.clinica.model.objetos.Cidade;
import java.util.ArrayList;

/**
 *
 * @author Henrique
 */
public interface GenericInterfaceDAO<T> {
    
    public void cadastrar(T t);

    public ArrayList<T> listarTodos();

    public void atualizar(T t);

    public void excluir(T t);
    
    public ArrayList<Cidade> listarCidadesPorUF(String uf);
            
    public T listarPorId(long id);

    public T listarPorCPF(String cpf);
    
}
