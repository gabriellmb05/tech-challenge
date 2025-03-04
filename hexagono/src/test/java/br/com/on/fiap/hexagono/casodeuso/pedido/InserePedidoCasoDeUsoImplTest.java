package br.com.on.fiap.hexagono.casodeuso.pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.on.fiap.hexagono.adaptadores.gateways.ClienteGateway;
import br.com.on.fiap.hexagono.casodeuso.pagamento.saida.PersistePagamentoPortaSaida;
import br.com.on.fiap.hexagono.datapool.*;
import br.com.on.fiap.hexagono.entidades.Cliente;
import br.com.on.fiap.hexagono.entidades.Pagamento;
import br.com.on.fiap.hexagono.entidades.Pedido;
import br.com.on.fiap.hexagono.excecao.ClienteNaoEncontradoExcecao;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InserePedidoCasoDeUsoImplTest {

    @Mock
    private ClienteGateway clienteGateway;

    @Mock
    private PersistePedidoPortaSaida persistePedidoPortaSaida;

    @Mock
    private PersistePedidoProdutoPortaSaida persistePedidoProdutoPortaSaida;

    @Mock
    private PersistePagamentoPortaSaida persistePagamentoPortaSaida;

    @Mock
    private PersistePedidoPagamentoPortaSaida persistePedidoPagamentoPortaSaida;

    @InjectMocks
    private InserePedidoCasoDeUsoImpl inserePedidoCasoDeUso;

    @Test
    @DisplayName("Dado um pedido válido, quando inserir o pedido, então o pedido deve ser inserido com sucesso")
    void dadoPedidoValido_quandoInserirPedido_entaoPedidoDeveSerInseridoComSucesso() {
        Cliente cliente = DataPoolCliente.clienteExistente(1L);
        Pedido pedido = DataPoolPedido.pedidoComCliente(cliente);
        Pagamento pagamento = DataPoolPagamento.pagamentoValido();
        pedido.setRelPedidoProdutos(DataPoolRelPedidoProduto.relPedidoProdutosComProdutos(1));

        when(clienteGateway.buscaClientePorId(cliente.getId())).thenReturn(java.util.Optional.of(cliente));
        when(persistePedidoPortaSaida.salvaPedido(pedido)).thenReturn(pedido);
        doNothing().when(persistePedidoProdutoPortaSaida).vincularPedido(pedido.getRelPedidoProdutos());
        when(persistePagamentoPortaSaida.salvaPagamento(pagamento)).thenReturn(pagamento);
        doNothing().when(persistePedidoPagamentoPortaSaida).salvaPedidoPagamento(pedido);

        Pedido pedidoSalvo = inserePedidoCasoDeUso.inserir(pedido, pagamento);

        verify(clienteGateway).buscaClientePorId(cliente.getId());
        verify(persistePedidoPortaSaida).salvaPedido(pedido);
        verify(persistePedidoProdutoPortaSaida).vincularPedido(pedido.getRelPedidoProdutos());
        verify(persistePagamentoPortaSaida).salvaPagamento(pagamento);
        verify(persistePedidoPagamentoPortaSaida).salvaPedidoPagamento(pedido);

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
                ClienteNaoEncontradoExcecao.class, () -> inserePedidoCasoDeUso.inserir(pedido, new Pagamento()));

        assertEquals("Não foi encontrado o Cliente para o id: 999", exception.getMessage());
        verify(clienteGateway).buscaClientePorId(cliente.getId());
    }
}
