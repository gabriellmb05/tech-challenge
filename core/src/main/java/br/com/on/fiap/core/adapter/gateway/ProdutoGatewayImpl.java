package br.com.on.fiap.core.adapter.gateway;

import br.com.on.fiap.core.adapter.datasource.ProdutoDataSource;
import br.com.on.fiap.core.application.dto.resposta.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.PaginacaoResposta;
import br.com.on.fiap.core.application.gateway.ProdutoGateway;
import br.com.on.fiap.core.domain.Produto;
import br.com.on.fiap.core.domain.ProdutoFiltro;
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
    public PaginaResposta<Produto> listarComFiltros(ProdutoFiltro filtro, PaginacaoResposta paginacaoResposta) {
        return produtoDataSource.listarComFiltros(filtro, paginacaoResposta);
    }
}
