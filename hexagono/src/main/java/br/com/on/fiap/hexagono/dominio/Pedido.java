package br.com.on.fiap.hexagono.dominio;

import java.math.BigDecimal;
import java.util.List;

public class Pedido {

	private List<RelPedidoProduto> produtos;
	private Cliente cliente;
	private BigDecimal valor;

	public Pedido() {
	}

	public Pedido(List<RelPedidoProduto> produtos, Cliente cliente, BigDecimal valor) {
		this.produtos = produtos;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
