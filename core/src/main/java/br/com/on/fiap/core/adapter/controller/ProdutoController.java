package br.com.on.fiap.core.adapter.controller;

import br.com.on.fiap.core.domain.model.*;

public interface ProdutoController {

    ProdutoResposta buscaProdutoPorId(Long id);

    Pagina<ProdutoResposta> listarProdutosComFiltro(ProdutoFiltro filtro, Paginacao paginacao);

    ProdutoResposta insereProduto(ProdutoEntrada produtoSolicitacaoDTO);

    ProdutoResposta alteraProduto(Long id, ProdutoEntrada produtoSolicitacaoDTO);

    void deletaProduto(Long id);
}
