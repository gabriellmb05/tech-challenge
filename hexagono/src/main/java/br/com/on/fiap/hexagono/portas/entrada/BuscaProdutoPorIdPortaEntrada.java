package br.com.on.fiap.hexagono.portas.entrada;

import br.com.on.fiap.hexagono.dominio.Produto;

public interface BuscaProdutoPorIdPortaEntrada {
    Produto buscar(Long id);
}