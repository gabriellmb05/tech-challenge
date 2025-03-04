package br.com.on.fiap.hexagono.casodeuso.produto;

import br.com.on.fiap.hexagono.casodeuso.produto.entrada.DeletaProdutoCasoDeUso;
import br.com.on.fiap.hexagono.casodeuso.produto.saida.PersisteProdutoPortaSaida;
import br.com.on.fiap.hexagono.excecao.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;

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
