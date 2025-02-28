package br.com.on.fiap.hexagono.usecases.interfaces.entrada.produto;

import br.com.on.fiap.hexagono.entities.entidades.Produto;
import br.com.on.fiap.hexagono.entities.entidades.ProdutoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BuscaProdutosCasoDeUso {

    Page<Produto> listarComFiltro(ProdutoFiltro filtro, Pageable pageable);
}
