package br.com.on.fiap.core.adapter.gateway;

import br.com.on.fiap.core.application.dto.Pagina;
import br.com.on.fiap.core.application.dto.Paginacao;
import br.com.on.fiap.core.application.dto.ProdutoFiltro;
import br.com.on.fiap.core.domain.entity.Produto;
import java.util.List;
import java.util.Optional;

public interface ProdutoGateway {

    Optional<Produto> buscaProdutoPorId(Long id);

    List<Produto> buscaProdutoPorIdsLista(List<Long> ids);

    Produto salvaProduto(Produto produto);

    Optional<Produto> buscaProdutoPorNome(String nome);

    void deletaProdutoPorId(Long id);

    Pagina<Produto> listarComFiltros(ProdutoFiltro filtro, Paginacao paginacao);
}
