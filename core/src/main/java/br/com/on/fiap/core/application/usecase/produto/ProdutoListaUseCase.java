package br.com.on.fiap.core.application.usecase.produto;

import br.com.on.fiap.core.application.dto.filtro.ProdutoFiltroEntrada;
import br.com.on.fiap.core.application.dto.resposta.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.PaginacaoResposta;
import br.com.on.fiap.core.domain.Produto;

public interface ProdutoListaUseCase {

    PaginaResposta<Produto> listarComFiltro(ProdutoFiltroEntrada filtro, PaginacaoResposta paginacaoResposta);
}
