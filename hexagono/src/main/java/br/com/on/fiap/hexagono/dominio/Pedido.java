package br.com.on.fiap.hexagono.dominio;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Pedido {

    private Long id;
    private List<RelPedidoProduto> relPedidoProdutos;
    private Cliente cliente;
    private BigDecimal valor;
    private SituacaoPedido situacao;
    private String protocolo;
    private LocalDateTime dataHora;

    public Pedido() {}

    public Pedido(
            Long id,
            List<RelPedidoProduto> relPedidoProdutos,
            Cliente cliente,
            BigDecimal valor,
            SituacaoPedido situacao,
            String protocolo,
            LocalDateTime dataHora) {
        this.id = id;
        this.relPedidoProdutos = relPedidoProdutos;
        this.cliente = cliente;
        this.valor = valor;
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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
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
