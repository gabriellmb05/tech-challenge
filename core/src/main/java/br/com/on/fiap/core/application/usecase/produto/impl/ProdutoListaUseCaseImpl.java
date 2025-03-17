package br.com.on.fiap.core.application.usecase.produto.impl;

import br.com.on.fiap.core.application.dto.filtro.ProdutoFiltroEntrada;
import br.com.on.fiap.core.application.dto.resposta.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.PaginacaoResposta;
import br.com.on.fiap.core.application.gateway.ProdutoGateway;
import br.com.on.fiap.core.application.usecase.produto.ProdutoListaUseCase;
import br.com.on.fiap.core.domain.Produto;
import br.com.on.fiap.core.domain.ProdutoFiltro;

public class ProdutoListaUseCaseImpl implements ProdutoListaUseCase {

    private final ProdutoGateway produtoGateway;

    public ProdutoListaUseCaseImpl(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Override
    public PaginaResposta<Produto> listarComFiltro(ProdutoFiltroEntrada filtro, PaginacaoResposta paginacaoResposta) {
        ProdutoFiltro produtoFiltro = new ProdutoFiltro(filtro.getNome(), filtro.getCategoria());
        return produtoGateway.listarComFiltros(produtoFiltro, paginacaoResposta);
    }
}
