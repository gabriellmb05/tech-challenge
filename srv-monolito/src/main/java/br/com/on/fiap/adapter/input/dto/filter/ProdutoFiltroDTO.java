package br.com.on.fiap.adapter.input.dto.filter;

import br.com.on.fiap.core.domain.model.ProdutoFiltro;
import br.com.on.fiap.core.domain.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoFiltroDTO implements ProdutoFiltro {

    private Categoria categoria;
    private String nome;
}
