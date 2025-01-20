package br.com.on.fiap.hexagono.casosdeuso.pedido;

import br.com.on.fiap.hexagono.dominio.Cliente;
import br.com.on.fiap.hexagono.dominio.Pagamento;
import br.com.on.fiap.hexagono.dominio.Pedido;
import br.com.on.fiap.hexagono.dominio.RelPedidoProduto;
import br.com.on.fiap.hexagono.excecao.ClienteNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
import br.com.on.fiap.hexagono.portas.entrada.pedido.InserePedidoPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.cliente.PersisteClientePortaSaida;
import br.com.on.fiap.hexagono.portas.saida.pagamento.PersistePagamentoPortaSaida;
import br.com.on.fiap.hexagono.portas.saida.pedido.PersistePedidoPagamentoPortaSaida;
import br.com.on.fiap.hexagono.portas.saida.pedido.PersistePedidoPortaSaida;
import br.com.on.fiap.hexagono.portas.saida.pedido.PersistePedidoProdutoPortaSaida;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class InserePedidoCasoDeUso implements InserePedidoPortaEntrada {

    private final PersisteClientePortaSaida persisteClientePortaSaida;
    private final PersistePedidoPortaSaida persistePedidoPortaSaida;
    private final PersistePedidoProdutoPortaSaida persistePedidoProdutoPortaSaida;
    private final PersistePagamentoPortaSaida persistePagamentoPortaSaida;
    private final PersistePedidoPagamentoPortaSaida persistePedidoPagamentoPortaSaida;

    public InserePedidoCasoDeUso(
            PersisteClientePortaSaida persisteClientePortaSaida,
            PersistePedidoPortaSaida persistePedidoPortaSaida,
            PersistePedidoProdutoPortaSaida persistePedidoProdutoPortaSaida,
            PersistePagamentoPortaSaida persistePagamentoPortaSaida,
            PersistePedidoPagamentoPortaSaida persistePedidoPagamentoPortaSaida) {
        this.persisteClientePortaSaida = persisteClientePortaSaida;
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
        return persisteClientePortaSaida
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

        pedidoSalvo.getRelPedidoProdutos().forEach(rel -> {
            valorPedido.set(valorPedido
                    .get()
                    .add(rel.getProduto().getPreco().multiply(BigDecimal.valueOf(rel.getQuantidade()))));
        });

        return valorPedido.get();
    }
}
