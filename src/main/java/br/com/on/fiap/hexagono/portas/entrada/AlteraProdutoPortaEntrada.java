package br.com.on.fiap.hexagono.portas.entrada;

import br.com.on.fiap.hexagono.dominio.Produto;

public interface AlteraProdutoPortaEntrada {

    public Produto alterar(Long id, Produto produto);
}