package br.com.on.fiap.adaptadores.entrada.controlador.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProdutoRespostaDTO(Long id, String nome, String categoria, BigDecimal preco) implements ProdutoBaseDTO {

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public String getCategoria() {
		return categoria;
	}

	@Override
	public BigDecimal getPreco() {
		return preco;
	}
}
