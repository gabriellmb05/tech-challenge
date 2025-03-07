package br.com.on.fiap.core.application.dto.entrada;

public interface PagamentoSolicitacao {

    Integer getTpPagamento();

    static PagamentoSolicitacao create(Integer tpPagamento) {
        return () -> tpPagamento;
    }
}
