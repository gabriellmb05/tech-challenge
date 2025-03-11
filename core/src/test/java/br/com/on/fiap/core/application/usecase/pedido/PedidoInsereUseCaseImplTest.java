package br.com.on.fiap.core.application.usecase.pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.on.fiap.core.application.dto.entrada.PagamentoEntrada;
import br.com.on.fiap.core.application.dto.entrada.PedidoEntrada;
import br.com.on.fiap.core.application.dto.entrada.ProdutoQuantidadeEntrada;
import br.com.on.fiap.core.application.exception.ClienteNaoEncontradoExcecao;
import br.com.on.fiap.core.application.exception.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.core.application.usecase.cliente.ClienteBuscaPorIdUseCase;
import br.com.on.fiap.core.application.usecase.pagamento.PagamentoCriaUseCase;
import br.com.on.fiap.core.application.usecase.pedido.impl.PedidoInsereUseCaseImpl;
import br.com.on.fiap.core.domain.*;
import java.math.BigDecimal;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

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
    private ProdutoQuantidadeEntrada produtoQuantidadeEntrada;
    private Produto produtoExistente;
    private Map<Produto, Long> produtosValidados;
    private Pagamento pagamento;
    private Pedido pedido;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Criando dados de teste
        pedidoEntrada = mock(PedidoEntrada.class);
        cliente = mock(Cliente.class);
        produtoQuantidadeEntrada = mock(ProdutoQuantidadeEntrada.class);
        produtoExistente = mock(Produto.class);
        produtosValidados = new HashMap<>();
        pagamento = mock(Pagamento.class);
        pedido = mock(Pedido.class);
    }

    @Test
    @DisplayName("Quando inserir um pedido, então todos os componentes devem ser chamados corretamente")
    void quandoInserirPedido_entaoTodosOsComponentesDevemSerChamados() {
        // Configurando mocks
        when(pedidoEntrada.getCliente()).thenReturn(1L);
        when(clienteBuscaPorIdUseCase.buscar(1L)).thenReturn(cliente);

        when(produtoQuantidadeEntrada.getIdProduto()).thenReturn(1L);
        when(produtoQuantidadeEntrada.getQuantidade()).thenReturn(10L);
        when(pedidoValidaProdutoUseCase.validarProdutos(anyList())).thenReturn(Map.of(produtoExistente, 10L));

        // Criando um pagamento
        PagamentoEntrada pagamentoEntrada = mock(PagamentoEntrada.class);
        when(pagamentoEntrada.getTpPagamento()).thenReturn(1); // Exemplo de tipo de pagamento
        when(pagamentoCriaUseCase.criarPagamento(
                        eq(pagamentoEntrada), eq(SituacaoPagamento.PENDENTE), eq(produtosValidados)))
                .thenAnswer(invocation -> {
                    Pagamento pagamentoCriado = new Pagamento();
                    pagamentoCriado.setStPagamento(SituacaoPagamento.PENDENTE);
                    pagamentoCriado.setTpPagamento(TipoPagamento.deCodigo(1)); // Tipo de pagamento
                    pagamentoCriado.setVlCompra(BigDecimal.valueOf(100)); // Definindo valor de compra fictício
                    return pagamentoCriado;
                });

        when(pedidoCriaUseCase.criaPedido(any(), eq(cliente), eq(pagamento))).thenReturn(pedido);

        doNothing()
                .when(pedidoProdutoCriaRelacionamentoUseCase)
                .criaRelacionamentoProdutoPedido(pedido, produtosValidados);
        when(pedidoSalvaUseCase.salvarPedido(pedido)).thenReturn(pedido);

        // Chama o método a ser testado
        Pedido resultado = pedidoInsereUseCase.inserePedido(pedidoEntrada);

        // Verificações
        assertNotNull(resultado);
        assertEquals(
                BigDecimal.valueOf(100), resultado.getPagamento().getVlCompra()); // Verifica o valor total do pagamento
        assertEquals(
                SituacaoPagamento.PENDENTE,
                resultado.getPagamento().getStPagamento()); // Verifica a situação do pagamento
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
        // Configurando mocks
        when(pedidoEntrada.getCliente()).thenReturn(1L);
        when(clienteBuscaPorIdUseCase.buscar(1L)).thenThrow(ClienteNaoEncontradoExcecao.class);

        // Chama o método e verifica se a exceção é lançada
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
        // Configurando mocks
        when(pedidoEntrada.getCliente()).thenReturn(1L);
        when(clienteBuscaPorIdUseCase.buscar(1L)).thenReturn(cliente);

        when(produtoQuantidadeEntrada.getIdProduto()).thenReturn(1L);
        when(produtoQuantidadeEntrada.getQuantidade()).thenReturn(10L);
        when(pedidoValidaProdutoUseCase.validarProdutos(anyList()))
                .thenThrow(new ProdutoNaoEncontradoExcecao("Produto não encontrado"));

        // Chama o método e verifica se a exceção é lançada
        ProdutoNaoEncontradoExcecao exception =
                assertThrows(ProdutoNaoEncontradoExcecao.class, () -> pedidoInsereUseCase.inserePedido(pedidoEntrada));

        assertEquals("Produto não encontrado", exception.getMessage());
        verify(clienteBuscaPorIdUseCase).buscar(1L);
        verify(pedidoValidaProdutoUseCase).validarProdutos(anyList());
        verifyNoMoreInteractions(
                pagamentoCriaUseCase, pedidoCriaUseCase, pedidoProdutoCriaRelacionamentoUseCase, pedidoSalvaUseCase);
    }
}
