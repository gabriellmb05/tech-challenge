package br.com.on.fiap.core.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Pedido {

    private Long id;
    private List<PedidoProduto> pedidoProdutos;
    private Cliente cliente;
    private Pagamento pagamento;
    private SituacaoPedido situacao;
    private String protocolo;
    private LocalDateTime dataHora;

    public Pedido() {}

    public Pedido(
            Long id,
            List<PedidoProduto> pedidoProdutos,
            Cliente cliente,
            Pagamento pagamento,
            SituacaoPedido situacao,
            String protocolo,
            LocalDateTime dataHora) {
        this.id = id;
        this.pedidoProdutos = pedidoProdutos;
        this.cliente = cliente;
        this.pagamento = pagamento;
        this.situacao = situacao;
        this.protocolo = protocolo;
        this.dataHora = dataHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PedidoProduto> getRelPedidoProdutos() {
        return pedidoProdutos;
    }

    public void setRelPedidoProdutos(List<PedidoProduto> pedidoProdutos) {
        this.pedidoProdutos = pedidoProdutos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public SituacaoPedido getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoPedido situacao) {
        this.situacao = situacao;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
