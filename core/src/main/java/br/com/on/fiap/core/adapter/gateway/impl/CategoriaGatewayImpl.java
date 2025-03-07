package br.com.on.fiap.core.adapter.gateway.impl;

import br.com.on.fiap.core.adapter.gateway.CategoriaGateway;
import br.com.on.fiap.core.domain.Categoria;
import java.util.Arrays;
import java.util.List;

public class CategoriaGatewayImpl implements CategoriaGateway {
    @Override
    public List<Categoria> buscaCategorias() {
        return Arrays.stream(Categoria.values()).toList();
    }
}
