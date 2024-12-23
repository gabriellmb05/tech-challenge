package br.com.on.fiap.hexagono.casosdeuso;

import br.com.on.fiap.hexagono.casosdeuso.excecoes.ProdutoNaoEncontratoExcecao;
import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.portas.entrada.BuscaProdutoPorIdPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.PersisteProdutoPortaSaida;

import java.util.Optional;

public class BuscaProdutoCasoDeUso implements BuscaProdutoPorIdPortaEntrada {


    private final PersisteProdutoPortaSaida persisteProdutoPortaSaida;


    public BuscaProdutoCasoDeUso(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
        this.persisteProdutoPortaSaida = persisteProdutoPortaSaida;
    }

    @Override
    public Produto buscar(Long id) {
        Optional<Produto> produtoBancoDados = persisteProdutoPortaSaida.buscaProdutoPorId(id);
        if(produtoBancoDados.isEmpty()) throw new ProdutoNaoEncontratoExcecao("Produto de id " + id + " não encontrado");
        return produtoBancoDados.get();
    }
}
