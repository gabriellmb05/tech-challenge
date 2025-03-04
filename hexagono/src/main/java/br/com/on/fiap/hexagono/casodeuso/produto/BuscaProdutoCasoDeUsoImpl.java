package br.com.on.fiap.hexagono.casodeuso.produto;

import br.com.on.fiap.hexagono.casodeuso.produto.entrada.BuscaProdutoPorIdCasoDeUso;
import br.com.on.fiap.hexagono.casodeuso.produto.saida.PersisteProdutoPortaSaida;
import br.com.on.fiap.hexagono.entidades.Produto;
import br.com.on.fiap.hexagono.excecao.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
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
