package br.com.on.fiap.hexagono.dominio;

import java.math.BigDecimal;
import java.util.List;

public class Pedido {

	private List<RelPedidoProduto> produtos;
	private Pagamento pagamento;
	private Cliente cliente;
	private BigDecimal valor;

	public Pedido() {
	}

	public Pedido(List<RelPedidoProduto> produtos, Pagamento pagamento, Cliente cliente, BigDecimal valor) {
		this.produtos = produtos;
		this.pagamento = pagamento;
		this.cliente = cliente;
		this.valor = valor;
	}

	public void setProdutos(List<RelPedidoProduto> produtos) {
		this.produtos = produtos;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public List<RelPedidoProduto> getProdutos() {
		return produtos;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
