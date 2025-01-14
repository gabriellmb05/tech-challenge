package br.com.on.fiap.hexagono.portas.entrada.produto;

import br.com.on.fiap.hexagono.dominio.Produto;

public interface AlteraProdutoPortaEntrada {

	Produto alterar(Long id, Produto produto);
}
