package br.com.on.fiap.hexagono.usecase.produto;

import br.com.on.fiap.hexagono.domain.entity.Produto;

public interface ProdutoBuscaPorIdUseCase {
    Produto buscar(Long id);
}
