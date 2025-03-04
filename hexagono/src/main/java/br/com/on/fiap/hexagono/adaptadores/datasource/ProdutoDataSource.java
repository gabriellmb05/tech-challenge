package br.com.on.fiap.hexagono.adaptadores.datasource;

import br.com.on.fiap.hexagono.entidades.Produto;
import br.com.on.fiap.hexagono.entidades.ProdutoFiltro;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoDataSource {

    Optional<Produto> buscaProdutoPorId(Long id);

    List<Produto> buscaProdutoPorIdsLista(List<Long> ids);

    Produto salvaProduto(Produto produto);

    Optional<Produto> buscaProdutoPorNome(String nome);

    void deletaProdutoPorId(Long id);

    Page<Produto> listarComFiltros(ProdutoFiltro filtro, Pageable page);
}
