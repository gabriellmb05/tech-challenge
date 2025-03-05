package br.com.on.fiap.core.application.usecase.produto;

import br.com.on.fiap.core.domain.model.Produto;

public interface ProdutoBuscaPorIdUseCase {
    Produto buscar(Long id);
}
