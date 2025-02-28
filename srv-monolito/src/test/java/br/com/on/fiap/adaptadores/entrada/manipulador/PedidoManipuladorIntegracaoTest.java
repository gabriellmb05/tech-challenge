package br.com.on.fiap.adaptadores.entrada.manipulador;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.on.fiap.datapool.DataPoolPedidoSolicitacaoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PedidoManipuladorIntegracaoTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    @Transactional
    @DisplayName(
            "Dado um pedido novo, quando não informar um produto, então deve retornar erro de validação 'Os produtos precisam ser informados'")
    void dadoPedidoNovo_quandoInserirPedidoSemProduto_entaoDeveRetornarErroDeValidacao() throws Exception {
        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                DataPoolPedidoSolicitacaoDTO.construirPedidoSemProdutos())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value("Os produtos precisam ser informados"))
                .andReturn();
    }

    @Test
    @Order(2)
    @Transactional
    @DisplayName("Dado um pedido novo, quando inserir pedido com dados válidos, então deve ser salvo")
    void dadoPedidoNovo_quandoInserirPedidoComDadosValidos_entaoDeveSerSalvo() throws Exception {
        criarPedido();
    }

    @Test
    @Order(3)
    @Transactional
    @DisplayName("Dado um pedido inexistente, quando detalhar pedido com protocolo inválido, então deve retornar erro")
    void dadoPedidoInexistente_quandoDetalharPedidoComProtocoloInvalido_entaoDeveRetornarErro() throws Exception {
        mockMvc.perform(get("/pedidos/{protocolo}/detalhar", "protocolo-invalido")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(
                        jsonPath("$.errors[0]").value("Não foi encontrado o pedido para protocolo: protocolo-invalido"))
                .andReturn();
    }

    @Test
    @Order(3)
    @Transactional
    @DisplayName("Dado um pedido existente, quando buscar pedidos sem filtro, então deve retornar todos os pedidos")
    void dadoPedidosExistentes_quandoBuscarPedidosSemFiltro_entaoDeveRetornarTodosOsPedidos() throws Exception {
        criarPedido();
        mockMvc.perform(get("/pedidos").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.page.totalElements").value(1))
                .andReturn();
    }

    @Test
    @Order(4)
    @Transactional
    @DisplayName("Dado um pedido existente, quando consultar o pedido, então deve retornar o pedido")
    void dadoPedidoExistente_quandoConsultarPedido_entaoDeveRetornarPedido() throws Exception {
        MvcResult postResult = mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DataPoolPedidoSolicitacaoDTO.construirPedido())))
                .andExpect(status().isCreated())
                .andReturn();

        String protocolo = postResult
                .getResponse()
                .getContentAsString()
                .split("\"protocolo\":\"")[1]
                .split("\"")[0];

        mockMvc.perform(get("/pedidos/{protocolo}/detalhar", protocolo).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.situacao").value("REALIZADO"))
                .andReturn();
    }

    @Test
    @Order(5)
    @Transactional
    @DisplayName("Dado um pedido existente, quando buscar pedidos com filtro, então deve retornar pedidos filtrados")
    void dadoPedidoExistente_quandoBuscarPedidosComFiltro_entaoDeveRetornarPedidosFiltrados() throws Exception {
        criarPedido();
        mockMvc.perform(get("/pedidos").param("status", "PENDENTE").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andReturn();
    }

    private void criarPedido() throws Exception {
        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DataPoolPedidoSolicitacaoDTO.construirPedido())))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    @Order(6)
    @Transactional
    @DisplayName(
            "Dado um pedido existente em situação realizado, quando atualizar pedido, então deve ser atualizado com sucesso para próximo estágio")
    void dadoPedidoExistenteSituacaoRealizado_quandoAtualizarPedido_entaoDeveAtualizadoParaProximoEstágio()
            throws Exception {
        MvcResult postResult = mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DataPoolPedidoSolicitacaoDTO.construirPedido())))
                .andExpect(status().isCreated())
                .andReturn();

        String protocolo = postResult
                .getResponse()
                .getContentAsString()
                .split("\"protocolo\":\"")[1]
                .split("\"")[0];

        mockMvc.perform(put("/pedidos/{protocolo}", protocolo).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.protocolo").value(protocolo))
                .andExpect(jsonPath("$.situacao").value("APROVADO"));

        mockMvc.perform(put("/pedidos/{protocolo}", protocolo).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.protocolo").value(protocolo))
                .andExpect(jsonPath("$.situacao").value("EM_PREPARACAO"));

        mockMvc.perform(put("/pedidos/{protocolo}", protocolo).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.protocolo").value(protocolo))
                .andExpect(jsonPath("$.situacao").value("FINALIZADO"));
    }
}
