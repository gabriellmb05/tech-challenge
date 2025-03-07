package br.com.on.fiap.core.application.usecase.produto.impl;

import br.com.on.fiap.core.application.dto.entrada.ProdutoFiltro;
import br.com.on.fiap.core.application.dto.resposta.Pagina;
import br.com.on.fiap.core.application.dto.resposta.PaginacaoResposta;
import br.com.on.fiap.core.application.gateway.ProdutoGateway;
import br.com.on.fiap.core.application.usecase.produto.ProdutoListaUseCase;
import br.com.on.fiap.core.domain.Produto;

public class ProdutoListaUseCaseImpl implements ProdutoListaUseCase {

    private final ProdutoGateway produtoGateway;

    public ProdutoListaUseCaseImpl(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Override
    public Pagina<Produto> listarComFiltro(ProdutoFiltro filtro, PaginacaoResposta paginacaoResposta) {
        return produtoGateway.listarComFiltros(filtro, paginacaoResposta);
    }
}
