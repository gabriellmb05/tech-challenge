package br.com.on.fiap.core.adapter.controller;

import br.com.on.fiap.core.application.dto.entrada.ProdutoEntrada;
import br.com.on.fiap.core.application.dto.entrada.ProdutoFiltro;
import br.com.on.fiap.core.application.dto.resposta.Pagina;
import br.com.on.fiap.core.application.dto.resposta.PaginacaoResposta;
import br.com.on.fiap.core.application.dto.resposta.ProdutoResposta;

public interface ProdutoController {

    ProdutoResposta buscaProdutoPorId(Long id);

    Pagina<ProdutoResposta> listarProdutosComFiltro(ProdutoFiltro filtro, PaginacaoResposta paginacaoResposta);

    ProdutoResposta insereProduto(ProdutoEntrada produtoSolicitacaoDTO);

    ProdutoResposta alteraProduto(Long id, ProdutoEntrada produtoSolicitacaoDTO);

    void deletaProduto(Long id);
}
