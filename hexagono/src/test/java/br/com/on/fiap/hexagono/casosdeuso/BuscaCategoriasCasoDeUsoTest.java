package br.com.on.fiap.hexagono.casosdeuso;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import br.com.on.fiap.hexagono.casosdeuso.produto.BuscaCategoriasCasoDeUso;
import br.com.on.fiap.hexagono.dominio.Categoria;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BuscaCategoriasCasoDeUsoTest {

    @Test
    @DisplayName("Dado categorias de produtos, quando buscar as categorias, então elas devem ser retornadas")
    void dadoCategorias_quandoBuscarCategorias_entaoDeveSerRetornado() {
        BuscaCategoriasCasoDeUso buscaCategoriasCasoDeUso = new BuscaCategoriasCasoDeUso();
        List<Categoria> categorias = buscaCategoriasCasoDeUso.buscaCategorias();
        assertNotNull(categorias);
        assertArrayEquals(Categoria.values(), categorias.toArray());
    }
}
