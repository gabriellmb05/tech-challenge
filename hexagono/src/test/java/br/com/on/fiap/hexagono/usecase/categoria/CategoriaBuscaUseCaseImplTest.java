package br.com.on.fiap.hexagono.usecase.categoria;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import br.com.on.fiap.hexagono.adapter.gateway.CategoriaGateway;
import br.com.on.fiap.hexagono.application.usecase.categoria.CategoriaBuscaUseCaseImpl;
import br.com.on.fiap.hexagono.domain.entity.Categoria;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CategoriaBuscaUseCaseImplTest {

    @InjectMocks
    private CategoriaBuscaUseCaseImpl categoriaBuscaUseCaseImpl;

    @Mock
    private CategoriaGateway categoriaGateway;

    @Test
    @DisplayName("Dado categorias de produtos, quando buscar as categorias, então elas devem ser retornadas")
    void dadoCategorias_quandoBuscarCategorias_entaoDeveSerRetornado() {
        List<Categoria> categoriaRetorno = List.of(Categoria.values());
        when(categoriaGateway.buscaCategorias()).thenReturn(categoriaRetorno);
        List<Categoria> categorias = categoriaBuscaUseCaseImpl.buscaCategorias();
        assertNotNull(categorias);
        assertArrayEquals(Categoria.values(), categorias.toArray());
    }
}
