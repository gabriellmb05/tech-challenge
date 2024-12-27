package br.com.on.fiap.hexagono.casosdeuso;

import br.com.on.fiap.hexagono.dominio.Categoria;
import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.portas.entrada.ListarProdutoPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.PersisteProdutoPortaSaida;
import java.util.List;

public class ListarProdutoCasoDeUso implements ListarProdutoPortaEntrada {

    private final PersisteProdutoPortaSaida persisteProdutoPortaSaida;

    public ListarProdutoCasoDeUso(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
        this.persisteProdutoPortaSaida = persisteProdutoPortaSaida;
    }

    @Override
    public List<Produto> listarTodosProdutos() {
        return this.persisteProdutoPortaSaida.listarTodosProdutos();
    }

    @Override
    public List<Produto> listarPorCategoria(Categoria categoria) {
        return this.persisteProdutoPortaSaida.listarProdutosPorCategoria(categoria);
    }
}