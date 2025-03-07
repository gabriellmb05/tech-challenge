package br.com.on.fiap.core.adapter.controller;

import br.com.on.fiap.core.application.dto.entrada.produto.ProdutoEntrada;
import br.com.on.fiap.core.application.dto.filtro.produto.ProdutoFiltroEntrada;
import br.com.on.fiap.core.application.dto.resposta.paginacao.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.paginacao.PaginacaoResposta;
import br.com.on.fiap.core.application.dto.resposta.produto.ProdutoResposta;

public interface ProdutoController {

    ProdutoResposta buscaProdutoPorId(Long id);

    PaginaResposta<ProdutoResposta> listarProdutosComFiltro(
            ProdutoFiltroEntrada filtro, PaginacaoResposta paginacaoResposta);

    ProdutoResposta insereProduto(ProdutoEntrada produtoSolicitacaoDTO);

    ProdutoResposta alteraProduto(Long id, ProdutoEntrada produtoSolicitacaoDTO);

    void deletaProduto(Long id);
}
