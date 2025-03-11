package br.com.on.fiap.core.application.usecase.pedido.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.on.fiap.core.application.dto.entrada.PedidoEntrada;
import br.com.on.fiap.core.application.usecase.cliente.ClienteBuscaPorIdUseCase;
import br.com.on.fiap.core.application.usecase.pagamento.PagamentoCriaUseCase;
import br.com.on.fiap.core.application.usecase.pedido.*;
import br.com.on.fiap.core.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Map;

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
        MockitoAnnotations.openMocks(this);

        // Inicialização de objetos necessários para os testes
        pedidoEntrada = mock(PedidoEntrada.class);
        cliente = mock(Cliente.class);
        produtosValidados = mock(Map.class);
        pagamento = mock(Pagamento.class);
        pedido = mock(Pedido.class);
    }

    @Test
    @DisplayName("Quando insere um pedido com sucesso, então o pedido deve ser salvo")
    void quandoInserePedidoComSucesso_entaoPedidoDeveSerSalvo() {
        // Configuração dos mocks
        when(clienteBuscaPorIdUseCase.buscar(any(Long.class))).thenReturn(cliente);
        when(pedidoValidaProdutoUseCase.validarProdutos(any())).thenReturn(produtosValidados);
        when(pagamentoCriaUseCase.criarPagamento(any(), any(), any())).thenReturn(pagamento);
        when(pedidoCriaUseCase.criaPedido(any(), any(), any())).thenReturn(pedido);
        doNothing().when(pedidoProdutoCriaRelacionamentoUseCase).criaRelacionamentoProdutoPedido(any(), any());
        when(pedidoSalvaUseCase.salvarPedido(any())).thenReturn(pedido);

        // Chama o método a ser testado
        Pedido result = pedidoInsereUseCase.inserePedido(pedidoEntrada);

        // Verifica as interações e se o retorno é o esperado
        verify(clienteBuscaPorIdUseCase).buscar(any(Long.class));
        verify(pedidoValidaProdutoUseCase).validarProdutos(any());
        verify(pagamentoCriaUseCase).criarPagamento(any(), any(), any());
        verify(pedidoCriaUseCase).criaPedido(any(), any(), any());
        verify(pedidoProdutoCriaRelacionamentoUseCase).criaRelacionamentoProdutoPedido(any(), any());
        verify(pedidoSalvaUseCase).salvarPedido(any());

        // Verifica se o resultado retornado é o esperado
        assertEquals(pedido, result);
    }

    @Test
    @DisplayName("Dado um erro ao buscar o cliente, então uma exceção deve ser lançada")
    void dadoErroAoBuscarCliente_quandoInserePedido_entaoUmaExcecaoDeveSerLancada() {
        // Configuração do mock para erro na busca do cliente
        when(clienteBuscaPorIdUseCase.buscar(any(Long.class))).thenThrow(new ClienteNaoEncontradoExcecao("Cliente não encontrado"));

        // Chama o método e verifica se a exceção é lançada
        assertThrows(ClienteNaoEncontradoExcecao.class, () -> pedidoInsereUseCase.inserePedido(pedidoEntrada));
    }

    @Test
    @DisplayName("Dado erro na validação de produtos, então uma exceção deve ser lançada")
    void dadoErroNaValidacaoDeProdutos_quandoInserePedido_entaoUmaExcecaoDeveSerLancada() {
        // Configuração do mock para erro na validação dos produtos
        when(clienteBuscaPorIdUseCase.buscar(any(Long.class))).thenReturn(cliente);
        when(pedidoValidaProdutoUseCase.validarProdutos(any())).thenThrow(new ProdutoNaoValidoExcecao("Produto inválido"));

        // Chama o método e verifica se a exceção é lançada
        assertThrows(ProdutoNaoValidoExcecao.class, () -> pedidoInsereUseCase.inserePedido(pedidoEntrada));
    }

    @Test
    @DisplayName("Dado erro ao salvar o pedido, então uma exceção deve ser lançada")
    void dadoErroAoSalvarPedido_quandoInserePedido_entaoUmaExcecaoDeveSerLancada() {
        // Configuração do mock para erro ao salvar o pedido
        when(clienteBuscaPorIdUseCase.buscar(any(Long.class))).thenReturn(cliente);
        when(pedidoValidaProdutoUseCase.validarProdutos(any())).thenReturn(produtosValidados);
        when(pagamentoCriaUseCase.criarPagamento(any(), any(), any())).thenReturn(pagamento);
        when(pedidoCriaUseCase.criaPedido(any(), any(), any())).thenReturn(pedido);
        doNothing().when(pedidoProdutoCriaRelacionamentoUseCase).criaRelacionamentoProdutoPedido(any(), any());
        when(pedidoSalvaUseCase.salvarPedido(any())).thenThrow(new PedidoNaoSalvoExcecao("Erro ao salvar pedido"));

        // Chama o método e verifica se a exceção é lançada
        assertThrows(PedidoNaoSalvoExcecao.class, () -> pedidoInsereUseCase.inserePedido(pedidoEntrada));
    }
}
