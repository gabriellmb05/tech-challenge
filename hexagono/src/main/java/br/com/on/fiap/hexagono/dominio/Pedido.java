package br.com.on.fiap.hexagono.dominio;

import java.math.BigDecimal;
import java.util.List;

public class Pedido {

	private Long id;
	private List<RelPedidoProduto> relPedidoProdutos;
	private Cliente cliente;
	private BigDecimal valor;
	private Long situacao;

	public Pedido() {
	}

	public Pedido(Long id, List<RelPedidoProduto> relPedidoProdutos, Cliente cliente, BigDecimal valor, Long situacao) {
		this.id = id;
		this.relPedidoProdutos = relPedidoProdutos;
		this.cliente = cliente;
		this.valor = valor;
		this.situacao = situacao;
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

	public Long getSituacao() {
		return situacao;
	}

	public void setSituacao(Long situacao) {
		this.situacao = situacao;
	}
}
