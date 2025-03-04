package br.com.on.fiap.hexagono.adapter.gateway.impl;

import br.com.on.fiap.hexagono.adapter.gateway.CategoriaGateway;
import br.com.on.fiap.hexagono.domain.entity.Categoria;
import java.util.Arrays;
import java.util.List;

public class CategoriaGatewayImpl implements CategoriaGateway {
    @Override
    public List<Categoria> buscaCategorias() {
        return Arrays.stream(Categoria.values()).toList();
    }
}
