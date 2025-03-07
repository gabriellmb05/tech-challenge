package br.com.on.fiap.core.application.usecase.produto;

import br.com.on.fiap.core.application.dto.entrada.ProdutoFiltro;
import br.com.on.fiap.core.application.dto.resposta.Pagina;
import br.com.on.fiap.core.application.dto.resposta.PaginacaoResposta;
import br.com.on.fiap.core.domain.Produto;

public interface ProdutoListaUseCase {

    Pagina<Produto> listarComFiltro(ProdutoFiltro filtro, PaginacaoResposta paginacaoResposta);
}
