package br.com.on.fiap.core.usecase.pagamento.impl;

import br.com.on.fiap.core.domain.entity.Pagamento;
import br.com.on.fiap.core.domain.entity.SituacaoPagamento;
import br.com.on.fiap.core.domain.exception.PagamentoJaRealizadoExcecao;
import br.com.on.fiap.core.domain.exception.message.MessageError;
import br.com.on.fiap.core.usecase.pagamento.PagamentoValidaUseCase;
import java.util.Objects;

public class PagamentoValidaUseCaseImpl implements PagamentoValidaUseCase {

    @Override
    public void validarPagamentoJaRealizado(Pagamento pagamento, String nrProtocolo) {
        if (pagamento.getStPagamento().equals(SituacaoPagamento.APROVADO)
                && Objects.nonNull(pagamento.getDhPagamento())) {
            throw new PagamentoJaRealizadoExcecao(
                    MessageError.MSG_ERRO_PAGAMENTO_JA_REALIZADO_PARA_PEDIDO.getMensagem(),
                    pagamento.getVlCompra(),
                    nrProtocolo);
        }
    }
}
