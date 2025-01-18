package br.com.on.fiap.adaptadores.entrada.controlador.dto.filtro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoFiltroDTO {

    private String categoria;
    private String nome;
}
