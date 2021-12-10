package br.com.clinica.interfaces;

import java.util.List;

public interface GenericInterfaceController<T> {

    public void atualizarTabela();

    public void atualizarTabela(List<T> t);

    public void salvar();

    public void editar();

    public void bloquearCampos(boolean bloquear);

    public void limparCampos();

    public void listarCidadesPorEstado();

    public void btnAlterar();

    public void btnExcluir();

    public void btnCancelar();

    public void btnPesquisar();

}
