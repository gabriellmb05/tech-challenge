package br.com.on.fiap.core.application.usecase.produto;

import br.com.on.fiap.core.application.dto.filtro.produto.ProdutoFiltroEntrada;
import br.com.on.fiap.core.application.dto.resposta.paginacao.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.paginacao.PaginacaoResposta;
import br.com.on.fiap.core.domain.Produto;

public interface ProdutoListaUseCase {

    PaginaResposta<Produto> listarComFiltro(ProdutoFiltroEntrada filtro, PaginacaoResposta paginacaoResposta);
}
