package br.com.on.fiap.portas.entrada;

import br.com.on.fiap.dominio.Produto;

public interface BuscaProdutoPorIdPortaEntrada {
  Produto buscar(Long id);
}
