package br.com.on.fiap.core.usecase.produto;

import br.com.on.fiap.core.domain.model.ProdutoEntrada;
import br.com.on.fiap.core.domain.model.Produto;

public interface ProdutoAlteraUseCase {

    Produto alterar(Long id, ProdutoEntrada produtoEntrada);
}
