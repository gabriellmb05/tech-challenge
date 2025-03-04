package br.com.on.fiap.core.adapter.gateway.impl;

import br.com.on.fiap.core.adapter.datasource.ProdutoDataSource;
import br.com.on.fiap.core.adapter.gateway.ProdutoGateway;
import br.com.on.fiap.core.domain.model.Pagina;
import br.com.on.fiap.core.domain.model.Paginacao;
import br.com.on.fiap.core.domain.model.ProdutoFiltro;
import br.com.on.fiap.core.domain.model.Produto;
import java.util.List;
import java.util.Optional;

public class ProdutoGatewayImpl implements ProdutoGateway {

    private final ProdutoDataSource produtoDataSource;

    public ProdutoGatewayImpl(ProdutoDataSource produtoDataSource) {
        this.produtoDataSource = produtoDataSource;
    }

    @Override
    public Optional<Produto> buscaProdutoPorId(Long id) {
        return produtoDataSource.buscaProdutoPorId(id);
    }

    @Override
    public List<Produto> buscaProdutoPorIdsLista(List<Long> ids) {
        return produtoDataSource.buscaProdutoPorIdsLista(ids);
    }

    @Override
    public Produto salvaProduto(Produto produto) {
        return produtoDataSource.salvaProduto(produto);
    }

    @Override
    public Optional<Produto> buscaProdutoPorNome(String nome) {
        return produtoDataSource.buscaProdutoPorNome(nome);
    }

    @Override
    public void deletaProdutoPorId(Long id) {
        produtoDataSource.deletaProdutoPorId(id);
    }

    @Override
    public Pagina<Produto> listarComFiltros(ProdutoFiltro filtro, Paginacao paginacao) {
        return produtoDataSource.listarComFiltros(filtro, paginacao);
    }
}
