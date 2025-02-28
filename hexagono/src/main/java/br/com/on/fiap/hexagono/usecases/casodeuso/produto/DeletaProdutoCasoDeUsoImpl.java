package br.com.on.fiap.hexagono.usecases.casodeuso.produto;

import br.com.on.fiap.hexagono.usecases.excecao.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.usecases.excecao.message.MessageError;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.produto.DeletaProdutoCasoDeUso;
import br.com.on.fiap.hexagono.usecases.interfaces.saida.produto.PersisteProdutoPortaSaida;

public class DeletaProdutoCasoDeUsoImpl implements DeletaProdutoCasoDeUso {

    private final PersisteProdutoPortaSaida persisteProdutoPortaSaida;

    public DeletaProdutoCasoDeUsoImpl(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
        this.persisteProdutoPortaSaida = persisteProdutoPortaSaida;
    }

    @Override
    public void deleta(Long id) {
        persisteProdutoPortaSaida
                .buscaProdutoPorId(id)
                .ifPresentOrElse(produto -> persisteProdutoPortaSaida.deletaProdutoPorId(id), () -> {
                    throw new ProdutoNaoEncontradoExcecao(
                            MessageError.MSG_ERRO_PRODUTO_NAO_CADASTRADO.getMensagem(), id);
                });
    }
}
