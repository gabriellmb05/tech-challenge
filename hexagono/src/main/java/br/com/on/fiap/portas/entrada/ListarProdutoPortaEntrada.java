package br.com.on.fiap.portas.entrada;

import br.com.on.fiap.dominio.Categoria;
import br.com.on.fiap.dominio.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListarProdutoPortaEntrada {
    public Page<Produto> listarTodosProdutos(Pageable page);
    public Page<Produto> listarPorCategoria(Categoria categoria, Pageable page);
}
