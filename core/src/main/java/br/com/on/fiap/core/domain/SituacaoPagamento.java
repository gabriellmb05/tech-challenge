package br.com.on.fiap.core.domain;

import java.util.Objects;
import java.util.stream.Stream;

public enum SituacaoPagamento {
    PENDENTE(1),
    RECUSADO(2),
    APROVADO(3);

    private final Integer codigo;

    SituacaoPagamento(Integer codigo) {
        this.codigo = codigo;
    }

    public static SituacaoPagamento deCodigo(Integer codigo) {
        return Stream.of(SituacaoPagamento.values())
                .filter(situacaoPagamento -> Objects.equals(situacaoPagamento.getCodigo(), codigo))
                .findFirst()
                .orElse(null);
    }

    public Integer getCodigo() {
        return codigo;
    }
}
