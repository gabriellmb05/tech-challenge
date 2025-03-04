package br.com.on.fiap.hexagono.casodeuso.produto.entrada;

import br.com.on.fiap.hexagono.entidades.Produto;
import br.com.on.fiap.hexagono.entidades.ProdutoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BuscaProdutosCasoDeUso {

    Page<Produto> listarComFiltro(ProdutoFiltro filtro, Pageable pageable);
}
