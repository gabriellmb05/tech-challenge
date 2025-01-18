package br.com.on.fiap.hexagono.dominio;

import br.com.on.fiap.hexagono.excecao.CategoriaNaoEncontradaExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
import java.util.Objects;
import java.util.stream.Stream;

public enum SituacaoPedido {
    REALIZADO(1),
    APROVADO(2),
    EM_PREPARACAO(3),
    FINALIZADO(4);

    private final Integer codigo;

    SituacaoPedido(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public static SituacaoPedido deCodigo(Integer codigo) {
        return Stream.of(SituacaoPedido.values())
                .filter(situacaoPedido -> Objects.equals(situacaoPedido.getCodigo(), codigo))
                .findFirst()
                .orElseThrow(() -> new CategoriaNaoEncontradaExcecao(
                        MessageError.MSG_ERRO_CATEGORIA_NAO_CADASTRADA.getMensagem(), codigo));
    }
}
