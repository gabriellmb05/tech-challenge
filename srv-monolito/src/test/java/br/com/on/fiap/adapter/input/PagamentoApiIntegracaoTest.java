package br.com.on.fiap.adapter.input;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.on.datapool.DataPoolPagamentoSolicitacaoDTO;
import br.com.on.datapool.DataPoolPedidoSolicitacaoDTO;
import br.com.on.fiap.adapter.output.api.mercadopago.client.IntegracaoPagamento;
import br.com.on.fiap.adapter.output.api.mercadopago.dto.PagamentoRespostaIntegracaoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PagamentoApiIntegracaoTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private IntegracaoPagamento integracaoPagamento;

    @Test
    @Order(1)
    @Transactional
    @DisplayName(
            "Dado um pedido realizado, quando atualizar pagamento, então deve ser atualizado a situacao e a data do mesmo")
    void dadoPedidoRealizado_quandoAtualizarPagamento_entaoDeveSerAtualizadoSituacaoData() throws Exception {
        MvcResult postResult = mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DataPoolPedidoSolicitacaoDTO.construirPedido())))
                .andExpect(status().isCreated())
                .andReturn();

        when(integracaoPagamento.enviarPagamento(Mockito.any()))
                .thenReturn(ResponseEntity.ok(
                        PagamentoRespostaIntegracaoDTO.builder().build()));

        String protocolo = postResult
                .getResponse()
                .getContentAsString()
                .split("\"protocolo\":\"")[1]
                .split("\"")[0];

        mockMvc.perform(put("/pagamentos/{protocolo}", protocolo)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                objectMapper.writeValueAsString(DataPoolPagamentoSolicitacaoDTO.construirPagamento(1))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stPagamento").value("APROVADO"))
                .andExpect(jsonPath("$.dhPagamento").value(notNullValue()))
                .andReturn();
    }

    @Test
    @Order(2)
    @Transactional
    @DisplayName(
            "Dado um pedido realizado, quando atualizar pagamento e api externa estiver indisponivel, então deve ser retornado erro")
    void dadoPedidoRealizado_quandoAtualizarPagamentoEApiExternaIndisponivel_entaoDeveSerRetornadoErro()
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

        mockMvc.perform(put("/pagamentos/{protocolo}", protocolo)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                objectMapper.writeValueAsString(DataPoolPagamentoSolicitacaoDTO.construirPagamento(1))))
                .andExpect(status().isBadRequest())
                .andExpect(
                        jsonPath("$.errors[0]")
                                .value(
                                        "Erro ao realizar pagamento, tente novamente. Se o erro persistir entre em contato com o provedor."));
    }

    @Test
    @Order(3)
    @Transactional
    @DisplayName(
            "Dado um pedido realizado, quando atualizar pagamento e api externa retornar erro, então deve ser retornado erro")
    void dadoPedidoRealizado_quandoAtualizarPagamentoEApiExternaIndisponivelRetornarErro_entaoDeveSerRetornadoErro()
            throws Exception {
        MvcResult postResult = mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(DataPoolPedidoSolicitacaoDTO.construirPedido())))
                .andExpect(status().isCreated())
                .andReturn();

        when(integracaoPagamento.enviarPagamento(Mockito.any()))
                .thenReturn(ResponseEntity.badRequest().body(null));

        String protocolo = postResult
                .getResponse()
                .getContentAsString()
                .split("\"protocolo\":\"")[1]
                .split("\"")[0];

        mockMvc.perform(put("/pagamentos/{protocolo}", protocolo)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                objectMapper.writeValueAsString(DataPoolPagamentoSolicitacaoDTO.construirPagamento(1))))
                .andExpect(status().isBadRequest())
                .andExpect(
                        jsonPath("$.errors[0]")
                                .value(
                                        "Erro ao realizar pagamento, tente novamente. Se o erro persistir entre em contato com o provedor."));
    }
}
