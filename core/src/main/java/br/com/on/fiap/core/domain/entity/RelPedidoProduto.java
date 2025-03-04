package br.com.on.fiap.core.domain.entity;

public class RelPedidoProduto {

    private Produto produto;
    private Pedido pedido;
    private Long quantidade;

    public RelPedidoProduto(Produto produto, Pedido pedido, Long quantidade) {
        this.produto = produto;
        this.pedido = pedido;
        this.quantidade = quantidade;
    }

    public RelPedidoProduto() {}

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }
}
