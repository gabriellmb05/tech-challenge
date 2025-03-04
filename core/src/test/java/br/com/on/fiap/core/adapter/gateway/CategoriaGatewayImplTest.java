package br.com.on.fiap.core.adapter.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.on.fiap.core.adapter.gateway.impl.CategoriaGatewayImpl;
import br.com.on.fiap.core.domain.model.Categoria;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CategoriaGatewayImplTest {

    @InjectMocks
    private CategoriaGatewayImpl categoriaGateway;

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
