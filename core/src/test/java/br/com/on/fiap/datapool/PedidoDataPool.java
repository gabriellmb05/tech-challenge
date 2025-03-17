package br.com.on.fiap.datapool;

import br.com.on.fiap.core.domain.Cliente;
import br.com.on.fiap.core.domain.Pedido;
import br.com.on.fiap.core.domain.SituacaoPedido;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoDataPool {

    private PedidoDataPool() {}

    public static Pedido criarPedidoExistente(Long id) {
        Pedido pedido = new Pedido();
        pedido.setId(id);
        pedido.setProtocolo("20250118213724238248");
        pedido.setDataHora(LocalDateTime.now());
        pedido.setSituacao(SituacaoPedido.REALIZADO);
        pedido.setCliente(ClienteDataPool.criarClienteExistente(1L));
        pedido.setPagamento(PagamentoDataPool.criarPagamentoExistente(1L));
        pedido.setRelPedidoProdutos(List.of(PedidoProdutoDataPool.criarPedidoProdutoComProduto(1L)));
        return pedido;
    }

    public static Pedido criarPedidoComProdutosValidos() {
        Pedido pedido = new Pedido();
        pedido.setRelPedidoProdutos(PedidoProdutoDataPool.criarPedidoProdutosComProdutos(2));
        return pedido;
    }

    public static Pedido criarPedidoComProdutosInvalidos() {
        Pedido pedido = new Pedido();
        pedido.setRelPedidoProdutos(List.of(PedidoProdutoDataPool.criarPedidoProdutoComProduto(1L)));
        return pedido;
    }

    public static Pedido criarPedidoComProtocolo(String protocolo) {
        Pedido pedido = new Pedido();
        pedido.setProtocolo(protocolo);
        pedido.setDataHora(LocalDateTime.now());
        pedido.setSituacao(SituacaoPedido.REALIZADO);
        pedido.setCliente(ClienteDataPool.criarClienteExistente(1L));
        pedido.setPagamento(PagamentoDataPool.criarPagamentoExistente(1L));
        pedido.setRelPedidoProdutos(List.of(PedidoProdutoDataPool.criarPedidoProdutoComProduto(1L)));
        return pedido;
    }

    public static Pedido criarPedidoComProtocoloPagamentoValido(String protocolo) {
        Pedido pedido = new Pedido();
        pedido.setProtocolo(protocolo);
        pedido.setDataHora(LocalDateTime.now());
        pedido.setSituacao(SituacaoPedido.REALIZADO);
        pedido.setCliente(ClienteDataPool.criarClienteExistente(1L));
        pedido.setPagamento(PagamentoDataPool.criarPagamentoValido());
        pedido.setRelPedidoProdutos(List.of(PedidoProdutoDataPool.criarPedidoProdutoComProduto(1L)));
        return pedido;
    }

    public static Pedido criarPedidoComCliente(Cliente cliente) {
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setCliente(cliente);
        pedido.setSituacao(SituacaoPedido.REALIZADO);
        pedido.setProtocolo("PROT12345");
        pedido.setDataHora(LocalDateTime.now());
        pedido.setRelPedidoProdutos(PedidoProdutoDataPool.criarPedidoProdutosComProdutos(1));
        return pedido;
    }

    public static Pedido criarPedidoExistenteComDataHora(Long id, LocalDateTime dataHora) {
        Pedido pedido = new Pedido();
        pedido.setId(id);
        pedido.setProtocolo("20250118213724238248");
        pedido.setDataHora(dataHora);
        pedido.setSituacao(SituacaoPedido.REALIZADO);
        pedido.setCliente(ClienteDataPool.criarClienteExistente(1L));
        pedido.setPagamento(PagamentoDataPool.criarPagamentoExistente(1L));
        pedido.setRelPedidoProdutos(List.of(PedidoProdutoDataPool.criarPedidoProdutoComProduto(1L)));
        return pedido;
    }
}
