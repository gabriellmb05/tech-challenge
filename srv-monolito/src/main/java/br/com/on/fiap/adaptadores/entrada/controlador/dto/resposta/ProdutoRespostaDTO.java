package br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoRespostaDTO {

    private Long id;
    private String nome;
    private String categoria;
    private BigDecimal preco;
}
