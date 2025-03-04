package br.com.on.fiap.hexagono.casodeuso.categoria;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import br.com.on.fiap.hexagono.adaptadores.gateways.CategoriaGateway;
import br.com.on.fiap.hexagono.entidades.Categoria;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BuscaCategoriasCasoDeUsoImplTest {

    @InjectMocks
    private BuscaCategoriasCasoDeUsoImpl buscaCategoriasCasoDeUsoImpl;

    @Mock
    private CategoriaGateway categoriaGateway;

    @Test
    @DisplayName("Dado categorias de produtos, quando buscar as categorias, então elas devem ser retornadas")
    void dadoCategorias_quandoBuscarCategorias_entaoDeveSerRetornado() {
        List<Categoria> categoriaRetorno = List.of(Categoria.values());
        when(categoriaGateway.buscaCategorias()).thenReturn(categoriaRetorno);
        List<Categoria> categorias = buscaCategoriasCasoDeUsoImpl.buscaCategorias();
        assertNotNull(categorias);
        assertArrayEquals(Categoria.values(), categorias.toArray());
    }
}
