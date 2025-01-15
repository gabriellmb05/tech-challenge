package br.com.on.fiap.adaptadores.entrada.controlador.dto;

public class ProdutoFiltroDTO {

	private String categoria;
	private String nome;

	public ProdutoFiltroDTO() {
	}

	public ProdutoFiltroDTO(String categoria, String nome) {
		this.categoria = categoria;
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
