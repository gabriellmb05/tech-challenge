package br.com.on.fiap.hexagono.casosdeuso;

import br.com.on.fiap.hexagono.casosdeuso.excecao.ProdutoExistenteExcecao;
import br.com.on.fiap.hexagono.casosdeuso.excecao.ProdutoNaoEncontratoExcecao;
import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.portas.entrada.AlteraProdutoPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.InsereProdutoPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.PersisteProdutoPortaSaida;

import java.util.Optional;

public class AlteraProdutoCasoDeUso implements AlteraProdutoPortaEntrada{


    private final PersisteProdutoPortaSaida persisteProdutoPortaSaida;


    public AlteraProdutoCasoDeUso(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
        this.persisteProdutoPortaSaida = persisteProdutoPortaSaida;
    }

    @Override
    public Produto alterar(Long id, Produto produto) {
        persisteProdutoPortaSaida.buscaProdutoPorId(id).
                orElseThrow(() -> new ProdutoNaoEncontratoExcecao(String.format("Produto (%d) não encontrado", id)));
        produto.setId(id);
        return persisteProdutoPortaSaida.salvaProduto(produto);
    }
}
