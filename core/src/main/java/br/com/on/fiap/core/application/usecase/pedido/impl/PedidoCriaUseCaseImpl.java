package br.com.on.fiap.core.application.usecase.pedido.impl;

import br.com.on.fiap.core.application.dto.entrada.PedidoEntrada;
import br.com.on.fiap.core.application.usecase.pedido.PedidoCriaUseCase;
import br.com.on.fiap.core.domain.Cliente;
import br.com.on.fiap.core.domain.Pagamento;
import br.com.on.fiap.core.domain.Pedido;
import br.com.on.fiap.core.domain.SituacaoPedido;
import java.time.LocalDateTime;

public class PedidoCriaUseCaseImpl implements PedidoCriaUseCase {

    @Override
    public Pedido criaPedido(PedidoEntrada pedidoEntrada, Cliente cliente, Pagamento pagamento) {
        LocalDateTime dataHora = LocalDateTime.now();
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataHora(dataHora);
        pedido.setSituacao(SituacaoPedido.REALIZADO);
        pedido.setPagamento(pagamento);
        return pedido;
    }
}
