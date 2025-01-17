package br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record ProdutoRespostaDTO(Long id, String nome, String categoria, BigDecimal preco, String ingredientes) {}
