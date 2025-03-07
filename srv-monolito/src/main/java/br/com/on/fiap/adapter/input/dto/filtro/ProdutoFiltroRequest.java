package br.com.on.fiap.adapter.input.dto.filtro;

import br.com.on.fiap.core.application.dto.filtro.ProdutoFiltroEntrada;
import br.com.on.fiap.core.domain.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoFiltroRequest implements ProdutoFiltroEntrada {

    private String nome;
    private Categoria categoria;
}
