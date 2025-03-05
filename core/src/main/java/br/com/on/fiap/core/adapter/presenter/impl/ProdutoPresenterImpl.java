package br.com.on.fiap.core.adapter.presenter.impl;

import br.com.on.fiap.core.adapter.presenter.ProdutoPresenter;
import br.com.on.fiap.core.domain.model.Pagina;
import br.com.on.fiap.core.domain.model.Produto;
import br.com.on.fiap.core.domain.model.ProdutoResposta;
import java.util.List;

public class ProdutoPresenterImpl implements ProdutoPresenter {
    @Override
    public ProdutoResposta formatar(Produto produto) {
        return ProdutoResposta.create(
                produto.getId(), produto.getNome(), produto.getCategoria().name(), produto.getPreco());
    }

    @Override
    public Pagina<ProdutoResposta> formatar(Pagina<Produto> obj) {
        List<ProdutoResposta> conteudo =
                obj.getConteudo().stream().map(this::formatar).toList();

        return new Pagina<>() {
            @Override
            public List<ProdutoResposta> getConteudo() {
                return conteudo;
            }

            @Override
            public Long getTotalElementos() {
                return obj.getTotalElementos();
            }

            @Override
            public Integer getTotalPaginas() {
                return obj.getTotalPaginas();
            }

            @Override
            public Integer getTamanhoPagina() {
                return obj.getTamanhoPagina();
            }

            @Override
            public Integer getPaginaAtual() {
                return obj.getPaginaAtual();
            }
        };
    }
}
