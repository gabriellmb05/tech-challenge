package br.com.on.fiap.casosdeuso;

import br.com.on.fiap.dominio.Categoria;
import br.com.on.fiap.dominio.Produto;
import br.com.on.fiap.portas.entrada.ListarProdutoPortaEntrada;
import br.com.on.fiap.portas.saida.PersisteProdutoPortaSaida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ListarProdutoCasoDeUso implements ListarProdutoPortaEntrada {

    private final PersisteProdutoPortaSaida persisteProdutoPortaSaida;

    public ListarProdutoCasoDeUso(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
        this.persisteProdutoPortaSaida = persisteProdutoPortaSaida;
    }

    @Override
    public Page<Produto> listarTodosProdutos(Pageable page) {
        return this.persisteProdutoPortaSaida.listarTodosProdutos(page);
    }

    @Override
    public Page<Produto> listarPorCategoria(Categoria categoria, Pageable page) {
        return this.persisteProdutoPortaSaida.listarProdutosPorCategoria(categoria, page);
    }
}