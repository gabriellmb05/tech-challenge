package br.com.on.fiap.adaptadores.saida.persistencia.mapeador;

import br.com.on.fiap.adaptadores.saida.persistencia.repositorio.entidade.ProdutoEntidade;
import br.com.on.fiap.hexagono.dominio.Produto;

public class ProdutoEntidadeMapeador {


    public static Produto produtoEntidadeParaProduto(ProdutoEntidade produtoEntidade){
        return new Produto(produtoEntidade.getId(),
                produtoEntidade.getNome(),
                produtoEntidade.getCategoria(),
                produtoEntidade.getPreco());
    }

    public static ProdutoEntidade produtoParaProdutoEntidade(Produto produto){
        return new ProdutoEntidade(produto.getId(),
                produto.getNome(),
                produto.getCategoria(),
                produto.getPreco());
    }
}
