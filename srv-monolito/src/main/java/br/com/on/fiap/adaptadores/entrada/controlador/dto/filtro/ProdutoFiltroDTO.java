package br.com.on.fiap.adaptadores.entrada.controlador.dto.filtro;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoFiltroDTO {

    private String categoria;
    private String nome;
}
