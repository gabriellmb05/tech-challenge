package br.com.on.fiap.hexagono.usecases.casodeuso.pagamento;

import br.com.on.fiap.hexagono.entities.entidades.Pagamento;
import br.com.on.fiap.hexagono.entities.entidades.SituacaoPagamento;
import br.com.on.fiap.hexagono.usecases.excecao.PagamentoJaRealizadoExcecao;
import br.com.on.fiap.hexagono.usecases.excecao.message.MessageError;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.pagamento.ValidaPagamentoCasoDeUso;
import java.util.Objects;

public class ValidaPagamentoCasoDeUsoImpl implements ValidaPagamentoCasoDeUso {

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
