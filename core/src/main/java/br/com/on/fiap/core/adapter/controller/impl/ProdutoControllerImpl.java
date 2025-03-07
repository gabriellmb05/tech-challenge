package br.com.on.fiap.core.adapter.controller.impl;

import br.com.on.fiap.core.adapter.controller.ProdutoController;
import br.com.on.fiap.core.adapter.presenter.ProdutoPresenter;
import br.com.on.fiap.core.application.dto.entrada.ProdutoEntrada;
import br.com.on.fiap.core.application.dto.entrada.ProdutoFiltro;
import br.com.on.fiap.core.application.dto.resposta.Pagina;
import br.com.on.fiap.core.application.dto.resposta.Paginacao;
import br.com.on.fiap.core.application.dto.resposta.ProdutoResposta;
import br.com.on.fiap.core.application.usecase.produto.*;
import br.com.on.fiap.core.domain.Produto;

public class ProdutoControllerImpl implements ProdutoController {

    private final ProdutoBuscaPorIdUseCase produtoBuscaPorIdUseCase;
    private final ProdutoListaUseCase produtoListaUseCase;
    private final ProdutoInsereUseCase produtoInsereUseCase;
    private final ProdutoAlteraUseCase produtoAlteraUseCase;
    private final ProdutoDeletaUseCase produtoDeletaUseCase;

    private final ProdutoPresenter produtoPresenter;

    public ProdutoControllerImpl(
            ProdutoBuscaPorIdUseCase produtoBuscaPorIdUseCase,
            ProdutoListaUseCase produtoListaUseCase,
            ProdutoInsereUseCase produtoInsereUseCase,
            ProdutoAlteraUseCase produtoAlteraUseCase,
            ProdutoDeletaUseCase produtoDeletaUseCase,
            ProdutoPresenter produtoPresenter) {
        this.produtoBuscaPorIdUseCase = produtoBuscaPorIdUseCase;
        this.produtoListaUseCase = produtoListaUseCase;
        this.produtoInsereUseCase = produtoInsereUseCase;
        this.produtoAlteraUseCase = produtoAlteraUseCase;
        this.produtoDeletaUseCase = produtoDeletaUseCase;
        this.produtoPresenter = produtoPresenter;
    }

    @Override
    public ProdutoResposta buscaProdutoPorId(Long id) {
        Produto produto = produtoBuscaPorIdUseCase.buscar(id);
        return produtoPresenter.formatar(produto);
    }

    @Override
    public Pagina<ProdutoResposta> listarProdutosComFiltro(ProdutoFiltro filtro, Paginacao paginacao) {
        Pagina<Produto> produtoPagina = produtoListaUseCase.listarComFiltro(filtro, paginacao);
        return produtoPresenter.formatar(produtoPagina);
    }

    @Override
    public ProdutoResposta insereProduto(ProdutoEntrada produtoEntrada) {
        Produto produtoPersistido = produtoInsereUseCase.inserir(produtoEntrada);
        return produtoPresenter.formatar(produtoPersistido);
    }

    @Override
    public ProdutoResposta alteraProduto(Long id, ProdutoEntrada produtoEntrada) {
        Produto produtoAtualizado = produtoAlteraUseCase.alterar(id, produtoEntrada);
        return produtoPresenter.formatar(produtoAtualizado);
    }

    @Override
    public void deletaProduto(Long id) {
        produtoDeletaUseCase.deleta(id);
    }
}
