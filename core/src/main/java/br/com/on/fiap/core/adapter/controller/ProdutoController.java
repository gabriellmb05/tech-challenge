package br.com.on.fiap.core.adapter.controller;

import br.com.on.fiap.core.domain.model.*;

public interface ProdutoController {

    ProdutoRespostaDTO buscaProdutoPorId(Long id);

    Pagina<ProdutoRespostaDTO> listarProdutosComFiltro(ProdutoFiltro filtro, Paginacao paginacao);

    ProdutoRespostaDTO insereProduto(ProdutoEntrada produtoSolicitacaoDTO);

    ProdutoRespostaDTO alteraProduto(Long id, ProdutoEntrada produtoSolicitacaoDTO);

    void deletaProduto(Long id);
}
