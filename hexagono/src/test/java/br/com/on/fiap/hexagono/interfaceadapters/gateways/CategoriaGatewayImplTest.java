package br.com.on.fiap.hexagono.interfaceadapters.gateways;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.on.fiap.hexagono.entities.entidades.Categoria;
import br.com.on.fiap.hexagono.usecases.interfaces.gateway.categoria.CategoriaGateway;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CategoriaGatewayImplTest {

    private final CategoriaGateway categoriaGateway = new CategoriaGatewayImpl();

    @Test
    @DisplayName("Quando buscar categorias, então deve retornar todas as categorias disponíveis")
    void quandoBuscarCategorias_entaoDeveRetornarTodasAsCategoriasDisponiveis() {
        List<Categoria> categorias = categoriaGateway.buscaCategorias();

        assertNotNull(categorias);
        assertEquals(Categoria.values().length, categorias.size());
        for (Categoria categoria : Categoria.values()) {
            assertEquals(categoria, categorias.get(categoria.ordinal()));
        }
    }
}
