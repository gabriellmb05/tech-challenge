package br.com.on.fiap.core.application.dto;

import br.com.on.fiap.core.domain.entity.Categoria;

public interface ProdutoFiltro {
    String getNome();

    Categoria getCategoria();
}
