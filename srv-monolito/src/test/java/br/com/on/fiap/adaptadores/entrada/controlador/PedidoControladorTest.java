package br.com.on.fiap.adaptadores.entrada.controlador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.filtro.PedidoFiltroDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta.PedidoDetalhadoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta.PedidoRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.mapeador.PedidoEntradaMapeador;
import br.com.on.fiap.adaptadores.entrada.controlador.mapeador.filtro.PedidoFiltroEntradaMapeador;
import br.com.on.fiap.datapool.*;
import br.com.on.fiap.hexagono.dominio.*;
import br.com.on.fiap.hexagono.portas.entrada.pedido.AtualizaPedidoPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.pedido.BuscaPedidosPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.pedido.DetalhaPedidoPortaEntrada;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class PedidoControladorTest {

    @Mock
    private AtualizaPedidoPortaEntrada atualizaPedidoPortaEntrada;

    @Mock
    private PedidoEntradaMapeador pedidoEntradaMapeador;

    @Mock
    private DetalhaPedidoPortaEntrada detalhaPedidoPortaEntrada;

    @Mock
    private PedidoFiltroEntradaMapeador pedidoFiltroEntradaMapeador;

    @Mock
    private BuscaPedidosPortaEntrada buscaPedidosPortaEntrada;

    @InjectMocks
    private PedidoControlador pedidoControlador;

    static Stream<Arguments> pedidoFiltroProvider() {
        return Stream.of(
                Arguments.of(
                        DataPoolPedidoFiltroDTO.gerarPedido1(),
                        DataPoolPedidoFiltro.gerarPedidoFiltro(),
                        Collections.emptyList(),
                        Collections.emptyList()),
                Arguments.of(
                        DataPoolPedidoFiltroDTO.gerarPedido1(),
                        DataPoolPedidoFiltro.gerarPedidoFiltro(),
                        List.of(DataPoolPedido.gerarPedido()),
                        List.of(DataPoolPedidoRespostaDTO.gerarPedido()),
                        Arguments.of(
                                DataPoolPedidoFiltroDTO.gerarPedido1(),
                                DataPoolPedidoFiltro.gerarPedidoFiltro(),
                                DataPoolPedido.gerarListaPedidos(),
                                DataPoolPedidoRespostaDTO.gerarListaPedidoRespostaDTO())));
    }

    @Test
    @DisplayName("Dado pedido existente, quando atualizar a situação do pedido, então ele deve ser atualizado")
    void dadoPedidoExistente_quandoAtualizarSituacaoPedido_entaoDeveSerAtualizado() {

        String protocolo = "2025012010424756339";
        Pedido pedido = new Pedido();
        PedidoRespostaDTO pedidoRespostaDTO = DataPoolPedidoRespostaDTO.gerarPedido();
        when(atualizaPedidoPortaEntrada.atualizarPedido(protocolo)).thenReturn(pedido);
        when(pedidoEntradaMapeador.paraPedidoDTO(pedido)).thenReturn(pedidoRespostaDTO);

        ResponseEntity<PedidoRespostaDTO> response = pedidoControlador.atualizarPedido(protocolo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pedidoRespostaDTO, response.getBody());
    }

    @Test
    @DisplayName("Dado pedido existente, quando buscar o pedido, então ele deve ser retornado")
    void dadoPedidoExistente_quandoBuscarPedido_entaoDeveSerRetornado() {

        String protocolo = "2025012010424756339";
        Pedido pedido = new Pedido();
        PedidoDetalhadoRespostaDTO pedidoRespostaDTO = DataPoolPedidoDetalheRespostaDTO.gerarPedidoDetalhe();
        when(detalhaPedidoPortaEntrada.detalhaPedido(protocolo)).thenReturn(pedido);
        when(pedidoEntradaMapeador.paraPedidoDetalheDTO(pedido)).thenReturn(pedidoRespostaDTO);

        ResponseEntity<PedidoDetalhadoRespostaDTO> response = pedidoControlador.detalhaPedido(protocolo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pedidoRespostaDTO, response.getBody());
    }

    @ParameterizedTest
    @MethodSource("pedidoFiltroProvider")
    @DisplayName("Dado pedido existente, quando buscar o pedido através do filtro, então ele deve ser retornado")
    void dadoPedidoExistente_quandoBuscarPedidoAtravesDoFiltro_entaoDeveSerRetornado(
            PedidoFiltroDTO filtroDTO,
            PedidoFiltro filtro,
            List<Pedido> pedidos,
            List<PedidoRespostaDTO> pedidoRespostaDTOs) {

        Pageable paginacao = PageRequest.of(0, 10);
        Page<Pedido> pedidoPAge = new PageImpl<>(pedidos, paginacao, pedidos.size());
        Page<PedidoRespostaDTO> pedidoRespostaPage =
                new PageImpl<>(pedidoRespostaDTOs, paginacao, pedidoRespostaDTOs.size());

        when(pedidoFiltroEntradaMapeador.paraPedidoFiltro(filtroDTO)).thenReturn(filtro);
        when(buscaPedidosPortaEntrada.buscarPedidosComFiltro(filtro, paginacao)).thenReturn(pedidoPAge);
        pedidos.forEach(pedido -> when(pedidoEntradaMapeador.paraPedidoDTO(pedido))
                .thenReturn(pedidoRespostaDTOs.get(pedidos.indexOf(pedido))));

        ResponseEntity<PagedModel<PedidoRespostaDTO>> response =
                pedidoControlador.buscaPedidosPaginado(filtroDTO, paginacao);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pedidoRespostaPage.getContent(), response.getBody().getContent());
        verify(pedidoFiltroEntradaMapeador).paraPedidoFiltro(filtroDTO);
        verify(buscaPedidosPortaEntrada).buscarPedidosComFiltro(filtro, paginacao);
        pedidos.forEach(pedido -> verify(pedidoEntradaMapeador).paraPedidoDTO(pedido));
    }
}
