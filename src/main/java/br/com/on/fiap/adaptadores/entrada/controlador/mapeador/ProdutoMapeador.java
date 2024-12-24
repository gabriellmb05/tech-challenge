package br.com.on.fiap.adaptadores.entrada.controlador.mapeador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.CategoriaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.ProdutoDTO;
import br.com.on.fiap.hexagono.dominio.Categoria;
import br.com.on.fiap.hexagono.dominio.Produto;

public class ProdutoMapeador {

    public static ProdutoDTO produtoParaProdutoDTO(Produto produto){
        CategoriaDTO categoriaDTO = CategoriaDTO.valueOf(produto.getCategoria().name());
        return new ProdutoDTO(produto.getId(),
                produto.getNome(),
                categoriaDTO,
                produto.getPreco());
    }

    public static Produto produtoDTOParaProduto(ProdutoDTO produtoDTO){
        Categoria categoria = Categoria.valueOf(produtoDTO.categoria().name());
        return new Produto(produtoDTO.nome(),
                categoria,
                produtoDTO.preco());
    }
}
