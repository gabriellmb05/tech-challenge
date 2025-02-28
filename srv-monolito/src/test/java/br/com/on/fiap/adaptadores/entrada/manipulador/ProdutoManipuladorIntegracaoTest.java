package br.com.on.fiap.adaptadores.entrada.manipulador;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.on.fiap.datapool.DataPoolProdutoSolicitacaoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProdutoManipuladorIntegracaoTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    @Order(1)
    @DisplayName(
            "Dado um produto novo, quando não informar o nome do produto, então deve retornar erro de validação 'O atributo nome é obrigatório'")
    void dadoProdutoNovo_quandoInserirProdutoSemInformarNome_entaoDeveRetornarErroDeValidacao() throws Exception {
        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DataPoolProdutoSolicitacaoDTO.gerarComNome(null))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value("O atributo nome é obrigatório"))
                .andReturn();
    }

    @Test
    @Transactional
    @Order(2)
    @DisplayName(
            "Dado um produto novo, quando nao informar a categoria do produto, então deve retornar erro de validação 'O atributo categoria é obrigatório'")
    void dadoProdutoNovo_quandoInserirProdutoSemInformarCategoria_entaoDeveRetornarErroDeValidacao() throws Exception {
        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                objectMapper.writeValueAsString(DataPoolProdutoSolicitacaoDTO.gerarComCategoria(null))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value("O atributo categoria é obrigatório"))
                .andReturn();
    }

    @Test
    @Transactional
    @Order(3)
    @DisplayName(
            "Dado um produto novo, quando nao informar o preço do produto, então deve retornar erro de validação 'O atributo preço é obrigatório'")
    void dadoProdutoNovo_quandoInserirProdutoSemPrecoCategoria_entaoDeveRetornarErroDeValidacao() throws Exception {
        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DataPoolProdutoSolicitacaoDTO.gerarComPreco(null))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value("O atributo preço é obrigatório"))
                .andReturn();
    }

    @Test
    @Transactional
    @Order(3)
    @DisplayName(
            "Dado um produto novo, quando informar uma categoria inválida, então deve retornar erro de validação 'Categoria (categoria) não encontrada'")
    void dadoProdutoNovo_quandoInserirProdutoComCategoriaInvalida_entaoDeveRetornarErroDeValidacao() throws Exception {
        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DataPoolProdutoSolicitacaoDTO.gerarNomeCategoriaPreco(
                                "Coca-cola", "inválida", BigDecimal.TEN))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value("Categoria (inválida) não encontrada"))
                .andReturn();
    }

    @Test
    @Transactional
    @Order(4)
    @DisplayName("Dado um produto novo, quando inserir o produto, então ele deve ser salvo")
    void dadoProdutoNovo_quandoInserirProduto_entaoDeveSerSalvo() throws Exception {
        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DataPoolProdutoSolicitacaoDTO.gerarNomeCategoriaPreco(
                                "Coca-cola", "BEBIDA", BigDecimal.TEN))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Coca-cola"))
                .andExpect(jsonPath("$.categoria").value("BEBIDA"))
                .andExpect(jsonPath("$.preco").value(10.0))
                .andReturn();
    }

    @Test
    @Transactional
    @Order(5)
    @DisplayName(
            "Dado um produto existente, quando atualizar o produto passando um id invalido, então deve retornar erro de validação 'Produto (id) não encontrado'")
    void dadoProdutoExistente_quandoAtualizarProdutoComIdInvalido_entaoDeveRetornarErro() throws Exception {
        mockMvc.perform(put("/produtos/{id}", 999999)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DataPoolProdutoSolicitacaoDTO.gerarNomeCategoriaPreco(
                                "Coca-cola", "BEBIDA", BigDecimal.TEN))))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errors[0]")
                        .value(Matchers.matchesPattern("Produto \\(999[.,]999\\) não encontrado")))
                .andReturn();
    }

    @Test
    @Transactional
    @Order(6)
    @DisplayName(
            "Dado um produto existente, quando atualizar o produto sem informar o nome do produto, então deve retornar erro de validação 'O atributo nome é obrigatório'")
    void dadoProdutoExistente_quandoAtualizarProdutoSemInformarNome_entaoDeveRetornarErroDeValidacao()
            throws Exception {
        mockMvc.perform(put("/produtos/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DataPoolProdutoSolicitacaoDTO.gerarComNome(null))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value("O atributo nome é obrigatório"))
                .andReturn();
    }

    @Test
    @Transactional
    @Order(7)
    @DisplayName(
            "Dado um produto existente, quando atualizar o produto sem informar o categoria do produto, então deve retornar erro de validação 'Categoria (categoria) não encontrada'")
    void dadoProdutoExistente_quandoAtualizarProdutoSemInformarCategoria_entaoDeveRetornarErroDeValidacao()
            throws Exception {
        mockMvc.perform(put("/produtos/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                objectMapper.writeValueAsString(DataPoolProdutoSolicitacaoDTO.gerarComCategoria(null))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value("O atributo categoria é obrigatório"))
                .andReturn();
    }

    @Test
    @Transactional
    @Order(8)
    @DisplayName(
            "Dado um produto existente, quando atualizar o produto sem informar o preço do produto, então deve retornar erro de validação 'O atributo preço é obrigatório'")
    void dadoProdutoExistente_quandoAtualizarProdutoSemInformarPreco_entaoDeveRetornarErroDeValidacao()
            throws Exception {
        mockMvc.perform(put("/produtos/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DataPoolProdutoSolicitacaoDTO.gerarComPreco(null))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value("O atributo preço é obrigatório"))
                .andReturn();
    }

    @Test
    @Transactional
    @Order(9)
    @DisplayName("Dado um produto existente, quando atualizar o produto, então ele deve ser atualizado")
    void dadoProdutoExistente_quandoAtualizarProduto_entaoDeveSerSalvo() throws Exception {
        mockMvc.perform(put("/produtos/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DataPoolProdutoSolicitacaoDTO.gerarNomeCategoriaPreco(
                                "Coca-cola", "BEBIDA", BigDecimal.TEN))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andReturn();
    }

    @Test
    @Transactional
    @Order(10)
    @DisplayName(
            "Dado um produto existente, quando deletar o produto informando id inválido, então deve retornar erro de validação 'Produto (id) não encontrado'")
    void dadoProdutoExistente_quandoDeletarProdutoComIdInvalido_entaoDeveRetornarErroDeValidacao() throws Exception {
        mockMvc.perform(delete("/produtos/{id}", 10).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    @Transactional
    @Order(11)
    @DisplayName("Dado um produto existente, quando deletar o produto, então deve ser deletado")
    void dadoProdutoExistente_quandoDeletarProduto_entaoDeveSerDeletado() throws Exception {
        mockMvc.perform(delete("/produtos/{id}", 1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    @Transactional
    @Order(12)
    @DisplayName(
            "Dado um produto existente, quando consultar o produto informando id inválido, então deve retornar erro de validação 'Produto (id) não encontrado'")
    void dadoProdutoExistente_quandoConsultarProdutoInformandoIdInvalido_entaoDeveRetornarErroDeValidacao()
            throws Exception {
        mockMvc.perform(get("/produtos/{id}", 999999).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errors[0]")
                        .value(Matchers.matchesPattern("Produto \\(999[.,]999\\) não encontrado")))
                .andReturn();
    }

    @Test
    @Transactional
    @Order(13)
    @DisplayName("Dado um produto existente, quando consultar o produto então deve retornar o produto")
    void dadoProdutoExistente_quandoConsultarProduto_entaoDeveRetornarProduto() throws Exception {
        mockMvc.perform(get("/produtos/{id}", 1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andReturn();
    }

    @Test
    @Transactional
    @Order(14)
    @DisplayName("Dado produtos existentes, quando listar produtos sem filtro, então deve retornar todos os produtos")
    void dadoProdutosExistentes_quandoListarProdutosSemFiltro_entaoDeveRetornarTodosOsProdutos() throws Exception {
        mockMvc.perform(get("/produtos").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.page.totalElements").value(8))
                .andReturn();
    }

    @Test
    @Transactional
    @Order(15)
    @DisplayName(
            "Dado produtos existentes, quando listar produtos com filtro de categoria, então deve retornar produtos filtrados")
    void dadoProdutosExistentes_quandoListarProdutosComFiltroDeCategoria_entaoDeveRetornarProdutosFiltrados()
            throws Exception {
        mockMvc.perform(get("/produtos?categoria=BEBIDA").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page.totalElements").value(1))
                .andExpect(jsonPath("$.content[0].categoria").value("BEBIDA"))
                .andReturn();
    }

    @Test
    @Transactional
    @Order(16)
    @DisplayName(
            "Dado produtos existentes, quando listar produtos com filtro de nome, então deve retornar produtos filtrados")
    void dadoProdutosExistentes_quandoListarProdutosComFiltroDeNome_entaoDeveRetornarProdutosFiltrados()
            throws Exception {
        mockMvc.perform(get("/produtos?nome=coca-cola lata").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page.totalElements").value(1))
                .andExpect(jsonPath("$.content[0].nome").value("coca-cola lata"))
                .andReturn();
    }

    @Test
    @Transactional
    @Order(18)
    @DisplayName(
            "Dado produtos existentes, quando listar produtos com múltiplos filtros, então deve retornar produtos filtrados")
    void dadoProdutosExistentes_quandoListarProdutosComMultiplosFiltros_entaoDeveRetornarProdutosFiltrados()
            throws Exception {
        mockMvc.perform(get("/produtos?categoria=LANCHE&nome=x-tudo").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page.totalElements").value(1))
                .andExpect(jsonPath("$.content[0].categoria").value("LANCHE"))
                .andExpect(jsonPath("$.content[0].nome").value("x-tudo"))
                .andReturn();
    }

    @Test
    @Transactional
    @Order(19)
    @DisplayName(
            "Dado um produto existente, quando consultar o produto informando filtrando por categoria inválida, então deve retornar erro de validação 'Categoria (categoria) não encontrada'")
    void dadoProdutoExistente_quandoConsultarFiltrandoPorCategoriaInvalida_entaoDeveRetornarErroDeValidacao()
            throws Exception {
        mockMvc.perform(get("/produtos?categoria=INVALIDA").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value("Categoria (INVALIDA) não encontrada"))
                .andReturn();
    }
}
