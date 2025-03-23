package br.com.on.fiap.adapter.input;

import br.com.on.datapool.DataPoolPagamentoSolicitacaoDTO;
import br.com.on.datapool.DataPoolPedidoQuantidadeSolicitacaoDTO;
import br.com.on.datapool.DataPoolPedidoSolicitacaoDTO;
import br.com.on.fiap.adapter.input.dto.entrada.PedidoQuantidadeRequest;
import br.com.on.fiap.adapter.input.dto.entrada.PedidoRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PedidoApiIntegracaoTest {

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
                .andExpect(jsonPath("$.conteudo").isArray())
                .andExpect(jsonPath("$.totalElementos").value(1))
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
        mockMvc.perform(get("/pedidos").param("situacao", "1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.conteudo", hasSize(1)))
                .andReturn();
    }

    @Test
    @Order(6)
    @Transactional
    @DisplayName("Dado pedidos existentes, quando buscar ordenado os pedidos, então eles devem ser retornados seguindo as regras de ordenação")
    void dadoPedidosExistentes_quandoBuscarOrdenadoOsPedido_entaoDevemSerRetornadosSeguindoRegrasOrdenacao() throws Exception {
        int tamanhoLote = 3;
        criarPedidosEmLote(tamanhoLote);

        //Busca inicial dos pedidos recém criados
        MvcResult resultadoPrimeiraConsulta = buscarOrdenadoEValidarSituacoes(tamanhoLote, "REALIZADO", "REALIZADO", "REALIZADO");

        // Atualiza o primeiro pedido para a situação "APROVADO" e valida a nova ordem
        atualizarPedidoEValidarOrdem(tamanhoLote, resultadoPrimeiraConsulta, 0, "APROVADO", "REALIZADO", "REALIZADO");

        // Atualiza o segundo pedido para a situação "APROVADO" e valida a nova ordem
        atualizarPedidoEValidarOrdem(tamanhoLote, resultadoPrimeiraConsulta, 1, "APROVADO", "APROVADO", "REALIZADO");

        // Atualiza novamente o segundo pedido para a situação "EM_PREPARACAO" e valida a nova ordem
        atualizarPedidoEValidarOrdem(tamanhoLote, resultadoPrimeiraConsulta, 1, "EM_PREPARACAO", "APROVADO", "REALIZADO");
    }

    private MvcResult buscarOrdenadoEValidarSituacoes(int tamanho, String... situacoesEsperadas) throws Exception {
        return mockMvc.perform(get("/pedidos/ordenar")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.conteudo", hasSize(tamanho)))
                .andExpect(jsonPath("$.conteudo[0].situacao").value(situacoesEsperadas[0]))
                .andExpect(jsonPath("$.conteudo[1].situacao").value(situacoesEsperadas[1]))
                .andExpect(jsonPath("$.conteudo[2].situacao").value(situacoesEsperadas[2]))
                .andReturn();
    }

    private void atualizarPedidoEValidarOrdem(int tamanhoLote, MvcResult resultadoPrimeiraConsulta,
                                              int indicePedidoCriado, String... situacoesEsperadas) throws Exception {
        mockMvc.perform(put("/pedidos/{protocolo}", obterProtocoloDoRetorno(resultadoPrimeiraConsulta, indicePedidoCriado))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        buscarOrdenadoEValidarSituacoes(tamanhoLote, situacoesEsperadas);
    }

    @Test
    @Order(7)
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
                .andExpect(jsonPath("$.situacao").value("PRONTO"));

        mockMvc.perform(put("/pedidos/{protocolo}", protocolo).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.protocolo").value(protocolo))
                .andExpect(jsonPath("$.situacao").value("FINALIZADO"));
    }

    private void criarPedido() throws Exception {
        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DataPoolPedidoSolicitacaoDTO.construirPedido())))
                .andExpect(status().isCreated())
                .andReturn();
    }

    private void criarPedidosEmLote(int qtdPedidosNoLote) throws Exception {
        for (int i = 0; i < qtdPedidosNoLote; i++) {
            PedidoQuantidadeRequest pedido = DataPoolPedidoQuantidadeSolicitacaoDTO.construirProduto(ThreadLocalRandom.current().nextLong(1, 4),
                    ThreadLocalRandom.current().nextLong(1, 10));

            PedidoRequest pedidoRequest = DataPoolPedidoSolicitacaoDTO.construirPedidoComParametros(1L,
                    Collections.singletonList(pedido), DataPoolPagamentoSolicitacaoDTO.construirPagamento(3));

            mockMvc.perform(post("/pedidos")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(pedidoRequest)))
                    .andExpect(status().isCreated())
                    .andReturn();
        }
    }

    private String obterProtocoloDoRetorno(MvcResult postResult, int posicao) throws Exception {
        List<LinkedHashMap<String, String>> conteudo =
                (List<LinkedHashMap<String, String>>) new ObjectMapper().readValue(postResult.getResponse().getContentAsString(), Map.class).get("conteudo");

        return conteudo.get(posicao).get("protocolo");
    }
}
