package br.com.on.fiap.hexagono.casosdeuso.produto;

import br.com.on.fiap.hexagono.dominio.Categoria;
import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.excecao.ProdutoNaoEncontratoExcecao;
import br.com.on.fiap.hexagono.portas.entrada.produto.BuscaCategoriaPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.produto.BuscaProdutoPorIdPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.produto.PersisteProdutoPortaSaida;

import java.util.List;
import java.util.Optional;

public class BuscaCategoriaCasoDeUso implements BuscaCategoriaPortaEntrada {

    @Override
    public List<Categoria> buscaCategorias() {
        return List.of(Categoria.values());
    }
}
