package br.com.on.fiap.adaptadores.entrada.controlador.dto.filtro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoFiltroDTO {

    private String categoria;
    private String nome;
}
