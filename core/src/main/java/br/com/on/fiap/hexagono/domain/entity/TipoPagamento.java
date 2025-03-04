package br.com.on.fiap.hexagono.domain.entity;

import br.com.on.fiap.hexagono.domain.exception.TipoPagamentoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.domain.exception.message.MessageError;
import java.util.Objects;
import java.util.stream.Stream;

public enum TipoPagamento {
    CREDITO(1, "CRÉDITO"),
    DEBITO(2, "DÉBITO"),
    PIX(3, "PIX");

    private final Integer codigo;
    private final String descricao;

    TipoPagamento(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static TipoPagamento deCodigo(Integer codigo) {
        return Stream.of(TipoPagamento.values())
                .filter(tipoPagamento -> Objects.equals(tipoPagamento.getCodigo(), codigo))
                .findFirst()
                .orElseThrow(() -> new TipoPagamentoNaoEncontradoExcecao(
                        MessageError.MSG_ERRO_PAGAMENTO_TIPO_PAGAMENTO_NAO_ENCONTRADO.getMensagem(), codigo));
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
