package br.com.on.fiap.core.adapter.controller.impl;

import br.com.on.fiap.core.adapter.controller.ProdutoController;
import br.com.on.fiap.core.adapter.presenter.ProdutoPresenter;
import br.com.on.fiap.core.application.dto.*;
import br.com.on.fiap.core.application.usecase.produto.*;
import br.com.on.fiap.core.domain.entity.Produto;
import java.util.List;

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
    public ProdutoRespostaDTO buscaProdutoPorId(Long id) {
        Produto produto = produtoBuscaPorIdUseCase.buscar(id);
        return produtoPresenter.formatar(produto);
    }

    @Override
    public Pagina<ProdutoRespostaDTO> listarProdutosComFiltro(ProdutoFiltro filtro, Paginacao paginacao) {
        Pagina<Produto> produtoPagina = produtoListaUseCase.listarComFiltro(filtro, paginacao);
        List<ProdutoRespostaDTO> conteudo = produtoPagina.getConteudo().stream()
                .map(produtoPresenter::formatar)
                .toList();
        return new Pagina<>(
                conteudo,
                produtoPagina.getTotalElementos(),
                produtoPagina.getTotalPaginas(),
                produtoPagina.getTamanhoPagina(),
                produtoPagina.getPaginaAtual());
    }

    @Override
    public ProdutoRespostaDTO insereProduto(ProdutoEntradaDTO produtoEntradaDTO) {
        Produto produtoPersistido = produtoInsereUseCase.inserir(produtoEntradaDTO);
        return produtoPresenter.formatar(produtoPersistido);
    }

    @Override
    public ProdutoRespostaDTO alteraProduto(Long id, ProdutoEntradaDTO produtoEntradaDTO) {
        Produto produtoAtualizado = produtoAlteraUseCase.alterar(id, produtoEntradaDTO);
        return produtoPresenter.formatar(produtoAtualizado);
    }

    @Override
    public void deletaProduto(Long id) {
        produtoDeletaUseCase.deleta(id);
    }
}
