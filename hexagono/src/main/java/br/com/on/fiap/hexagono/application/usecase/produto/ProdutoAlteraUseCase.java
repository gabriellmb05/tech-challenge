package br.com.on.fiap.hexagono.application.usecase.produto;

import br.com.on.fiap.hexagono.domain.entity.Produto;

public interface ProdutoAlteraUseCase {

    Produto alterar(Long id, Produto produto);
}
