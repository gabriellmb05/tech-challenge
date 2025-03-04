package br.com.on.fiap.hexagono.adapter.gateway.impl;

import br.com.on.fiap.hexagono.adapter.datasource.ProdutoDataSource;
import br.com.on.fiap.hexagono.adapter.gateway.ProdutoGateway;
import br.com.on.fiap.hexagono.domain.entity.Produto;
import br.com.on.fiap.hexagono.domain.entity.ProdutoFiltro;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    public Page<Produto> listarComFiltros(ProdutoFiltro filtro, Pageable page) {
        return produtoDataSource.listarComFiltros(filtro, page);
    }
}
