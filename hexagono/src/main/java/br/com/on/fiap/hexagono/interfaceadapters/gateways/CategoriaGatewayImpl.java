package br.com.on.fiap.hexagono.interfaceadapters.gateways;

import br.com.on.fiap.hexagono.entities.entidades.Categoria;
import br.com.on.fiap.hexagono.usecases.interfaces.gateway.categoria.CategoriaGateway;
import java.util.Arrays;
import java.util.List;

public class CategoriaGatewayImpl implements CategoriaGateway {
    @Override
    public List<Categoria> buscaCategorias() {
        return Arrays.stream(Categoria.values()).toList();
    }
}
