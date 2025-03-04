package br.com.on.fiap.datapool;

import br.com.on.fiap.core.domain.model.Cliente;
import br.com.on.fiap.core.domain.model.Pedido;
import br.com.on.fiap.core.domain.model.SituacaoPedido;
import java.time.LocalDateTime;
import java.util.List;

public class DataPoolPedido {

    private DataPoolPedido() {}

    public static Pedido pedidoExistente(Long id) {
        Pedido pedido = new Pedido();
        pedido.setId(id);
        pedido.setProtocolo("20250118213724238248");
        pedido.setDataHora(LocalDateTime.now());
        pedido.setSituacao(SituacaoPedido.REALIZADO);
        pedido.setCliente(DataPoolCliente.clienteExistente(1L));
        pedido.setPagamento(DataPoolPagamento.pagamentoExistente(1L));
        pedido.setRelPedidoProdutos(List.of(DataPoolRelPedidoProduto.relPedidoProdutoComProduto(1L)));
        return pedido;
    }

    public static Pedido pedidoComProdutosValidos() {
        Pedido pedido = new Pedido();
        pedido.setRelPedidoProdutos(DataPoolRelPedidoProduto.relPedidoProdutosComProdutos(2));
        return pedido;
    }

    public static Pedido pedidoComProdutosInvalidos() {
        Pedido pedido = new Pedido();
        pedido.setRelPedidoProdutos(List.of(DataPoolRelPedidoProduto.relPedidoProdutoComProduto(1L)));
        return pedido;
    }

    public static Pedido pedidoComProtocolo(String protocolo) {
        Pedido pedido = new Pedido();
        pedido.setProtocolo(protocolo);
        pedido.setDataHora(LocalDateTime.now());
        pedido.setSituacao(SituacaoPedido.REALIZADO);
        pedido.setCliente(DataPoolCliente.clienteExistente(1L));
        pedido.setPagamento(DataPoolPagamento.pagamentoExistente(1L));
        pedido.setRelPedidoProdutos(List.of(DataPoolRelPedidoProduto.relPedidoProdutoComProduto(1L)));
        return pedido;
    }

    public static Pedido pedidoComCliente(Cliente cliente) {
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setCliente(cliente);
        pedido.setSituacao(SituacaoPedido.REALIZADO);
        pedido.setProtocolo("PROT12345");
        pedido.setDataHora(LocalDateTime.now());
        pedido.setRelPedidoProdutos(DataPoolRelPedidoProduto.relPedidoProdutosComProdutos(1));
        return pedido;
    }

    public static Pedido pedidoExistenteComDataHora(Long id, LocalDateTime dataHora) {
        Pedido pedido = new Pedido();
        pedido.setId(id);
        pedido.setProtocolo("20250118213724238248");
        pedido.setDataHora(dataHora);
        pedido.setSituacao(SituacaoPedido.REALIZADO);
        pedido.setCliente(DataPoolCliente.clienteExistente(1L));
        pedido.setPagamento(DataPoolPagamento.pagamentoExistente(1L));
        pedido.setRelPedidoProdutos(List.of(DataPoolRelPedidoProduto.relPedidoProdutoComProduto(1L)));
        return pedido;
    }
}
