package br.com.on.fiap.core.application.usecase.produto;

import br.com.on.fiap.core.domain.entity.Produto;

public interface ProdutoAlteraUseCase {

    Produto alterar(Long id, Produto produto);
}
