package br.com.on.fiap.core.application.usecase.produto;

import br.com.on.fiap.core.domain.Produto;

public interface ProdutoBuscaPorIdUseCase {
    Produto buscar(Long id);
}
