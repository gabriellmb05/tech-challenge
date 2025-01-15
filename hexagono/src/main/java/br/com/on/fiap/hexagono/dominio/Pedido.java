package br.com.on.fiap.hexagono.dominio;

import java.util.List;

public class Pedido {

	private List<Produto> listaItems;
	private Pagamento pagamento;
	private Cliente cliente;

	public Pedido() {
	}

	public Pedido(List<Produto> listaItems, Pagamento pagamento, Cliente cliente) {
		this.listaItems = listaItems;
		this.pagamento = pagamento;
		this.cliente = cliente;
	}

	public List<Produto> getListaItems() {
		return listaItems;
	}

	public void setListaItems(List<Produto> listaItems) {
		this.listaItems = listaItems;
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
