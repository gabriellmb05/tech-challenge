package br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta;

import java.util.List;
import lombok.Builder;

@Builder
public record CategoriaRespostaDTO(List<String> categorias) {}
