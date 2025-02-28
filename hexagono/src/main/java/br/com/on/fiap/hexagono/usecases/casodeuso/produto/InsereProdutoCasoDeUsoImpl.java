package br.com.on.fiap.hexagono.usecases.casodeuso.produto;

import br.com.on.fiap.hexagono.entities.entidades.Produto;
import br.com.on.fiap.hexagono.usecases.excecao.ProdutoExistenteExcecao;
import br.com.on.fiap.hexagono.usecases.excecao.message.MessageError;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.produto.InsereProdutoCasoDeUso;
import br.com.on.fiap.hexagono.usecases.interfaces.saida.produto.PersisteProdutoPortaSaida;
import java.util.Optional;

public class InsereProdutoCasoDeUsoImpl implements InsereProdutoCasoDeUso {

    private final PersisteProdutoPortaSaida persisteProdutoPortaSaida;

    public InsereProdutoCasoDeUsoImpl(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
        this.persisteProdutoPortaSaida = persisteProdutoPortaSaida;
    }

    @Override
    public Produto inserir(Produto produto) {
        Optional<Produto> produtoBancoDados = persisteProdutoPortaSaida.buscaProdutoPorNome(produto.getNome());
        produtoBancoDados.ifPresent(p -> {
            throw new ProdutoExistenteExcecao(
                    MessageError.MSG_ERRO_PRODUTO_JA_CADASTRADO.getMensagem(), produto.getNome());
        });
        return persisteProdutoPortaSaida.salvaProduto(produto);
    }
}
