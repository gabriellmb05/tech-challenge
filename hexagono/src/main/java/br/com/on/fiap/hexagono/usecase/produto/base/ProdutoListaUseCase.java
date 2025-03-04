package br.com.on.fiap.hexagono.usecase.produto.base;

import br.com.on.fiap.hexagono.domain.entity.Produto;
import br.com.on.fiap.hexagono.domain.entity.ProdutoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoListaUseCase {

    Page<Produto> listarComFiltro(ProdutoFiltro filtro, Pageable pageable);
}
