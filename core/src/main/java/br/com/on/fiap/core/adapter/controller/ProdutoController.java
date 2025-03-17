package br.com.on.fiap.core.adapter.controller;

import br.com.on.fiap.core.application.dto.entrada.ProdutoEntrada;
import br.com.on.fiap.core.application.dto.filtro.ProdutoFiltroEntrada;
import br.com.on.fiap.core.application.dto.resposta.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.PaginacaoResposta;
import br.com.on.fiap.core.application.dto.resposta.ProdutoResposta;

public interface ProdutoController {

    ProdutoResposta buscaProdutoPorId(Long id);

    PaginaResposta<ProdutoResposta> listarProdutosComFiltro(
            ProdutoFiltroEntrada filtro, PaginacaoResposta paginacaoResposta);

    ProdutoResposta insereProduto(ProdutoEntrada produtoSolicitacaoDTO);

    ProdutoResposta alteraProduto(Long id, ProdutoEntrada produtoSolicitacaoDTO);

    void deletaProduto(Long id);
}
