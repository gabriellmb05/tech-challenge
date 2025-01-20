package br.com.on.fiap.hexagono.casosdeuso.pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.on.fiap.hexagono.datapool.DataPoolCliente;
import br.com.on.fiap.hexagono.datapool.DataPoolPagamento;
import br.com.on.fiap.hexagono.datapool.DataPoolPedido;
import br.com.on.fiap.hexagono.datapool.DataPoolRelPedidoProduto;
import br.com.on.fiap.hexagono.dominio.Cliente;
import br.com.on.fiap.hexagono.dominio.Pagamento;
import br.com.on.fiap.hexagono.dominio.Pedido;
import br.com.on.fiap.hexagono.excecao.ClienteNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.portas.saida.cliente.PersisteClientePortaSaida;
import br.com.on.fiap.hexagono.portas.saida.pagamento.PersistePagamentoPortaSaida;
import br.com.on.fiap.hexagono.portas.saida.pedido.PersistePedidoPagamentoPortaSaida;
import br.com.on.fiap.hexagono.portas.saida.pedido.PersistePedidoPortaSaida;
import br.com.on.fiap.hexagono.portas.saida.pedido.PersistePedidoProdutoPortaSaida;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InserePedidoCasoDeUsoTest {

    @Mock
    private PersisteClientePortaSaida persisteClientePortaSaida;

    @Mock
    private PersistePedidoPortaSaida persistePedidoPortaSaida;

    @Mock
    private PersistePedidoProdutoPortaSaida persistePedidoProdutoPortaSaida;

    @Mock
    private PersistePagamentoPortaSaida persistePagamentoPortaSaida;

    @Mock
    private PersistePedidoPagamentoPortaSaida persistePedidoPagamentoPortaSaida;

    @InjectMocks
    private InserePedidoCasoDeUso inserePedidoCasoDeUso;

    @Test
    @DisplayName("Dado um pedido válido, quando inserir o pedido, então o pedido deve ser inserido com sucesso")
    void dadoPedidoValido_quandoInserirPedido_entaoPedidoDeveSerInseridoComSucesso() {
        Cliente cliente = DataPoolCliente.clienteExistente(1L);
        Pedido pedido = DataPoolPedido.pedidoComCliente(cliente);
        Pagamento pagamento = DataPoolPagamento.pagamentoValido();
        pedido.setRelPedidoProdutos(DataPoolRelPedidoProduto.relPedidoProdutosComProdutos(1));

        when(persisteClientePortaSaida.buscaClientePorId(cliente.getId())).thenReturn(java.util.Optional.of(cliente));
        when(persistePedidoPortaSaida.salvaPedido(pedido)).thenReturn(pedido);
        doNothing().when(persistePedidoProdutoPortaSaida).vincularPedido(pedido.getRelPedidoProdutos());
        when(persistePagamentoPortaSaida.salvaPagamento(pagamento)).thenReturn(pagamento);
        doNothing().when(persistePedidoPagamentoPortaSaida).salvaPedidoPagamento(pedido);

        Pedido pedidoSalvo = inserePedidoCasoDeUso.inserir(pedido, pagamento);

        verify(persisteClientePortaSaida).buscaClientePorId(cliente.getId());
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
    @DisplayName("Dado um pedido com cliente inexistente, quando inserir o pedido, então a exceção ClienteNaoEncontradoExcecao deve ser lançada")
    void dadoPedidoComClienteInexistente_quandoInserirPedido_entaoClienteNaoEncontradoExcecaoDeveSerLancada() {
        Cliente cliente = new Cliente(999L);
        Pedido pedido = DataPoolPedido.pedidoComCliente(cliente);

        when(persisteClientePortaSaida.buscaClientePorId(cliente.getId())).thenReturn(Optional.empty());

        ClienteNaoEncontradoExcecao exception = assertThrows(
                ClienteNaoEncontradoExcecao.class, () -> inserePedidoCasoDeUso.inserir(pedido, new Pagamento()));

        assertEquals("Não foi encontrado o Cliente para o id: 999", exception.getMessage());
        verify(persisteClientePortaSaida).buscaClientePorId(cliente.getId());
    }
}
