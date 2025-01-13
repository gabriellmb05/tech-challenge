package br.com.on.fiap.portas.entrada;

import br.com.on.fiap.dominio.Produto;

public interface AlteraProdutoPortaEntrada {

  Produto alterar(Long id, Produto produto);
}
