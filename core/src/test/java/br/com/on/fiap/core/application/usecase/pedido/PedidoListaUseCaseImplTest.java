package br.com.on.fiap.core.application.usecase.pedido;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.core.adapter.gateway.PedidoGateway;
import br.com.on.fiap.core.domain.model.Pedido;
import br.com.on.fiap.core.domain.model.PedidoFiltro;
import br.com.on.fiap.core.domain.model.SituacaoPedido;
import br.com.on.fiap.core.application.usecase.pedido.impl.PedidoListaUseCaseImpl;
import br.com.on.fiap.datapool.DataPoolPedido;
import br.com.on.fiap.datapool.DataPoolPedidoFiltro;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class PedidoListaUseCaseImplTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private PedidoListaUseCaseImpl pedidoBuscaUseCase;

    @Test
    @DisplayName(
            "Dado um filtro com data de início e fim, ao buscar pedidos, os pedidos devem ser filtrados pelo período")
    void dadoFiltroComDataInicioEDataFim_quandoBuscarPedidos_entaoPedidosDevemSerFiltradosPorPeriodo() {
        LocalDate dataInicio = LocalDate.of(2025, 1, 1);
        LocalDate dataFim = LocalDate.of(2025, 1, 31);
        PedidoFiltro filtro = DataPoolPedidoFiltro.pedidoFiltroComPeriodo(dataInicio, dataFim);

        Pageable pageable = PageRequest.of(0, 10);
        Pedido pedido = DataPoolPedido.pedidoExistenteComDataHora(1L, LocalDateTime.of(2025, 1, 15, 12, 0));
        Page<Pedido> page = new PageImpl<>(List.of(pedido), pageable, 1L);

        when(pedidoGateway.listarComFiltros(filtro, pageable)).thenReturn(page);

        Page<Pedido> result = pedidoBuscaUseCase.buscarPedidosComFiltro(filtro, pageable);

        assertEquals(1, result.getTotalElements());
        assertTrue(result.getContent().getFirst().getDataHora().isAfter(dataInicio.atStartOfDay()));
        assertTrue(result.getContent().getFirst().getDataHora().isBefore(dataFim.atTime(23, 59, 59)));
        verify(pedidoGateway).listarComFiltros(filtro, pageable);
    }

    @Test
    @DisplayName("Dado um filtro com situação, ao buscar pedidos, os pedidos devem ser filtrados pela situação")
    void dadoFiltroComSituacao_quandoBuscarPedidos_entaoPedidosDevemSerFiltradosPorSituacao() {
        SituacaoPedido situacao = SituacaoPedido.deCodigo(1);
        PedidoFiltro filtro = DataPoolPedidoFiltro.pedidoFiltroComSituacao(
                situacao.getCodigo().longValue());

        Pageable pageable = PageRequest.of(0, 10);
        Pedido pedido = DataPoolPedido.pedidoExistente(1L);
        Page<Pedido> page = new PageImpl<>(List.of(pedido), pageable, 1L);

        when(pedidoGateway.listarComFiltros(filtro, pageable)).thenReturn(page);

        Page<Pedido> result = pedidoBuscaUseCase.buscarPedidosComFiltro(filtro, pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals(
                situacao.getCodigo(),
                result.getContent().getFirst().getSituacao().getCodigo());
        verify(pedidoGateway).listarComFiltros(filtro, pageable);
    }

    @Test
    @DisplayName(
            "Dado um filtro com CPF de cliente, ao buscar pedidos, os pedidos devem ser filtrados pelo CPF do cliente")
    void dadoFiltroComCpfCliente_quandoBuscarPedidos_entaoPedidosDevemSerFiltradosPorCpfCliente() {
        String cpfCliente = "12345678900";
        PedidoFiltro filtro = DataPoolPedidoFiltro.pedidoFiltroComCpfCliente(cpfCliente);

        Pageable pageable = PageRequest.of(0, 10);
        Pedido pedido = DataPoolPedido.pedidoExistente(1L);
        Page<Pedido> page = new PageImpl<>(List.of(pedido), pageable, 1L);

        when(pedidoGateway.listarComFiltros(filtro, pageable)).thenReturn(page);

        Page<Pedido> result = pedidoBuscaUseCase.buscarPedidosComFiltro(filtro, pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals(cpfCliente, result.getContent().getFirst().getCliente().getCpf());
        verify(pedidoGateway).listarComFiltros(filtro, pageable);
    }

    @Test
    @DisplayName("Dado um filtro vazio, ao buscar pedidos, todos os pedidos devem ser retornados")
    void dadoFiltroVazio_quandoBuscarPedidos_entaoTodosOsPedidosDevemSerRetornados() {
        PedidoFiltro filtro = DataPoolPedidoFiltro.pedidoFiltroVazio();

        Pageable pageable = PageRequest.of(0, 10);
        Pedido pedido1 = DataPoolPedido.pedidoExistente(1L);
        Pedido pedido2 = DataPoolPedido.pedidoExistente(2L);
        Page<Pedido> page = new PageImpl<>(List.of(pedido1, pedido2), pageable, 2L);

        when(pedidoGateway.listarComFiltros(filtro, pageable)).thenReturn(page);

        Page<Pedido> result = pedidoBuscaUseCase.buscarPedidosComFiltro(filtro, pageable);

        assertEquals(2, result.getTotalElements());
        verify(pedidoGateway).listarComFiltros(filtro, pageable);
    }
}
