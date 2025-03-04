package br.com.on.fiap.core.application.usecase.produto;

import br.com.on.fiap.core.domain.entity.Produto;
import br.com.on.fiap.core.domain.entity.ProdutoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoListaUseCase {

    Page<Produto> listarComFiltro(ProdutoFiltro filtro, Pageable pageable);
}
