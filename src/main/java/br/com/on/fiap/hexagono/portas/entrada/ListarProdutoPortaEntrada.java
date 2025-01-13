package br.com.on.fiap.hexagono.portas.entrada;

import br.com.on.fiap.hexagono.dominio.Categoria;
import br.com.on.fiap.hexagono.dominio.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListarProdutoPortaEntrada {
    public Page<Produto> listarTodosProdutos(Pageable page);
    public Page<Produto> listarPorCategoria(Categoria categoria, Pageable page);
}
