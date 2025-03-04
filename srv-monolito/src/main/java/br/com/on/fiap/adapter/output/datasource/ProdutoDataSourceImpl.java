package br.com.on.fiap.adapter.output.datasource;

import br.com.on.fiap.adapter.output.persistence.entity.ProdutoEntity;
import br.com.on.fiap.adapter.output.persistence.mapper.ProdutoSaidaMapeador;
import br.com.on.fiap.adapter.output.persistence.repository.ProdutoRepository;
import br.com.on.fiap.core.adapter.datasource.ProdutoDataSource;
import br.com.on.fiap.core.domain.model.Pagina;
import br.com.on.fiap.core.domain.model.Paginacao;
import br.com.on.fiap.core.domain.model.ProdutoFiltro;
import br.com.on.fiap.core.domain.model.Produto;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public Pagina<Produto> listarComFiltros(ProdutoFiltro filtro, Paginacao paginacao) {
        PageRequest page = PageRequest.of(paginacao.getPagina(), paginacao.getTamanhoPagina());
        if (Objects.nonNull(paginacao.getOrdenacao())) {
            Sort.Direction direcao =
                    Sort.Direction.valueOf(paginacao.getOrdenacao().getDirecao().name());
            Sort sort = Sort.by(direcao, paginacao.getOrdenacao().getCampo());
            page.withSort(sort);
        }

        Page<Produto> produto = produtoRepository.buscarComFiltro(filtro, page).map(produtoSaidaMapeador::paraProduto);

        return new Pagina<>(
                produto.getContent(),
                produto.getTotalElements(),
                produto.getTotalPages(),
                produto.getSize(),
                produto.getNumber());
    }
}
