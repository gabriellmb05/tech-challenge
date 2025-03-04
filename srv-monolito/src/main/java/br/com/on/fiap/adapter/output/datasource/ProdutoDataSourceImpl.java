package br.com.on.fiap.adapter.output.datasource;

import br.com.on.fiap.adapter.output.persistence.mapper.ProdutoSaidaMapeador;
import br.com.on.fiap.adapter.output.persistence.repository.ProdutoRepository;
import br.com.on.fiap.adapter.output.persistence.entity.ProdutoEntity;
import br.com.on.fiap.hexagono.adapter.datasource.ProdutoDataSource;
import br.com.on.fiap.hexagono.domain.entity.Produto;
import br.com.on.fiap.hexagono.domain.entity.ProdutoFiltro;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ProdutoDataSourceImpl implements ProdutoDataSource {

    private final ProdutoRepository produtoRepository;
    private final ProdutoSaidaMapeador produtoSaidaMapeador;

    public ProdutoDataSourceImpl(ProdutoRepository produtoRepository, ProdutoSaidaMapeador produtoSaidaMapeador) {
        this.produtoRepository = produtoRepository;
        this.produtoSaidaMapeador = produtoSaidaMapeador;
    }

    @Override
    public Optional<Produto> buscaProdutoPorId(Long id) {
        return produtoRepository.findById(id).map(produtoSaidaMapeador::paraProduto);
    }

    @Override
    public List<Produto> buscaProdutoPorIdsLista(List<Long> ids) {
        return produtoSaidaMapeador.paraListaProdutos(produtoRepository.findByProIdIn(ids));
    }

    @Override
    public Produto salvaProduto(Produto produto) {
        ProdutoEntity produtoEntity = produtoSaidaMapeador.paraEntidade(produto);
        ProdutoEntity produtoPersistido = produtoRepository.save(produtoEntity);
        return produtoSaidaMapeador.paraProduto(produtoPersistido);
    }

    @Override
    public Optional<Produto> buscaProdutoPorNome(String nome) {
        return produtoRepository.findByNmNome(nome).map(produtoSaidaMapeador::paraProduto);
    }

    @Override
    public void deletaProdutoPorId(Long id) {
        produtoRepository.deleteById(id);
    }

    @Override
    public Page<Produto> listarComFiltros(ProdutoFiltro filtro, Pageable page) {
        return produtoRepository.buscarComFiltro(filtro, page).map(produtoSaidaMapeador::paraProduto);
    }
}
