package br.com.on.fiap.adaptadores.entrada.controlador.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProdutoRespostaDTO(Long id, String nome, String categoria, BigDecimal preco, String ingredientes) {
}
