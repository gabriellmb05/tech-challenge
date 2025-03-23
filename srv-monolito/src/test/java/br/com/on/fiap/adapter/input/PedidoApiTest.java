package br.com.on.fiap.adapter.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import br.com.on.datapool.DataPoolPedido;
import br.com.on.fiap.adapter.input.dto.filtro.PedidoFiltroRequest;
import br.com.on.fiap.core.adapter.controller.PedidoController;
import br.com.on.fiap.core.application.dto.resposta.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.PedidoDetalhadoResposta;
import br.com.on.fiap.core.application.dto.resposta.PedidoResposta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class PedidoApiTest {

    @Mock
    private PedidoController pedidoController;

    @InjectMocks
    private PedidoApi pedidoControlador;

    @Test
    @DisplayName("Dado pedido existente, quando atualizar a situação do pedido, então ele deve ser atualizado")
    void dadoPedidoExistente_quandoAtualizarSituacaoPedido_entaoDeveSerAtualizado() {

        String protocolo = "2025012010424756339";
        PedidoResposta pedidoResposta = PedidoResposta.create(DataPoolPedido.gerarPedido());

        when(pedidoController.atualizarPedido(protocolo)).thenReturn(pedidoResposta);

        ResponseEntity<PedidoResposta> response = pedidoControlador.atualizarPedido(protocolo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pedidoResposta, response.getBody());
    }

    @Test
    @DisplayName("Dado pedido existente, quando buscar o pedido, então ele deve ser retornado")
    void dadoPedidoExistente_quandoBuscarPedido_entaoDeveSerRetornado() {
        String protocolo = "2025012010424756339";
        PedidoDetalhadoResposta pedidoDetalhadoResposta = PedidoDetalhadoResposta.create(null, null, null, null, null);

        when(pedidoController.detalhaPedido(protocolo)).thenReturn(pedidoDetalhadoResposta);

        ResponseEntity<PedidoDetalhadoResposta> response = pedidoControlador.detalhaPedido(protocolo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pedidoDetalhadoResposta, response.getBody());
    }

    @Test
    @DisplayName("Dado pedido existente, quando buscar o pedido através do filtro, então ele deve ser retornado")
    void dadoPedidoExistente_quandoBuscarPedidoAtravesDoFiltro_entaoDeveSerRetornado() {
        Pageable paginacao = PageRequest.of(0, 10);
        PedidoFiltroRequest pedidoFiltroRequest = new PedidoFiltroRequest();
        PaginaResposta<PedidoResposta> pedidoPaginaResposta = PaginaResposta.create(null, null, null, null, null);

        when(pedidoController.listarPedidoComFiltro(Mockito.any(), Mockito.any()))
                .thenReturn(pedidoPaginaResposta);

        ResponseEntity<PaginaResposta<PedidoResposta>> resposta =
                pedidoControlador.listarPedidoComFiltro(pedidoFiltroRequest, paginacao);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(pedidoPaginaResposta, resposta.getBody());
    }

    @Test
    @DisplayName(
            "Dado pedidos existentes, quando buscar ordenado os pedidos, então eles devem ser retornados seguindo as regras de ordenação")
    void dadoPedidosExistentes_quandoBuscarOrdenadoOsPedido_entaoDevemSerRetornadosSeguindoRegrasOrdenacao() {
        Pageable paginacao = PageRequest.of(0, 10, Sort.by(Sort.Order.asc("nome")));
        PaginaResposta<PedidoResposta> pedidoPaginaResposta = PaginaResposta.create(null, null, null, null, null);

        when(pedidoController.listarPedidoComFiltro(Mockito.any())).thenReturn(pedidoPaginaResposta);

        ResponseEntity<PaginaResposta<PedidoResposta>> resposta = pedidoControlador.listarPedido(paginacao);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(pedidoPaginaResposta, resposta.getBody());
    }
}
