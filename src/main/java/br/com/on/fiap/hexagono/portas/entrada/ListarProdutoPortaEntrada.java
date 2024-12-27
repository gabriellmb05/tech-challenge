package br.com.on.fiap.hexagono.portas.entrada;

import br.com.on.fiap.hexagono.dominio.Categoria;
import br.com.on.fiap.hexagono.dominio.Produto;

import java.util.List;

public interface ListarProdutoPortaEntrada {
    public List<Produto> listarTodosProdutos();
    public List<Produto> listarPorCategoria(Categoria categoria);
}
