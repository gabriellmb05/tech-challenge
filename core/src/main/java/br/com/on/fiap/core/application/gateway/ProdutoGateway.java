package br.com.on.fiap.core.application.gateway;

import br.com.on.fiap.core.application.dto.entrada.ProdutoFiltro;
import br.com.on.fiap.core.application.dto.resposta.Pagina;
import br.com.on.fiap.core.application.dto.resposta.PaginacaoResposta;
import br.com.on.fiap.core.domain.Produto;
import java.util.List;
import java.util.Optional;

public interface ProdutoGateway {

    Optional<Produto> buscaProdutoPorId(Long id);

    List<Produto> buscaProdutoPorIdsLista(List<Long> ids);

    Produto salvaProduto(Produto produto);

    Optional<Produto> buscaProdutoPorNome(String nome);

    void deletaProdutoPorId(Long id);

    Pagina<Produto> listarComFiltros(ProdutoFiltro filtro, PaginacaoResposta paginacaoResposta);
}
