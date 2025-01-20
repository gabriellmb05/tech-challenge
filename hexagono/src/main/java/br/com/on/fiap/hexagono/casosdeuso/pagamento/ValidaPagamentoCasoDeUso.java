package br.com.on.fiap.hexagono.casosdeuso.pagamento;

import br.com.on.fiap.hexagono.dominio.Pagamento;
import br.com.on.fiap.hexagono.dominio.SituacaoPagamento;
import br.com.on.fiap.hexagono.excecao.PagamentoJaRealizadoExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
import br.com.on.fiap.hexagono.portas.entrada.pagamento.ValidaPagamentoPortaEntrada;
import java.util.Objects;

public class ValidaPagamentoCasoDeUso implements ValidaPagamentoPortaEntrada {

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
