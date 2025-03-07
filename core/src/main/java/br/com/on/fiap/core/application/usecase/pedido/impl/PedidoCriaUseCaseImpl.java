package br.com.on.fiap.core.application.usecase.pedido.impl;

import br.com.on.fiap.core.application.dto.entrada.pedido.PedidoEntrada;
import br.com.on.fiap.core.application.usecase.pedido.PedidoCriaUseCase;
import br.com.on.fiap.core.domain.Cliente;
import br.com.on.fiap.core.domain.Pagamento;
import br.com.on.fiap.core.domain.Pedido;
import br.com.on.fiap.core.domain.SituacaoPedido;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PedidoCriaUseCaseImpl implements PedidoCriaUseCase {

    @Override
    public Pedido criaPedido(PedidoEntrada pedidoEntrada, Cliente cliente, Pagamento pagamento) {
        LocalDateTime dataHora = LocalDateTime.now();
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataHora(dataHora);
        pedido.setSituacao(SituacaoPedido.REALIZADO);

        String protocoloGerado = gerarProtocolo(pedido);
        pedido.setProtocolo(protocoloGerado);

        pedido.setPagamento(pagamento);
        return pedido;
    }

    public String gerarProtocolo(Pedido pedido) {
        String dataHoraFormatada = pedido.getDataHora().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        Integer numeroAleatorio = new SecureRandom().nextInt(Integer.MAX_VALUE) % 10000;
        return String.format("%s%s%s", dataHoraFormatada, pedido.getId(), numeroAleatorio);
    }
}
