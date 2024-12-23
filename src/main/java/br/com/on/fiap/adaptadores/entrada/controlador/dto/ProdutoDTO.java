package br.com.on.fiap.adaptadores.entrada.controlador.dto;

import br.com.on.fiap.hexagono.dominio.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ProdutoDTO {

    private Long id;
    private String nome;
    private Categoria categoria;
    private BigDecimal preco;
}
