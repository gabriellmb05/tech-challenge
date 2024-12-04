package br.com.on.fiap.aplicacao.adaptadores.dto;

import br.com.on.fiap.dominio.Categoria;
import lombok.Getter;

import java.math.BigDecimal;
@Getter
public class ProdutoDTO {

    private Long Id;
    private String nome;
    private Categoria categoria;
    private BigDecimal preco;
    private String ingredientes = "";

    public ProdutoDTO(Long id, String nome, Categoria categoria, BigDecimal preco, String ingredientes)
    {
        this.Id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.ingredientes = ingredientes;
    }
}