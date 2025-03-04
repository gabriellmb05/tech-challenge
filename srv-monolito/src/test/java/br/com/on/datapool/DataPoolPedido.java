package br.com.on.datapool;

import br.com.on.fiap.core.domain.entity.Pedido;
import br.com.on.fiap.core.domain.entity.SituacaoPedido;
import java.time.LocalDateTime;
import java.util.List;

public class DataPoolPedido {

    public static Pedido gerarPedido() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setCliente(DataPoolCliente.gerarCliente());
        pedido.setPagamento(DataPoolPagamento.gerarPagamento());
        pedido.setSituacao(SituacaoPedido.REALIZADO);
        pedido.setProtocolo("2025012010424756339");
        pedido.setDataHora(LocalDateTime.now());
        pedido.setRelPedidoProdutos(DataPoolRelPedidoProduto.gerarListaRelPedidoProduto(pedido));
        return pedido;
    }

    private static Pedido gerarPedido2() {
        Pedido pedido = new Pedido();
        pedido.setId(2L);
        pedido.setCliente(DataPoolCliente.gerarCliente());
        pedido.setPagamento(DataPoolPagamento.gerarPagamento());
        pedido.setSituacao(SituacaoPedido.REALIZADO);
        pedido.setProtocolo("2025012010424756340");
        pedido.setDataHora(LocalDateTime.now());
        pedido.setRelPedidoProdutos(DataPoolRelPedidoProduto.gerarListaRelPedidoProduto(pedido));
        return pedido;
    }

    public static List<Pedido> gerarListaPedidos() {
        return List.of(gerarPedido(), gerarPedido2());
    }
}
