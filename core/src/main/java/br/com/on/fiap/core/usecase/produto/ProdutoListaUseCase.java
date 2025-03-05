package br.com.on.fiap.core.usecase.produto;

import br.com.on.fiap.core.domain.model.Pagina;
import br.com.on.fiap.core.domain.model.Paginacao;
import br.com.on.fiap.core.domain.model.Produto;
import br.com.on.fiap.core.domain.model.ProdutoFiltro;

public interface ProdutoListaUseCase {

    Pagina<Produto> listarComFiltro(ProdutoFiltro filtro, Paginacao paginacao);
}
