package br.com.on.fiap.adapter.input.dto.response;

import br.com.on.fiap.core.domain.model.ProdutoResposta;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponse implements ProdutoResposta {

    private Long id;
    private String nome;
    private String categoria;
    private BigDecimal preco;
}
