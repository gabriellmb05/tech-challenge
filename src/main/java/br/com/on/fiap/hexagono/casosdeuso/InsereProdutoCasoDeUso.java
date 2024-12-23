package br.com.on.fiap.hexagono.casosdeuso;

import br.com.on.fiap.hexagono.casosdeuso.excecoes.ProdutoExistenteExcecao;
import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.portas.entrada.InsereProdutoPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.PersisteProdutoPortaSaida;

import java.util.Optional;

public class InsereProdutoCasoDeUso implements InsereProdutoPortaEntrada {


    private final PersisteProdutoPortaSaida persisteProdutoPortaSaida;


    public InsereProdutoCasoDeUso(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
        this.persisteProdutoPortaSaida = persisteProdutoPortaSaida;
    }

    @Override
    public Produto inserir(Produto produto) {
        Optional<Produto> produtoBancoDados = persisteProdutoPortaSaida.buscaProdutoPorNome(produto.getNome());
        if(produtoBancoDados.isPresent()) throw new ProdutoExistenteExcecao("Produto " + produto.getNome() + " já cadastrado");
        return persisteProdutoPortaSaida.salvaProduto(produto);
    }
}
