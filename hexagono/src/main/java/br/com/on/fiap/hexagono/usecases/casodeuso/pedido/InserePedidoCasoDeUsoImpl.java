package br.com.on.fiap.hexagono.usecases.casodeuso.pedido;

import br.com.on.fiap.hexagono.entities.entidades.Cliente;
import br.com.on.fiap.hexagono.entities.entidades.Pagamento;
import br.com.on.fiap.hexagono.entities.entidades.Pedido;
import br.com.on.fiap.hexagono.entities.entidades.RelPedidoProduto;
import br.com.on.fiap.hexagono.usecases.excecao.ClienteNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.usecases.excecao.message.MessageError;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.pedido.InserePedidoCasoDeUso;
import br.com.on.fiap.hexagono.usecases.interfaces.gateway.cliente.ClienteGateway;
import br.com.on.fiap.hexagono.usecases.interfaces.saida.pagamento.PersistePagamentoPortaSaida;
import br.com.on.fiap.hexagono.usecases.interfaces.saida.pedido.PersistePedidoPagamentoPortaSaida;
import br.com.on.fiap.hexagono.usecases.interfaces.saida.pedido.PersistePedidoPortaSaida;
import br.com.on.fiap.hexagono.usecases.interfaces.saida.pedido.PersistePedidoProdutoPortaSaida;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class InserePedidoCasoDeUsoImpl implements InserePedidoCasoDeUso {

    private final ClienteGateway clienteGateway;
    private final PersistePedidoPortaSaida persistePedidoPortaSaida;
    private final PersistePedidoProdutoPortaSaida persistePedidoProdutoPortaSaida;
    private final PersistePagamentoPortaSaida persistePagamentoPortaSaida;
    private final PersistePedidoPagamentoPortaSaida persistePedidoPagamentoPortaSaida;

    public InserePedidoCasoDeUsoImpl(
            ClienteGateway clienteGateway,
            PersistePedidoPortaSaida persistePedidoPortaSaida,
            PersistePedidoProdutoPortaSaida persistePedidoProdutoPortaSaida,
            PersistePagamentoPortaSaida persistePagamentoPortaSaida,
            PersistePedidoPagamentoPortaSaida persistePedidoPagamentoPortaSaida) {
        this.clienteGateway = clienteGateway;
        this.persistePedidoPortaSaida = persistePedidoPortaSaida;
        this.persistePedidoProdutoPortaSaida = persistePedidoProdutoPortaSaida;
        this.persistePagamentoPortaSaida = persistePagamentoPortaSaida;
        this.persistePedidoPagamentoPortaSaida = persistePedidoPagamentoPortaSaida;
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
        return persistePedidoPortaSaida.salvaPedido(pedido);
    }

    private void vincularProdutosAoPedido(Pedido pedidoSalvo, List<RelPedidoProduto> pedidoProdutos) {
        pedidoProdutos.forEach(relPedidoProduto -> relPedidoProduto.setPedido(pedidoSalvo));
        pedidoSalvo.setRelPedidoProdutos(pedidoProdutos);
        persistePedidoProdutoPortaSaida.vincularPedido(pedidoSalvo.getRelPedidoProdutos());
    }

    private void vincularPedidoAoPagamento(Pedido pedidoSalvo, Pagamento pagamento) {
        pagamento.setVlCompra(definirValorPedido(pedidoSalvo));
        Pagamento pagamentoSalvo = persistePagamentoPortaSaida.salvaPagamento(pagamento);
        pedidoSalvo.setPagamento(pagamentoSalvo);
        persistePedidoPagamentoPortaSaida.salvaPedidoPagamento(pedidoSalvo);
    }

    private BigDecimal definirValorPedido(Pedido pedidoSalvo) {
        AtomicReference<BigDecimal> valorPedido = new AtomicReference<>(BigDecimal.ZERO);

        pedidoSalvo.getRelPedidoProdutos().forEach(rel -> valorPedido.set(valorPedido
                .get()
                .add(rel.getProduto().getPreco().multiply(BigDecimal.valueOf(rel.getQuantidade())))));

        return valorPedido.get();
    }
}
