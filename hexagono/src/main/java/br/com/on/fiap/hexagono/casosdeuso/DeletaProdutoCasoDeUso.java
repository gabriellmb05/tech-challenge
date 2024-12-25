package br.com.on.fiap.hexagono.casosdeuso;

import br.com.on.fiap.hexagono.excecao.ProdutoNaoEncontratoExcecao;
import br.com.on.fiap.hexagono.portas.entrada.DeletaProdutoPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.PersisteProdutoPortaSaida;

public class DeletaProdutoCasoDeUso implements DeletaProdutoPortaEntrada {

    private final PersisteProdutoPortaSaida persisteProdutoPortaSaida;

    public DeletaProdutoCasoDeUso(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
        this.persisteProdutoPortaSaida = persisteProdutoPortaSaida;
    }

    @Override
    public void deleta(Long id) {
        persisteProdutoPortaSaida.buscaProdutoPorId(id).orElseThrow(() -> new ProdutoNaoEncontratoExcecao(String.format("Produto (%d) não encontrado", id)));
        persisteProdutoPortaSaida.deletaProdutoPorId(id);
    }
}