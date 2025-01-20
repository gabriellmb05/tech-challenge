package br.com.on.fiap.adaptadores.entrada.controlador;

import br.com.on.fiap.datapool.DataPoolPagamentoSolicitacaoDTO;
import br.com.on.fiap.datapool.DataPoolPedidoSolicitacaoDTO;
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

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PagamentoControladorIntegracaoTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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

        String protocolo = postResult
                .getResponse()
                .getContentAsString()
                .split("\"protocolo\":\"")[1]
                .split("\"")[0];

        mockMvc.perform(post("/pagamentos/{protocolo}", protocolo)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                DataPoolPagamentoSolicitacaoDTO.construirPagamento(1))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.situacao").value("APROVADO"))
                .andExpect(jsonPath("$.dhPagamento").value(notNullValue()))
                .andReturn();
    }

}
