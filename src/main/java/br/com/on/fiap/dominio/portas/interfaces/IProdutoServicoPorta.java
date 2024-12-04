package br.com.on.fiap.dominio.portas.interfaces;

import br.com.on.fiap.aplicacao.adaptadores.dto.ProdutoDTO;

import java.util.List;

public interface IProdutoServicoPorta {
    List<ProdutoDTO> getAll();

    ProdutoDTO getProdutoById(long id);

    String getTotalProdutos();

    int createProduto(ProdutoDTO produto);

    int updateProduto(ProdutoDTO produto);
}
