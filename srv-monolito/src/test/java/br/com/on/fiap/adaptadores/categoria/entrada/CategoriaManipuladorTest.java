package br.com.on.fiap.adaptadores.categoria.entrada;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import br.com.on.fiap.hexagono.adapter.controller.impl.CategoriaControllerImpl;
import br.com.on.fiap.hexagono.usecase.dto.CategoriaSaidaDTO;
import br.com.on.fiap.hexagono.domain.entity.Categoria;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class CategoriaManipuladorTest {

    @Mock
    private CategoriaControllerImpl categoriaControladorImpl;

    @InjectMocks
    private CategoriaManipulador categoriaManipulador;

    @Test
    @DisplayName("Dado categorias de produtos, quando buscar as categorias, então elas devem ser retornadas")
    void dadoCategoriasDeProdutos_quandoBuscarCategorias_entaoDevemSerRetornadas() {
        List<String> categoriasEsperadas =
                Arrays.stream(Categoria.values()).map(Categoria::name).toList();
        CategoriaSaidaDTO respostaEsperada = new CategoriaSaidaDTO(categoriasEsperadas);
        when(categoriaControladorImpl.buscaCategorias()).thenReturn(respostaEsperada);

        ResponseEntity<CategoriaSaidaDTO> response = categoriaManipulador.buscaCategorias();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(new CategoriaSaidaDTO(categoriasEsperadas), response.getBody());
    }
}
