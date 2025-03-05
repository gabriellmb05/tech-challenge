package br.com.on.fiap.adapter.input.dto.filter;

import br.com.on.fiap.core.domain.model.Categoria;
import br.com.on.fiap.core.domain.model.ProdutoFiltro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoFiltroRequest implements ProdutoFiltro {

    private String nome;
    private Categoria categoria;
}
