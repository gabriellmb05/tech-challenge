package br.com.on.fiap.hexagono.portas.entrada.produto;

import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.dominio.ProdutoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListarProdutoPortaEntrada {

	Page<Produto> listarComFiltro(ProdutoFiltro filtro, Pageable pageable);
}
