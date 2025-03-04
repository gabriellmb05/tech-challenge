package br.com.on.fiap.core.usecase.produto;

import br.com.on.fiap.core.domain.model.ProdutoEntrada;
import br.com.on.fiap.core.domain.model.Produto;

public interface ProdutoInsereUseCase {

    Produto inserir(ProdutoEntrada produtoEntrada);
}
