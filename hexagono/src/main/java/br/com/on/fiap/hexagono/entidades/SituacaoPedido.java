package br.com.on.fiap.hexagono.entidades;

import java.util.Objects;
import java.util.stream.Stream;

public enum SituacaoPedido {
    REALIZADO(1, 1),
    APROVADO(2, 2),
    EM_PREPARACAO(3, 3),
    FINALIZADO(4, 4);

    private final Integer codigo;
    private final Integer ordem;

    SituacaoPedido(Integer codigo, Integer ordem) {
        this.codigo = codigo;
        this.ordem = ordem;
    }

    public static SituacaoPedido deCodigo(Integer codigo) {
        return Stream.of(SituacaoPedido.values())
                .filter(situacaoPedido -> Objects.equals(situacaoPedido.getCodigo(), codigo))
                .findFirst()
                .orElse(null);
    }

    public Integer getCodigo() {
        return codigo;
    }

    public Integer getOrdem() {
        return ordem;
    }
}
