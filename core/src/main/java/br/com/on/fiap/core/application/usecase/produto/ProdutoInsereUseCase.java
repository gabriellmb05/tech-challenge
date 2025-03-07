package br.com.on.fiap.core.application.usecase.produto;

import br.com.on.fiap.core.application.dto.entrada.produto.ProdutoEntrada;
import br.com.on.fiap.core.domain.Produto;

public interface ProdutoInsereUseCase {

    Produto inserir(ProdutoEntrada produtoEntrada);
}
