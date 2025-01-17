package br.com.on.fiap.hexagono.dominio;

import br.com.on.fiap.hexagono.excecao.CategoriaNaoEncontradaExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;

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

    public Integer getCodigo() {
        return codigo;
    }

    public static SituacaoPagamento deCodigo(Integer codigo) {
        return Stream.of(SituacaoPagamento.values())
                .filter(situacaoPagamento -> Objects.equals(situacaoPagamento.getCodigo(), codigo))
                .findFirst()
                .orElseThrow(() -> new CategoriaNaoEncontradaExcecao(
                        MessageError.MSG_ERRO_CATEGORIA_NAO_CADASTRADA.getMensagem(), codigo));
    }
}
