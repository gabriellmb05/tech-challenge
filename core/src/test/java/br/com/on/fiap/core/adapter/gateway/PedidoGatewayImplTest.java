package br.com.on.fiap.core.adapter.gateway;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.core.adapter.datasource.PedidoDataSource;
import br.com.on.fiap.core.application.dto.filtro.PedidoFiltroEntrada;
import br.com.on.fiap.core.application.dto.resposta.Direcao;
import br.com.on.fiap.core.application.dto.resposta.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.PaginacaoResposta;
import br.com.on.fiap.core.domain.Pedido;
import br.com.on.fiap.datapool.*;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PedidoGatewayImplTest {

    @Mock
    private PedidoDataSource pedidoDataSource;

    @InjectMocks
    private PedidoGatewayImpl pedidoGateway;

    @Test
    @DisplayName("Dado um protocolo válido, quando atualizar pedido, então deve retornar o pedido atualizado")
    void dadoProtocoloValido_quandoAtualizarPedido_entaoDeveRetornarPedidoAtualizado() {
        Pedido pedido = PedidoDataPool.criarPedidoComProdutosValidos();  // Criando um pedido válido com o DataPool
        String protocolo = pedido.getProtocolo();
        when(pedidoDataSource.atualizarPedido(protocolo)).thenReturn(Optional.of(pedido));

        Optional<Pedido> resultado = pedidoGateway.atualizarPedido(protocolo);

        assertTrue(resultado.isPresent());
        assertEquals(pedido, resultado.get());
        verify(pedidoDataSource).atualizarPedido(protocolo);
    }

    @Test
    @DisplayName("Dado um filtro e uma paginação válidos, quando listar pedidos, então deve retornar uma página de pedidos")
    void dadoFiltroEPaginacaoValidos_quandoListarPedidos_entaoDeveRetornarPaginaDePedidos() {
        PedidoFiltroEntrada filtro = PedidoFiltroEntradaDataPool.criarFiltroVazio();
        PaginacaoResposta paginacaoResposta =
                PaginacaoResposta.create(0, 10, OrdenacaoDataPool.criarOrdenacaoPorCampoEDirecao("nome", Direcao.ASC));
        PaginaResposta<Pedido> paginaResposta = PaginaRespostaDataPool.criarPaginaComPaginacao(Collections.emptyList(), 0L, 0, 10, 0);

        when(pedidoDataSource.listarComFiltros(filtro, paginacaoResposta)).thenReturn(paginaResposta);

        PaginaResposta<Pedido> resultado = pedidoGateway.listarComFiltros(filtro, paginacaoResposta);

        assertNotNull(resultado);
        verify(pedidoDataSource).listarComFiltros(filtro, paginacaoResposta);
    }

    @Test
    @DisplayName("Dado um protocolo válido, quando detalhar pedido, então deve retornar os detalhes do pedido")
    void dadoProtocoloValido_quandoDetalharPedido_entaoDeveRetornarDetalhesDoPedido() {
        Pedido pedido = PedidoDataPool.criarPedidoComProdutosValidos();
        String protocolo = pedido.getProtocolo();
        when(pedidoDataSource.detalhaPedido(protocolo)).thenReturn(Optional.of(pedido));

        Optional<Pedido> resultado = pedidoGateway.detalhaPedido(protocolo);

        assertTrue(resultado.isPresent());
        assertEquals(pedido, resultado.get());
        verify(pedidoDataSource).detalhaPedido(protocolo);
    }

    @Test
    @DisplayName("Dado um pedido válido, quando salvar pedido, então deve retornar o pedido salvo")
    void dadoPedidoValido_quandoSalvarPedido_entaoDeveRetornarPedidoSalvo() {
        Pedido pedido = PedidoDataPool.criarPedidoComProdutosValidos();
        when(pedidoDataSource.salvaPedido(pedido)).thenReturn(pedido);

        Pedido resultado = pedidoGateway.salvaPedido(pedido);

        assertNotNull(resultado);
        assertEquals(pedido, resultado);
        verify(pedidoDataSource).salvaPedido(pedido);
    }
}
