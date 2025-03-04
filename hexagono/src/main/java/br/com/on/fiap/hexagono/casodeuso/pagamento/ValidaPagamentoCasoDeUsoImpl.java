package br.com.on.fiap.hexagono.casodeuso.pagamento;

import br.com.on.fiap.hexagono.casodeuso.pagamento.entrada.ValidaPagamentoCasoDeUso;
import br.com.on.fiap.hexagono.entidades.Pagamento;
import br.com.on.fiap.hexagono.entidades.SituacaoPagamento;
import br.com.on.fiap.hexagono.excecao.PagamentoJaRealizadoExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
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
