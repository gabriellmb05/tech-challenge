package br.com.on.fiap.adapter.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import br.com.on.fiap.core.adapter.controller.impl.CategoriaControllerImpl;
import br.com.on.fiap.core.application.dto.CategoriaRespostaDTO;
import br.com.on.fiap.core.domain.entity.Categoria;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class CategoriaApiTest {

    @Mock
    private CategoriaControllerImpl categoriaController;

    @InjectMocks
    private CategoriaApi categoriaApi;

    @Test
    @DisplayName("Dado categorias de produtos, quando buscar as categorias, então elas devem ser retornadas")
    void dadoCategoriasDeProdutos_quandoBuscarCategorias_entaoDevemSerRetornadas() {
        List<String> categoriasEsperadas =
                Arrays.stream(Categoria.values()).map(Categoria::name).toList();
        CategoriaRespostaDTO respostaEsperada = new CategoriaRespostaDTO(categoriasEsperadas);
        when(categoriaController.buscaCategorias()).thenReturn(respostaEsperada);

        ResponseEntity<CategoriaRespostaDTO> response = categoriaApi.buscaCategorias();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(new CategoriaRespostaDTO(categoriasEsperadas), response.getBody());
    }
}
