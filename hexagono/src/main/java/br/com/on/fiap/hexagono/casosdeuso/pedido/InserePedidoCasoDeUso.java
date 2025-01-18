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
import br.com.on.fiap.hexagono.portas.saida.pedido.PersistePedidoPortaSaida;
import br.com.on.fiap.hexagono.portas.saida.pedido.PersistePedidoProdutoPortaSaida;
import java.util.List;

public class InserePedidoCasoDeUso implements InserePedidoPortaEntrada {

    private final PersisteClientePortaSaida persisteClientePortaSaida;
    private final PersistePedidoPortaSaida persistePedidoPortaSaida;
    private final PersistePedidoProdutoPortaSaida persistePedidoProdutoPortaSaida;
    private final PersistePagamentoPortaSaida persistePagamentoPortaSaida;

    public InserePedidoCasoDeUso(
            PersisteClientePortaSaida persisteClientePortaSaida,
            PersistePedidoPortaSaida persistePedidoPortaSaida,
            PersistePedidoProdutoPortaSaida persistePedidoProdutoPortaSaida,
            PersistePagamentoPortaSaida persistePagamentoPortaSaida) {
        this.persisteClientePortaSaida = persisteClientePortaSaida;
        this.persistePedidoPortaSaida = persistePedidoPortaSaida;
        this.persistePedidoProdutoPortaSaida = persistePedidoProdutoPortaSaida;
        this.persistePagamentoPortaSaida = persistePagamentoPortaSaida;
    }

    @Override
    public Pedido inserir(Pedido pedido) {
        Cliente cliente = buscarCliente(pedido);
        pedido.setCliente(cliente);
        Pedido pedidoSalvo = salvarPedido(pedido);
        vincularProdutosAoPedido(pedidoSalvo, pedido.getRelPedidoProdutos());
        vincularPedidoAoPagamento(pedidoSalvo, pedido.getPagamento());
        return pedidoSalvo;
    }

    private Cliente buscarCliente(Pedido pedido) {
        return persisteClientePortaSaida
                .buscaClientePorId(pedido.getCliente().getId())
                .orElseThrow(() -> new ClienteNaoEncontradoExcecao(
                        MessageError.MSG_CLIENTE_NAO_ENCONTRATO_PARA_ID.getMensagem(),
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
        Pagamento pagamentoSalvo = persistePagamentoPortaSaida.salvaPagamento(pagamento);
        pedidoSalvo.setPagamento(pagamentoSalvo);
        persistePedidoPortaSaida.salvaPedido(pedidoSalvo);
    }
}
