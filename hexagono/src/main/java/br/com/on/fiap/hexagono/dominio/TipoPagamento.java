package br.com.on.fiap.hexagono.dominio;

import br.com.on.fiap.hexagono.excecao.CategoriaNaoEncontradaExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;

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

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoPagamento deCodigo(Integer codigo) {
        return Stream.of(TipoPagamento.values())
                .filter(tipoPagamento -> Objects.equals(tipoPagamento.getCodigo(), codigo))
                .findFirst()
                .orElseThrow(() -> new CategoriaNaoEncontradaExcecao(
                        MessageError.MSG_ERRO_PAGAMENTO_TIPO_PAGAMENTO_NAO_ENCONTRADO.getMensagem(), codigo));
    }
}
