package br.com.on.fiap.hexagono.adaptadores.gateways.impl;

import br.com.on.fiap.hexagono.adaptadores.gateways.CategoriaGateway;
import br.com.on.fiap.hexagono.entidades.Categoria;
import java.util.Arrays;
import java.util.List;

public class CategoriaGatewayImpl implements CategoriaGateway {
    @Override
    public List<Categoria> buscaCategorias() {
        return Arrays.stream(Categoria.values()).toList();
    }
}
