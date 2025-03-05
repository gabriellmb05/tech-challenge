package br.com.on.fiap.core.application.usecase.pedido.impl;

import br.com.on.fiap.core.adapter.gateway.ClienteGateway;
import br.com.on.fiap.core.adapter.gateway.PagamentoGateway;
import br.com.on.fiap.core.adapter.gateway.PedidoGateway;
import br.com.on.fiap.core.application.exception.ClienteNaoEncontradoExcecao;
import br.com.on.fiap.core.application.exception.message.MessageError;
import br.com.on.fiap.core.application.usecase.pedido.PedidoInsereUseCase;
import br.com.on.fiap.core.domain.model.Cliente;
import br.com.on.fiap.core.domain.model.Pagamento;
import br.com.on.fiap.core.domain.model.Pedido;
import br.com.on.fiap.core.domain.model.RelPedidoProduto;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class PedidoInsereUseCaseImpl implements PedidoInsereUseCase {

    private final ClienteGateway clienteGateway;
    private final PedidoGateway pedidoGateway;
    private final PagamentoGateway pagamentoGateway;

    public PedidoInsereUseCaseImpl(
            ClienteGateway clienteGateway, PedidoGateway pedidoGateway, PagamentoGateway pagamentoGateway) {
        this.clienteGateway = clienteGateway;
        this.pedidoGateway = pedidoGateway;
        this.pagamentoGateway = pagamentoGateway;
    }

    @Override
    public Pedido inserir(Pedido pedido, Pagamento pagamento) {
        Cliente cliente = buscarCliente(pedido);
        pedido.setCliente(cliente);
        Pedido pedidoSalvo = salvarPedido(pedido);
        vincularProdutosAoPedido(pedidoSalvo, pedido.getRelPedidoProdutos());
        vincularPedidoAoPagamento(pedidoSalvo, pagamento);
        return pedidoSalvo;
    }

    private Cliente buscarCliente(Pedido pedido) {
        return clienteGateway
                .buscaClientePorId(pedido.getCliente().getId())
                .orElseThrow(() -> new ClienteNaoEncontradoExcecao(
                        MessageError.MSG_ERRO_CLIENTE_NAO_ENCONTRATO_PARA_ID.getMensagem(),
                        pedido.getCliente().getId()));
    }

    private Pedido salvarPedido(Pedido pedido) {
        return pedidoGateway.salvaPedido(pedido);
    }

    private void vincularProdutosAoPedido(Pedido pedidoSalvo, List<RelPedidoProduto> pedidoProdutos) {
        pedidoProdutos.forEach(relPedidoProduto -> relPedidoProduto.setPedido(pedidoSalvo));
        pedidoSalvo.setRelPedidoProdutos(pedidoProdutos);
        pedidoGateway.vincularPedido(pedidoSalvo.getRelPedidoProdutos());
    }

    private void vincularPedidoAoPagamento(Pedido pedidoSalvo, Pagamento pagamento) {
        pagamento.setVlCompra(definirValorPedido(pedidoSalvo));
        Pagamento pagamentoSalvo = pagamentoGateway.salvaPagamento(pagamento);
        pedidoSalvo.setPagamento(pagamentoSalvo);
        pedidoGateway.salvaPedidoPagamento(pedidoSalvo);
    }

    private BigDecimal definirValorPedido(Pedido pedidoSalvo) {
        AtomicReference<BigDecimal> valorPedido = new AtomicReference<>(BigDecimal.ZERO);

        pedidoSalvo
                .getRelPedidoProdutos()
                .forEach(rel -> valorPedido.set(valorPedido
                        .get()
                        .add(rel.getProduto().getPreco().multiply(BigDecimal.valueOf(rel.getQuantidade())))));

        return valorPedido.get();
    }
}
