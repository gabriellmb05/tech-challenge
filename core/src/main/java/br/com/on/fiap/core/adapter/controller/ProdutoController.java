package br.com.on.fiap.core.adapter.controller;

import br.com.on.fiap.core.application.dto.*;

public interface ProdutoController {

    ProdutoRespostaDTO buscaProdutoPorId(Long id);

    Pagina<ProdutoRespostaDTO> listarProdutosComFiltro(ProdutoFiltro filtro, Paginacao paginacao);

    ProdutoRespostaDTO insereProduto(ProdutoEntradaDTO produtoSolicitacaoDTO);

    ProdutoRespostaDTO alteraProduto(Long id, ProdutoEntradaDTO produtoSolicitacaoDTO);

    void deletaProduto(Long id);
}
