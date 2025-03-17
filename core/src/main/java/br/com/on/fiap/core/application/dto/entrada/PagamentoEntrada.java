package br.com.on.fiap.core.application.dto.entrada;

public interface PagamentoEntrada {

    Integer getTpPagamento();

    static PagamentoEntrada create(Integer tpPagamento) {
        return () -> tpPagamento;
    }
}
