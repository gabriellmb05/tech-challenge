package br.com.on.fiap.hexagono.casodeuso.pedido;

import br.com.on.fiap.hexagono.adaptadores.gateways.ClienteGateway;
import br.com.on.fiap.hexagono.adaptadores.gateways.PedidoGateway;
import br.com.on.fiap.hexagono.casodeuso.pagamento.saida.PersistePagamentoPortaSaida;
import br.com.on.fiap.hexagono.casodeuso.pedido.entrada.InserePedidoCasoDeUso;
import br.com.on.fiap.hexagono.entidades.Cliente;
import br.com.on.fiap.hexagono.entidades.Pagamento;
import br.com.on.fiap.hexagono.entidades.Pedido;
import br.com.on.fiap.hexagono.entidades.RelPedidoProduto;
import br.com.on.fiap.hexagono.excecao.ClienteNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class InserePedidoCasoDeUsoImpl implements InserePedidoCasoDeUso {

    private final ClienteGateway clienteGateway;
    private final PedidoGateway pedidoGateway;
    private final PersistePagamentoPortaSaida persistePagamentoPortaSaida;

    public InserePedidoCasoDeUsoImpl(
            ClienteGateway clienteGateway,
            PedidoGateway pedidoGateway,
            PersistePagamentoPortaSaida persistePagamentoPortaSaida) {
        this.clienteGateway = clienteGateway;
        this.pedidoGateway = pedidoGateway;
        this.persistePagamentoPortaSaida = persistePagamentoPortaSaida;
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
        Pagamento pagamentoSalvo = persistePagamentoPortaSaida.salvaPagamento(pagamento);
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
