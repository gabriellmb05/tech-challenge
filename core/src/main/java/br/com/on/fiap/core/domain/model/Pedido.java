package br.com.on.fiap.core.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class Pedido {

    private Long id;
    private List<RelPedidoProduto> relPedidoProdutos;
    private Cliente cliente;
    private Pagamento pagamento;
    private SituacaoPedido situacao;
    private String protocolo;
    private LocalDateTime dataHora;

    public Pedido() {}

    public Pedido(
            Long id,
            List<RelPedidoProduto> relPedidoProdutos,
            Cliente cliente,
            Pagamento pagamento,
            SituacaoPedido situacao,
            String protocolo,
            LocalDateTime dataHora) {
        this.id = id;
        this.relPedidoProdutos = relPedidoProdutos;
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

    public List<RelPedidoProduto> getRelPedidoProdutos() {
        return relPedidoProdutos;
    }

    public void setRelPedidoProdutos(List<RelPedidoProduto> relPedidoProdutos) {
        this.relPedidoProdutos = relPedidoProdutos;
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
