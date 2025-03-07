package br.com.on.fiap.adapter.output.datasource;

import br.com.on.fiap.adapter.input.dto.resposta.PaginaRespostaInfo;
import br.com.on.fiap.adapter.output.persistence.component.PageableComponent;
import br.com.on.fiap.adapter.output.persistence.entity.ProdutoEntity;
import br.com.on.fiap.adapter.output.persistence.repository.ProdutoRepository;
import br.com.on.fiap.core.adapter.datasource.ProdutoDataSource;
import br.com.on.fiap.core.application.dto.resposta.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.PaginacaoResposta;
import br.com.on.fiap.core.domain.Produto;
import br.com.on.fiap.core.domain.ProdutoFiltro;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ProdutoDataSourceImpl implements ProdutoDataSource {

    private final ProdutoRepository produtoRepository;
    private final PageableComponent pageableComponent;

    public ProdutoDataSourceImpl(ProdutoRepository produtoRepository, PageableComponent pageableComponent) {
        this.produtoRepository = produtoRepository;
        this.pageableComponent = pageableComponent;
    }

    @Override
    public Optional<Produto> buscaProdutoPorId(Long id) {
        return produtoRepository.findById(id).map(ProdutoEntity::toDomain);
    }

    @Override
    public List<Produto> buscaProdutoPorIdsLista(List<Long> ids) {
        return produtoRepository.findByProIdIn(ids).stream()
                .map(ProdutoEntity::toDomain)
                .toList();
    }

    @Override
    public Produto salvaProduto(Produto produto) {
        ProdutoEntity produtoEntity = ProdutoEntity.fromDomain(produto);
        ProdutoEntity produtoPersistido = produtoRepository.save(produtoEntity);
        return produtoPersistido.toDomain();
    }

    @Override
    public Optional<Produto> buscaProdutoPorNome(String nome) {
        return produtoRepository.findByNmNome(nome).map(ProdutoEntity::toDomain);
    }

    @Override
    public void deletaProdutoPorId(Long id) {
        produtoRepository.deleteById(id);
    }

    @Override
    public PaginaResposta<Produto> listarComFiltros(ProdutoFiltro filtro, PaginacaoResposta paginacaoResposta) {
        Pageable pageable = pageableComponent.criarPageable(paginacaoResposta);
        Page<Produto> pageProduto =
                produtoRepository.buscarComFiltro(filtro, pageable).map(ProdutoEntity::toDomain);
        return PaginaRespostaInfo.create(pageProduto);
    }
}
