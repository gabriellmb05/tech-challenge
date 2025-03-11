package br.com.on.fiap.core.application.usecase.pedido.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import br.com.on.fiap.core.application.dto.filtro.PedidoFiltroEntrada;
import br.com.on.fiap.core.application.dto.resposta.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.PaginacaoResposta;
import br.com.on.fiap.core.application.gateway.PedidoGateway;
import br.com.on.fiap.core.application.usecase.pedido.impl.PedidoListaUseCaseImpl;
import br.com.on.fiap.core.domain.Pedido;
import br.com.on.fiap.datapool.*;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PedidoListaUseCaseImplTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private PedidoListaUseCaseImpl pedidoListaUseCase;

    @Test
    @DisplayName("Dado filtro por status, quando listar pedidos, então pedidos com o status devem ser retornados")
    void dadoFiltroPorStatus_quandoListarPedidos_entaoPedidosComStatusDevemSerRetornados() {
        PedidoFiltroEntrada filtro = PedidoFiltroEntradaDataPool.criarFiltroComSituacao("PENDENTE");
        PedidoFiltroEntrada pedidoFiltro = PedidoFiltroDataPool.criarPedidoFiltro("PENDENTE");
        PaginacaoResposta paginacaoResposta = PaginacaoResposta.create(0, 10, null);
        List<Pedido> pedidos = PedidoDataPool.criarPedidosComIdsDinamicos(1);
        PaginaResposta<Pedido> pedidoPaginaResposta = PaginaRespostaDataPool.criarPaginaComPaginacao(pedidos, 1L, 0, 10, 0);
        when(pedidoGateway.listarComFiltros(pedidoFiltro, paginacaoResposta)).thenReturn(pedidoPaginaResposta);

        PaginaResposta<Pedido> result = pedidoListaUseCase.buscarPedidosComFiltro(filtro, paginacaoResposta);

        assertEquals(1, result.getTotalElementos());
        assertEquals("PENDENTE", result.getConteudo().getFirst().getStatus());
        verify(pedidoGateway).listarComFiltros(pedidoFiltro, paginacaoResposta);
    }

    @Test
    @DisplayName("Dado filtro por status e cliente, quando listar pedidos, então os pedidos do status e cliente devem ser retornados")
    void dadoFiltroPorStatusECliente_quandoListarPedidos_entaoPedidosDoStatusEClienteDevemSerRetornados() {
        PedidoFiltroEntrada filtro = PedidoFiltroEntradaDataPool.criarPedidoFiltro("PENDENTE", "Cliente A");
        PedidoFiltro pedidoFiltro = PedidoFiltroDataPool.criarPedidoFiltro("PENDENTE", "Cliente A");
        PaginacaoResposta paginacaoResposta = PaginacaoResposta.create(0, 10, null);
        List<Pedido> pedidos = PedidoDataPool.criarPedidosComIdsDinamicos(10);
        PaginaResposta<Pedido> pedidoPaginaResposta = PaginaRespostaDataPool.criarPaginaComPaginacao(pedidos, 10L, 0, 10, 0);
        when(pedidoGateway.listarComFiltros(pedidoFiltro, paginacaoResposta)).thenReturn(pedidoPaginaResposta);

        PaginaResposta<Pedido> result = pedidoListaUseCase.buscarPedidosComFiltro(filtro, paginacaoResposta);

        assertEquals(10, result.getTotalElementos());
        verify(pedidoGateway).listarComFiltros(pedidoFiltro, paginacaoResposta);
    }

    @Test
    @DisplayName("Dado filtro vazio, quando listar pedidos, então todos os pedidos devem ser retornados")
    void dadoFiltroVazio_quandoListarPedidos_entaoTodosPedidosDevemSerRetornados() {
        PedidoFiltroEntrada filtro = PedidoFiltroEntradaDataPool.filtroVazio();
        PedidoFiltro pedidoFiltro = PedidoFiltroDataPool.criarFiltroVazio();
        PaginacaoResposta paginacaoResposta = PaginacaoResposta.create(0, 10, null);
        List<Pedido> pedidos = PedidoDataPool.criarPedidosComIdsDinamicos(2);
        PaginaResposta<Pedido> pedidoPaginaResposta = PaginaRespostaDataPool.criarPaginaComPaginacao(pedidos, 2L, 0, 10, 0);
        when(pedidoGateway.listarComFiltros(pedidoFiltro, paginacaoResposta)).thenReturn(pedidoPaginaResposta);

        PaginaResposta<Pedido> result = pedidoListaUseCase.buscarPedidosComFiltro(filtro, paginacaoResposta);

        assertEquals(2, result.getTotalElementos());
        verify(pedidoGateway).listarComFiltros(pedidoFiltro, paginacaoResposta);
    }

    @Test
    @DisplayName("Dado filtro de pedido inexistente, quando listar pedidos, então nenhum pedido deve ser retornado")
    void dadoFiltroDePedidoInexistente_quandoListarPedidos_entaoNenhumPedidoDeveSerRetornado() {
        PedidoFiltroEntrada filtro = PedidoFiltroEntradaDataPool.criarPedidoFiltro("INEXISTENTE", "Cliente X");
        PedidoFiltro pedidoFiltro = PedidoFiltroDataPool.criarPedidoFiltro("INEXISTENTE", "Cliente X");
        PaginacaoResposta paginacaoResposta = PaginacaoResposta.create(0, 10, null);
        List<Pedido> pedidos = Collections.emptyList();
        PaginaResposta<Pedido> pedidoPaginaResposta = PaginaRespostaDataPool.criarPaginaComPaginacao(pedidos, 0L, 0, 10, 0);
        when(pedidoGateway.listarComFiltros(pedidoFiltro, paginacaoResposta)).thenReturn(pedidoPaginaResposta);

        PaginaResposta<Pedido> result = pedidoListaUseCase.buscarPedidosComFiltro(filtro, paginacaoResposta);

        assertEquals(0, result.getTotalElementos());
        verify(pedidoGateway).listarComFiltros(pedidoFiltro, paginacaoResposta);
    }

    @Test
    @DisplayName("Dado filtro e ordenação, quando listar pedidos, então os pedidos devem ser retornados na ordem correta")
    void dadoFiltroComOrdenacao_quandoListarPedidos_entaoPedidosDevemSerOrdenados() {
        PedidoFiltroEntrada filtro = PedidoFiltroEntradaDataPool.criarPedidoFiltro("PENDENTE", null);
        PedidoFiltro pedidoFiltro = PedidoFiltroDataPool.criarPedidoFiltro("PENDENTE", null);
        PaginacaoResposta paginacaoResposta = PaginacaoRespostaDataPool.criarPaginacaoComOrdenacao(0, 10, OrdenacaoDataPool.criarOrdenacaoPorCampoEDirecao("data", Direcao.ASC));
        List<Pedido> pedidos = PedidoDataPool.criarPedidosComIdsDinamicos(2);
        PaginaResposta<Pedido> pedidoPaginaResposta = PaginaRespostaDataPool.criarPaginaComPaginacao(pedidos, 2L, 0, 10, 0);
        when(pedidoGateway.listarComFiltros(pedidoFiltro, paginacaoResposta)).thenReturn(pedidoPaginaResposta);

        PaginaResposta<Pedido> result = pedidoListaUseCase.buscarPedidosComFiltro(filtro, paginacaoResposta);

        assertEquals(2, result.getTotalElementos());
        verify(pedidoGateway).listarComFiltros(pedidoFiltro, paginacaoResposta);
    }

    @Test
    @DisplayName("Dado um status inválido no filtro, quando listar os pedidos, então uma exceção deve ser lançada")
    void dadoStatusInvalido_quandoListarPedidos_entaoUmaExcecaoDeveSerLancada() {
        PedidoFiltroEntrada filtro = PedidoFiltroEntradaDataPool.criarPedidoFiltro("INEXISTENTE", null);
        PaginacaoResposta paginacaoResposta = PaginacaoRespostaDataPool.criarPaginacaoComOrdenacao(0, 10, null);

        // Exceção específica que você deseja lançar, caso o filtro seja inválido
        PedidoNaoEncontradoExcecao exception = assertThrows(
                PedidoNaoEncontradoExcecao.class,
                () -> pedidoListaUseCase.buscarPedidosComFiltro(filtro, paginacaoResposta));

        assertEquals("Pedido com status (INEXISTENTE) não encontrado", exception.getMessage());
        verify(pedidoGateway, never()).listarComFiltros(any(), eq(paginacaoResposta));
    }
}
