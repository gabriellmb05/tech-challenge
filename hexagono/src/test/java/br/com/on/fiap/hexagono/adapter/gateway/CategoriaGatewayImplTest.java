package br.com.on.fiap.hexagono.adapter.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.on.fiap.hexagono.adapter.gateway.base.CategoriaGateway;
import br.com.on.fiap.hexagono.domain.entity.Categoria;
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
