package br.com.on.fiap.hexagono.usecases.interfaces.entrada.produto;

import br.com.on.fiap.hexagono.entities.entidades.Produto;

public interface BuscaProdutoPorIdCasoDeUso {
    Produto buscar(Long id);
}
