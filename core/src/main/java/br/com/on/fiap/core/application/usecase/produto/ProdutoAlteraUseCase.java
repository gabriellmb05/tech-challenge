package br.com.on.fiap.core.application.usecase.produto;

import br.com.on.fiap.core.application.dto.entrada.produto.ProdutoEntrada;
import br.com.on.fiap.core.domain.Produto;

public interface ProdutoAlteraUseCase {

    Produto alterar(Long id, ProdutoEntrada produtoEntrada);
}
