package br.com.on.fiap.hexagono.casosdeuso.produto;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.on.fiap.hexagono.dominio.Categoria;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BuscaCategoriasCasoDeUsoTest {

    @InjectMocks
    private BuscaCategoriasCasoDeUso buscaCategoriasCasoDeUso;

    @Test
    @DisplayName("Dado categorias de produtos, quando buscar as categorias, então elas devem ser retornadas")
    void dadoCategorias_quandoBuscarCategorias_entaoDeveSerRetornado() {
        List<Categoria> categorias = buscaCategoriasCasoDeUso.buscaCategorias();
        assertNotNull(categorias);
        assertArrayEquals(Categoria.values(), categorias.toArray());
    }
}
