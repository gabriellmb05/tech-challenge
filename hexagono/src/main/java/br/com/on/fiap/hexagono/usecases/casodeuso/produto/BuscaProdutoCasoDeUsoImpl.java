package br.com.on.fiap.hexagono.usecases.casodeuso.produto;

import br.com.on.fiap.hexagono.entities.entidades.Produto;
import br.com.on.fiap.hexagono.usecases.excecao.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.usecases.excecao.message.MessageError;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.produto.BuscaProdutoPorIdCasoDeUso;
import br.com.on.fiap.hexagono.usecases.interfaces.saida.produto.PersisteProdutoPortaSaida;
import java.util.Optional;

public class BuscaProdutoCasoDeUsoImpl implements BuscaProdutoPorIdCasoDeUso {

    private final PersisteProdutoPortaSaida persisteProdutoPortaSaida;

    public BuscaProdutoCasoDeUsoImpl(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
        this.persisteProdutoPortaSaida = persisteProdutoPortaSaida;
    }

    @Override
    public Produto buscar(Long id) {
        Optional<Produto> produtoBancoDados = persisteProdutoPortaSaida.buscaProdutoPorId(id);
        return produtoBancoDados.orElseThrow(
                () -> new ProdutoNaoEncontradoExcecao(MessageError.MSG_ERRO_PRODUTO_NAO_CADASTRADO.getMensagem(), id));
    }
}
