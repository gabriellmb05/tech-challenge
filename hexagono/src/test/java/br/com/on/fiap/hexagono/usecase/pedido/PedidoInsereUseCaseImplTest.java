package br.com.on.fiap.hexagono.usecase.pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.on.fiap.hexagono.adapter.gateway.ClienteGateway;
import br.com.on.fiap.hexagono.adapter.gateway.PagamentoGateway;
import br.com.on.fiap.hexagono.adapter.gateway.PedidoGateway;
import br.com.on.fiap.hexagono.application.usecase.pedido.PedidoInsereUseCaseImpl;
import br.com.on.fiap.hexagono.datapool.*;
import br.com.on.fiap.hexagono.domain.entity.Cliente;
import br.com.on.fiap.hexagono.domain.entity.Pagamento;
import br.com.on.fiap.hexagono.domain.entity.Pedido;
import br.com.on.fiap.hexagono.domain.exception.ClienteNaoEncontradoExcecao;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PedidoInsereUseCaseImplTest {

    @Mock
    private ClienteGateway clienteGateway;

    @Mock
    private PedidoGateway pedidoGateway;

    @Mock
    private PagamentoGateway pagamentoGateway;

    @InjectMocks
    private PedidoInsereUseCaseImpl pedidoInsereUseCase;

    @Test
    @DisplayName("Dado um pedido válido, quando inserir o pedido, então o pedido deve ser inserido com sucesso")
    void dadoPedidoValido_quandoInserirPedido_entaoPedidoDeveSerInseridoComSucesso() {
        Cliente cliente = DataPoolCliente.clienteExistente(1L);
        Pedido pedido = DataPoolPedido.pedidoComCliente(cliente);
        Pagamento pagamento = DataPoolPagamento.pagamentoValido();
        pedido.setRelPedidoProdutos(DataPoolRelPedidoProduto.relPedidoProdutosComProdutos(1));

        when(clienteGateway.buscaClientePorId(cliente.getId())).thenReturn(java.util.Optional.of(cliente));
        when(pedidoGateway.salvaPedido(pedido)).thenReturn(pedido);
        doNothing().when(pedidoGateway).vincularPedido(pedido.getRelPedidoProdutos());
        when(pagamentoGateway.salvaPagamento(pagamento)).thenReturn(pagamento);
        doNothing().when(pedidoGateway).salvaPedidoPagamento(pedido);

        Pedido pedidoSalvo = pedidoInsereUseCase.inserir(pedido, pagamento);

        verify(clienteGateway).buscaClientePorId(cliente.getId());
        verify(pedidoGateway).salvaPedido(pedido);
        verify(pedidoGateway).vincularPedido(pedido.getRelPedidoProdutos());
        verify(pagamentoGateway).salvaPagamento(pagamento);
        verify(pedidoGateway).salvaPedidoPagamento(pedido);

        assertNotNull(pedidoSalvo);
        assertEquals(cliente.getId(), pedidoSalvo.getCliente().getId());
        assertEquals(pedido.getRelPedidoProdutos(), pedidoSalvo.getRelPedidoProdutos());
        assertEquals(pedido.getPagamento(), pedidoSalvo.getPagamento());
    }

    @Test
    @DisplayName(
            "Dado um pedido com cliente inexistente, quando inserir o pedido, então a exceção ClienteNaoEncontradoExcecao deve ser lançada")
    void dadoPedidoComClienteInexistente_quandoInserirPedido_entaoClienteNaoEncontradoExcecaoDeveSerLancada() {
        Cliente cliente = new Cliente(999L);
        Pedido pedido = DataPoolPedido.pedidoComCliente(cliente);

        when(clienteGateway.buscaClientePorId(cliente.getId())).thenReturn(Optional.empty());

        ClienteNaoEncontradoExcecao exception = assertThrows(
                ClienteNaoEncontradoExcecao.class, () -> pedidoInsereUseCase.inserir(pedido, new Pagamento()));

        assertEquals("Não foi encontrado o Cliente para o id: 999", exception.getMessage());
        verify(clienteGateway).buscaClientePorId(cliente.getId());
    }
}
