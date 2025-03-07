package br.com.on.fiap.adapter.input.dto.filter;

import br.com.on.fiap.core.application.dto.entrada.ProdutoFiltro;
import br.com.on.fiap.core.domain.Categoria;
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
