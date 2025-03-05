package br.com.on.fiap.core.application.usecase.produto;

import br.com.on.fiap.core.domain.model.Produto;
import br.com.on.fiap.core.domain.model.ProdutoEntrada;

public interface ProdutoInsereUseCase {

    Produto inserir(ProdutoEntrada produtoEntrada);
}
