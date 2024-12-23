package br.com.on.fiap.adaptadores.entrada.controlador.mapeador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.ProdutoDTO;
import br.com.on.fiap.hexagono.dominio.Produto;

public class ProdutoMapeador {

    public static ProdutoDTO produtoParaProdutoDTO(Produto produto){
        return new ProdutoDTO(produto.getId(),
                produto.getNome(),
                produto.getCategoria(),
                produto.getPreco());
    }

    public static Produto produtoDTOParaProduto(ProdutoDTO produtoDTO){
        return new Produto(produtoDTO.getNome(),
                produtoDTO.getCategoria(),
                produtoDTO.getPreco());
    }
}
