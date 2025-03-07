package br.com.on.fiap.adapter.input.dto.filter;

import br.com.on.fiap.core.application.dto.filtro.produto.ProdutoFiltroEntrada;
import br.com.on.fiap.core.domain.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoFiltroEntradaRequest implements ProdutoFiltroEntrada {

    private String nome;
    private Categoria categoria;
}
