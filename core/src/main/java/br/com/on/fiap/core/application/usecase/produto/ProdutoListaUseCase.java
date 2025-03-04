package br.com.on.fiap.core.application.usecase.produto;

import br.com.on.fiap.core.application.dto.Pagina;
import br.com.on.fiap.core.application.dto.Paginacao;
import br.com.on.fiap.core.application.dto.ProdutoFiltro;
import br.com.on.fiap.core.domain.entity.Produto;

public interface ProdutoListaUseCase {

    Pagina<Produto> listarComFiltro(ProdutoFiltro filtro, Paginacao paginacao);
}
