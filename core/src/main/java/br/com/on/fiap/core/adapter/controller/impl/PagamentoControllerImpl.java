package br.com.on.fiap.core.adapter.controller.impl;

import br.com.on.fiap.core.adapter.controller.PagamentoController;
import br.com.on.fiap.core.adapter.presenter.PagamentoPresenter;
import br.com.on.fiap.core.application.usecase.pagamento.PagamentoAtualizaUseCase;
import br.com.on.fiap.core.application.usecase.pagamento.PagamentoValidaUseCase;
import br.com.on.fiap.core.application.usecase.pedido.PedidoDetalhaUseCase;
import br.com.on.fiap.core.domain.model.Pagamento;
import br.com.on.fiap.core.domain.model.PagamentoRespostaDTO;

public class PagamentoControllerImpl implements PagamentoController {

    private final PedidoDetalhaUseCase pedidoDetalhaUseCase;
    private final PagamentoValidaUseCase pagamentoValidaUseCase;
    private final PagamentoAtualizaUseCase pagamentoAtualizaUseCase;

    private final PagamentoPresenter pagamentoPresenter;

    public PagamentoControllerImpl(
            PedidoDetalhaUseCase pedidoDetalhaUseCase,
            PagamentoValidaUseCase pagamentoValidaUseCase,
            PagamentoAtualizaUseCase pagamentoAtualizaUseCase,
            PagamentoPresenter pagamentoPresenter) {
        this.pedidoDetalhaUseCase = pedidoDetalhaUseCase;
        this.pagamentoValidaUseCase = pagamentoValidaUseCase;
        this.pagamentoAtualizaUseCase = pagamentoAtualizaUseCase;
        this.pagamentoPresenter = pagamentoPresenter;
    }

    @Override
    public PagamentoRespostaDTO atualizaPagamento(String nrProtocolo) {
        Pagamento pagamento = pedidoDetalhaUseCase.detalhaPedido(nrProtocolo).getPagamento();
        pagamentoValidaUseCase.validarPagamentoJaRealizado(pagamento, nrProtocolo);
        Pagamento pagamentoAtualizado = pagamentoAtualizaUseCase.atualizaPagamento(pagamento);
        return pagamentoPresenter.formatar(pagamentoAtualizado);
    }
}
