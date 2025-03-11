package br.com.on.fiap.adapter.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import br.com.on.fiap.core.adapter.controller.impl.CategoriaControllerImpl;
import br.com.on.fiap.core.application.dto.resposta.CategoriaResposta;
import br.com.on.fiap.core.domain.Categoria;
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
        CategoriaResposta respostaEsperada = new CategoriaResposta(categoriasEsperadas);
        when(categoriaController.buscaCategorias()).thenReturn(respostaEsperada);

        ResponseEntity<CategoriaResposta> response = categoriaApi.buscaCategorias();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(new CategoriaResposta(categoriasEsperadas), response.getBody());
    }
}
