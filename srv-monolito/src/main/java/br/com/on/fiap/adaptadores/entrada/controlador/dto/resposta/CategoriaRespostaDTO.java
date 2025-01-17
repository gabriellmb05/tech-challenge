package br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta;

import lombok.Builder;

import java.util.List;

@Builder
public record CategoriaRespostaDTO(List<String> categorias) {
}
