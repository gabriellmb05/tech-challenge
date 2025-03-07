package br.com.on.fiap.core.domain.model;

public interface PagamentoSolicitacao {

    Integer getTpPagamento();

    static PagamentoSolicitacao create(Integer tpPagamento) {
        return () -> tpPagamento;
    }
}
