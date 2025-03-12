package br.com.on.fiap.core.adapter.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.on.fiap.core.adapter.controller.impl.PedidoControllerImpl;
import br.com.on.fiap.core.adapter.presenter.PedidoPresenter;
import br.com.on.fiap.core.application.dto.entrada.PedidoEntrada;
import br.com.on.fiap.core.application.dto.filtro.PedidoFiltroEntrada;
import br.com.on.fiap.core.application.dto.resposta.*;
import br.com.on.fiap.core.application.usecase.pedido.PedidoAtualizaUseCase;
import br.com.on.fiap.core.application.usecase.pedido.PedidoDetalhaUseCase;
import br.com.on.fiap.core.application.usecase.pedido.PedidoInsereUseCase;
import br.com.on.fiap.core.application.usecase.pedido.PedidoListaUseCase;
import br.com.on.fiap.core.domain.Pedido;
import br.com.on.fiap.core.domain.SituacaoPedido;
import br.com.on.fiap.datapool.*;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PedidoControllerImplTest {

    @Mock
    private PedidoInsereUseCase pedidoInsereUseCase;

    @Mock
    private PedidoListaUseCase pedidoListaUseCase;

    @Mock
    private PedidoDetalhaUseCase pedidoDetalhaUseCase;

    @Mock
    private PedidoAtualizaUseCase pedidoAtualizaUseCase;

    @Mock
    private PedidoPresenter pedidoPresenter;

    @InjectMocks
    private PedidoControllerImpl pedidoController;

    private final PaginacaoResposta paginacaoResposta = PaginacaoResposta.create(0, 10, null);

    private PaginaResposta<Pedido> criarPedidoPage() {
        return PaginaRespostaDataPool.criarPaginaComPaginacao(
                Collections.singletonList(PedidoDataPool.criarPedidoComProdutosInvalidos()), 1L, 0, 10, 0);
    }

    private PaginaResposta<PedidoResposta> criarPedidoRespostaPage() {
        return PaginaRespostaDataPool.criarPaginaComPaginacao(
                Collections.singletonList(PedidoResposta.create(PedidoDataPool.criarPedidoComProdutosInvalidos())),
                1L,
                0,
                10,
                0);
    }

    @Test
    @DisplayName("Dado um pedido, quando inserido, então deve ser retornado um pedido formatado")
    void dadoPedidoEntrada_quandoInserirPedido_entaoDeveRetornarPedidoFormatado() {
        PedidoEntrada pedidoEntrada = PedidoEntrada.create(1L, null, null);
        Pedido pedido = PedidoDataPool.criarPedidoExistente(1L);
        PedidoResposta pedidoResposta = PedidoResposta.create(pedido);

        when(pedidoInsereUseCase.inserePedido(pedidoEntrada)).thenReturn(pedido);
        when(pedidoPresenter.formatar(pedido)).thenReturn(pedidoResposta);

        PedidoResposta resultado = pedidoController.inserePedido(pedidoEntrada);

        assertNotNull(resultado);
        assertEquals(pedidoResposta.getId(), resultado.getId());
        verify(pedidoInsereUseCase).inserePedido(pedidoEntrada);
        verify(pedidoPresenter).formatar(pedido);
    }

    @Test
    @DisplayName(
            "Dado um filtro e paginação, quando listar pedidos, então deve retornar uma lista de pedidos formatados")
    void dadoFiltroEPaginacao_quandoListarPedidos_entaoDeveRetornarPaginaComPedidosFormatados() {
        PedidoFiltroEntrada pedidoFiltroEntrada = PedidoFiltroEntradaDataPool.criarFiltroVazio();
        PaginaResposta<Pedido> pedidoPaginaResposta = criarPedidoPage();
        PaginaResposta<PedidoResposta> paginaRespostaFormatada = criarPedidoRespostaPage();

        when(pedidoListaUseCase.buscarPedidosComFiltro(pedidoFiltroEntrada, paginacaoResposta))
                .thenReturn(pedidoPaginaResposta);
        when(pedidoPresenter.formatar(pedidoPaginaResposta)).thenReturn(paginaRespostaFormatada);

        PaginaResposta<PedidoResposta> resultado =
                pedidoController.listarPedidoComFiltro(pedidoFiltroEntrada, paginacaoResposta);

        assertNotNull(resultado);
        assertEquals(
                paginaRespostaFormatada.getConteudo().size(),
                resultado.getConteudo().size());
        verify(pedidoListaUseCase).buscarPedidosComFiltro(pedidoFiltroEntrada, paginacaoResposta);
        verify(pedidoPresenter).formatar(pedidoPaginaResposta);
    }

    @Test
    @DisplayName("Dado um protocolo, quando detalhar o pedido, então deve retornar o pedido detalhado formatado")
    void dadoProtocolo_quandoDetalharPedido_entaoDeveRetornarPedidoDetalhadoFormatado() {
        String protocolo = "1234567890";
        Pedido pedido = PedidoDataPool.criarPedidoExistente(1L);
        PedidoDetalhadoResposta pedidoDetalhadoResposta = PedidoDetalhadoResposta.create(
                1L,
                ClienteResposta.fromDomain(ClienteDataPool.criarClienteValido()),
                SituacaoPedido.REALIZADO,
                Collections.singletonList(ProdutoResposta.create(ProdutoDataPool.criarProdutoNovo())),
                PagamentoResposta.create(PagamentoDataPool.criarPagamentoValido()));

        when(pedidoDetalhaUseCase.detalhaPedido(protocolo)).thenReturn(pedido);
        when(pedidoPresenter.formatarDetalhado(pedido)).thenReturn(pedidoDetalhadoResposta);

        PedidoDetalhadoResposta resultado = pedidoController.detalhaPedido(protocolo);

        assertNotNull(resultado);
        assertEquals(pedidoDetalhadoResposta.getId(), resultado.getId());
        verify(pedidoDetalhaUseCase).detalhaPedido(protocolo);
        verify(pedidoPresenter).formatarDetalhado(pedido);
    }

    @Test
    @DisplayName("Dado um protocolo, quando atualizar o pedido, então deve retornar o pedido atualizado formatado")
    void dadoProtocolo_quandoAtualizarPedido_entaoDeveRetornarPedidoAtualizadoFormatado() {
        String protocolo = "1234567890";
        Pedido pedido = PedidoDataPool.criarPedidoExistente(1L);
        PedidoResposta pedidoResposta = PedidoResposta.create(pedido);

        when(pedidoAtualizaUseCase.atualizarPedido(protocolo)).thenReturn(pedido);
        when(pedidoPresenter.formatar(pedido)).thenReturn(pedidoResposta);

        PedidoResposta resultado = pedidoController.atualizarPedido(protocolo);

        assertNotNull(resultado);
        assertEquals(pedidoResposta.getId(), resultado.getId());
        verify(pedidoAtualizaUseCase).atualizarPedido(protocolo);
        verify(pedidoPresenter).formatar(pedido);
    }
}
