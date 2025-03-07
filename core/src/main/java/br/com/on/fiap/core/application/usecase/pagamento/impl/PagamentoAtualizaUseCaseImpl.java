package br.com.on.fiap.core.application.usecase.pagamento.impl;

import br.com.on.fiap.core.application.gateway.PagamentoGateway;
import br.com.on.fiap.core.application.gateway.PagamentoIntegracaoGateway;
import br.com.on.fiap.core.application.usecase.pagamento.PagamentoAtualizaUseCase;
import br.com.on.fiap.core.application.usecase.pagamento.PagamentoValidaUseCase;
import br.com.on.fiap.core.application.usecase.pedido.PedidoDetalhaUseCase;
import br.com.on.fiap.core.domain.Pagamento;
import br.com.on.fiap.core.domain.SituacaoPagamento;
import java.time.LocalDateTime;

public class PagamentoAtualizaUseCaseImpl implements PagamentoAtualizaUseCase {

    private final PedidoDetalhaUseCase pedidoDetalhaUseCase;
    private final PagamentoValidaUseCase pagamentoValidaUseCase;

    private final PagamentoGateway pagamentoGateway;
    private final PagamentoIntegracaoGateway pagamentoIntegracaoGateway;

    public PagamentoAtualizaUseCaseImpl(
            PedidoDetalhaUseCase pedidoDetalhaUseCase,
            PagamentoValidaUseCase pagamentoValidaUseCase,
            PagamentoGateway pagamentoGateway,
            PagamentoIntegracaoGateway pagamentoIntegracaoGateway) {
        this.pedidoDetalhaUseCase = pedidoDetalhaUseCase;
        this.pagamentoValidaUseCase = pagamentoValidaUseCase;
        this.pagamentoGateway = pagamentoGateway;
        this.pagamentoIntegracaoGateway = pagamentoIntegracaoGateway;
    }

    @Override
    public Pagamento atualizaPagamento(String nrProtocolo) {
        Pagamento pagamento = pedidoDetalhaUseCase.detalhaPedido(nrProtocolo).getPagamento();
        pagamentoValidaUseCase.validarPagamentoJaRealizado(pagamento, nrProtocolo);
        pagamentoIntegracaoGateway.integracaoEnviaPagamento(pagamento);

        pagamento.setDhPagamento(LocalDateTime.now());
        pagamento.setStPagamento(SituacaoPagamento.APROVADO);
        return pagamentoGateway.salvaPagamento(pagamento);
    }
}
