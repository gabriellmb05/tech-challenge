package br.com.on.fiap.hexagono.dominio;

import java.math.BigDecimal;

public class Produto {
	private Long id;
	private String nome;
	private Categoria categoria;
	private BigDecimal preco;

	public Produto() {
	}

	public Produto(Long id, String nome, Categoria categoria, BigDecimal preco) {
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
		this.preco = preco;
	}

	public Produto(String nome, Categoria categoria, BigDecimal preco) {
		this.nome = nome;
		this.categoria = categoria;
		this.preco = preco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
}
