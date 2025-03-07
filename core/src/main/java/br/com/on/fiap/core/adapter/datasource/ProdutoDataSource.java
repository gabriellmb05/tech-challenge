package br.com.on.fiap.core.adapter.datasource;

import br.com.on.fiap.core.application.dto.filtro.ProdutoFiltroEntrada;
import br.com.on.fiap.core.application.dto.resposta.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.PaginacaoResposta;
import br.com.on.fiap.core.domain.Produto;
import java.util.List;
import java.util.Optional;

public interface ProdutoDataSource {

    Optional<Produto> buscaProdutoPorId(Long id);

    List<Produto> buscaProdutoPorIdsLista(List<Long> ids);

    Produto salvaProduto(Produto produto);

    Optional<Produto> buscaProdutoPorNome(String nome);

    void deletaProdutoPorId(Long id);

    PaginaResposta<Produto> listarComFiltros(ProdutoFiltroEntrada filtro, PaginacaoResposta paginacaoResposta);
}
