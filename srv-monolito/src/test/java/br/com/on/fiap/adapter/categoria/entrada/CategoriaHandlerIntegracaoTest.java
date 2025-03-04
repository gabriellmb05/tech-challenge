package br.com.on.fiap.adapter.categoria.entrada;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.on.fiap.hexagono.domain.entity.Categoria;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
class CategoriaHandlerIntegracaoTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    @DisplayName(
            "Dado categorias de produtos, quando buscar as categorias dos produtos então deve retornar as categorias")
    void dadoCategoriasDeProdutos_quandoBuscarCategorias_entaoDeveRetornarCategorias() throws Exception {

        int quantidadeCategorias = Categoria.values().length;
        mockMvc.perform(get("/categorias").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categorias").isArray())
                .andExpect(jsonPath("$.categorias", hasSize(quantidadeCategorias)))
                .andReturn();
    }
}
