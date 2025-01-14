package br.com.on.fiap.portas.entrada;

import br.com.on.fiap.dominio.Produto;
import br.com.on.fiap.dominio.ProdutoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListarProdutoPortaEntrada {

  Page<Produto> listarComFiltro(ProdutoFiltro filtro, Pageable pageable);
}
