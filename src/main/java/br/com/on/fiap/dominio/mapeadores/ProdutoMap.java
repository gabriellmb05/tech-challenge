package br.com.on.fiap.dominio.mapeadores;

import br.com.on.fiap.aplicacao.adaptadores.dto.ProdutoDTO;
import br.com.on.fiap.dominio.Produto;

public class ProdutoMap {
    public static ProdutoDTO mapToProdutoDTO (Produto produto){
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getCategoria(),
                produto.getPreco(),
                produto.getIngredientes());
    }
    public static Produto mapToProduto (ProdutoDTO dto){
        return new Produto(dto);
    }
}