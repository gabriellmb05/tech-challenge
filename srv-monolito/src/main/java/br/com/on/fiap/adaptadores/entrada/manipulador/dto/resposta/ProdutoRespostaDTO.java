package br.com.on.fiap.adaptadores.entrada.manipulador.dto.resposta;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRespostaDTO {

    private Long id;
    private String nome;
    private String categoria;
    private BigDecimal preco;
}
