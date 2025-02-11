package br.com.on.fiap.hexagono.casosdeuso.categoria;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import br.com.on.fiap.hexagono.dominio.Categoria;
import br.com.on.fiap.hexagono.gateways.CategoriaGateway;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BuscaCategoriasCasoDeUsoTest {

    @InjectMocks
    private BuscaCategoriasCasoDeUso buscaCategoriasCasoDeUso;

    @Mock
    private CategoriaGateway categoriaGateway;

    @Test
    @DisplayName("Dado categorias de produtos, quando buscar as categorias, então elas devem ser retornadas")
    void dadoCategorias_quandoBuscarCategorias_entaoDeveSerRetornado() {
        List<Categoria> categoriaRetorno = List.of(Categoria.values());
        when(categoriaGateway.buscaCategorias()).thenReturn(categoriaRetorno);
        List<Categoria> categorias = buscaCategoriasCasoDeUso.buscaCategorias();
        assertNotNull(categorias);
        assertArrayEquals(Categoria.values(), categorias.toArray());
    }
}
