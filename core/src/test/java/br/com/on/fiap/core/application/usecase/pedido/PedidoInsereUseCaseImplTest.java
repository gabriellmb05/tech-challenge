package br.com.on.fiap.core.application.usecase.pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.on.fiap.core.application.dto.entrada.PedidoEntrada;
import br.com.on.fiap.core.application.exception.ClienteNaoEncontradoExcecao;
import br.com.on.fiap.core.application.exception.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.core.application.usecase.cliente.ClienteBuscaPorIdUseCase;
import br.com.on.fiap.core.application.usecase.pagamento.PagamentoCriaUseCase;
import br.com.on.fiap.core.application.usecase.pedido.impl.PedidoInsereUseCaseImpl;
import br.com.on.fiap.core.domain.*;
import br.com.on.fiap.datapool.ClienteDataPool;
import br.com.on.fiap.datapool.PagamentoDataPool;
import br.com.on.fiap.datapool.ProdutoDataPool;
import java.math.BigDecimal;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PedidoInsereUseCaseImplTest {

    @Mock
    private ClienteBuscaPorIdUseCase clienteBuscaPorIdUseCase;

    @Mock
    private PedidoValidaProdutoUseCase pedidoValidaProdutoUseCase;

    @Mock
    private PedidoCriaUseCase pedidoCriaUseCase;

    @Mock
    private PagamentoCriaUseCase pagamentoCriaUseCase;

    @Mock
    private PedidoProdutoCriaRelacionamentoUseCase pedidoProdutoCriaRelacionamentoUseCase;

    @Mock
    private PedidoSalvaUseCase pedidoSalvaUseCase;

    @InjectMocks
    private PedidoInsereUseCaseImpl pedidoInsereUseCase;

    private PedidoEntrada pedidoEntrada;
    private Cliente cliente;
    private Map<Produto, Long> produtosValidados;
    private Pagamento pagamento;
    private Pedido pedido;

    @BeforeEach
    void setUp() {
        cliente = ClienteDataPool.criarClienteExistente(1L);
        pedidoEntrada = mock(PedidoEntrada.class);

        when(pedidoEntrada.getCliente()).thenReturn(1L);

        produtosValidados = Map.of(ProdutoDataPool.criarProdutoExistente(1L), 10L);
        pagamento = PagamentoDataPool.criarPagamentoPendente();

        pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setPagamento(pagamento);
    }

    @Test
    @DisplayName("Quando inserir um pedido, então todos os componentes devem ser chamados corretamente")
    void quandoInserirPedido_entaoTodosOsComponentesDevemSerChamados() {
        when(clienteBuscaPorIdUseCase.buscar(1L)).thenReturn(cliente);
        when(pedidoValidaProdutoUseCase.validarProdutos(anyList())).thenReturn(produtosValidados);

        when(pagamentoCriaUseCase.criarPagamento(any(), eq(SituacaoPagamento.PENDENTE), eq(produtosValidados)))
                .thenReturn(pagamento);

        when(pedidoCriaUseCase.criaPedido(any(), eq(cliente), eq(pagamento))).thenReturn(pedido);

        doNothing()
                .when(pedidoProdutoCriaRelacionamentoUseCase)
                .criaRelacionamentoProdutoPedido(pedido, produtosValidados);
        when(pedidoSalvaUseCase.salvarPedido(pedido)).thenReturn(pedido);

        Pedido resultado = pedidoInsereUseCase.inserePedido(pedidoEntrada);

        assertNotNull(resultado);
        assertEquals(BigDecimal.valueOf(50), resultado.getPagamento().getVlCompra());
        assertEquals(SituacaoPagamento.PENDENTE, resultado.getPagamento().getStPagamento());

        verify(clienteBuscaPorIdUseCase).buscar(1L);
        verify(pedidoValidaProdutoUseCase).validarProdutos(anyList());
        verify(pagamentoCriaUseCase).criarPagamento(any(), eq(SituacaoPagamento.PENDENTE), eq(produtosValidados));
        verify(pedidoCriaUseCase).criaPedido(any(), eq(cliente), eq(pagamento));
        verify(pedidoProdutoCriaRelacionamentoUseCase).criaRelacionamentoProdutoPedido(pedido, produtosValidados);
        verify(pedidoSalvaUseCase).salvarPedido(pedido);
    }

    @Test
    @DisplayName("Quando o cliente não existe, então uma exceção ClienteNaoEncontradoExcecao deve ser lançada")
    void quandoClienteNaoExiste_entaoExcecaoClienteNaoEncontradoDeveSerLancada() {
        when(pedidoEntrada.getCliente()).thenReturn(1L);

        doThrow(ClienteNaoEncontradoExcecao.class)
                .when(clienteBuscaPorIdUseCase)
                .buscar(1L);

        assertThrows(ClienteNaoEncontradoExcecao.class, () -> pedidoInsereUseCase.inserePedido(pedidoEntrada));

        verify(clienteBuscaPorIdUseCase).buscar(1L);
        verifyNoInteractions(
                pedidoValidaProdutoUseCase,
                pagamentoCriaUseCase,
                pedidoCriaUseCase,
                pedidoProdutoCriaRelacionamentoUseCase,
                pedidoSalvaUseCase);
    }

    @Test
    @DisplayName("Quando algum produto não é válido, então uma exceção ProdutoNaoEncontradoExcecao deve ser lançada")
    void quandoProdutoNaoValido_entaoExcecaoProdutoNaoEncontradoDeveSerLancada() {
        when(pedidoEntrada.getCliente()).thenReturn(1L);
        when(clienteBuscaPorIdUseCase.buscar(1L)).thenReturn(cliente);

        doThrow(ProdutoNaoEncontradoExcecao.class)
                .when(pedidoValidaProdutoUseCase)
                .validarProdutos(anyList());

        assertThrows(ProdutoNaoEncontradoExcecao.class, () -> pedidoInsereUseCase.inserePedido(pedidoEntrada));

        verify(clienteBuscaPorIdUseCase).buscar(1L);
        verify(pedidoValidaProdutoUseCase).validarProdutos(anyList());
        verifyNoMoreInteractions(
                pagamentoCriaUseCase, pedidoCriaUseCase, pedidoProdutoCriaRelacionamentoUseCase, pedidoSalvaUseCase);
    }
}
