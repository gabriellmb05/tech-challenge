package br.com.on.fiap.hexagono.dominio;

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
                .orElse(null);
    }
}
