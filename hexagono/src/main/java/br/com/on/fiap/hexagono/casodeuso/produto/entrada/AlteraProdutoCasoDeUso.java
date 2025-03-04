package br.com.on.fiap.hexagono.casodeuso.produto.entrada;

import br.com.on.fiap.hexagono.entidades.Produto;

public interface AlteraProdutoCasoDeUso {

    Produto alterar(Long id, Produto produto);
}
