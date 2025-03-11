package br.com.on.fiap.core.application.usecase.pagamento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.on.fiap.core.application.gateway.PagamentoGateway;
import br.com.on.fiap.core.application.gateway.PagamentoIntegracaoGateway;
import br.com.on.fiap.core.application.usecase.pagamento.impl.PagamentoAtualizaUseCaseImpl;
import br.com.on.fiap.core.application.usecase.pedido.PedidoDetalhaUseCase;
import br.com.on.fiap.core.domain.Pagamento;
import br.com.on.fiap.core.domain.SituacaoPagamento;
import br.com.on.fiap.datapool.PedidoDataPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class PagamentoAtualizaUseCaseImplTest {

    @Mock
    private PedidoDetalhaUseCase pedidoDetalhaUseCase;

    @Mock
    private PagamentoValidaUseCase pagamentoValidaUseCase;

    @Mock
    private PagamentoGateway pagamentoGateway;

    @Mock
    private PagamentoIntegracaoGateway pagamentoIntegracaoGateway;

    @InjectMocks
    private PagamentoAtualizaUseCaseImpl pagamentoAtualizaUseCase;

    private Pagamento pagamento;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pagamento = mock(Pagamento.class);
    }

    @Test
    @DisplayName("Quando o pagamento for atualizado, então ele deve ser enviado e salvo corretamente")
    void quandoAtualizarPagamento_entaoPagamentoDeveSerEnviadoESalvo() {
        // Preparando mocks
        String nrProtocolo = "12345";
        when(pedidoDetalhaUseCase.detalhaPedido(nrProtocolo)).thenReturn(PedidoDataPool.criarPedidoExistente(1L));

        // Configurando o pagamento mockado
        when(pagamento.getStPagamento()).thenReturn(SituacaoPagamento.PENDENTE);

        // Executa o método que será testado
        Pagamento resultado = pagamentoAtualizaUseCase.atualizaPagamento(nrProtocolo);

        // Verificando as interações e assertivas
        verify(pedidoDetalhaUseCase).detalhaPedido(nrProtocolo);
        verify(pagamentoValidaUseCase).validarPagamentoJaRealizado(pagamento, nrProtocolo);
        verify(pagamentoIntegracaoGateway).integracaoEnviaPagamento(pagamento);
        verify(pagamentoGateway).salvaPagamento(resultado);

        assertNotNull(resultado);
        assertEquals(
                SituacaoPagamento.APROVADO,
                resultado.getStPagamento()); // Verifica se a situação foi alterada para 'APROVADO'
        assertNotNull(resultado.getDhPagamento()); // Verifica se a data de pagamento foi atribuída
    }

    @Test
    @DisplayName("Quando o pagamento já foi realizado, então deve lançar uma exceção")
    void quandoPagamentoJaRealizado_entaoDeveLancarExcecao() {
        // Preparando mocks
        String nrProtocolo = "12345";
        when(pedidoDetalhaUseCase.detalhaPedido(nrProtocolo)).thenReturn(PedidoDataPool.criarPedidoExistente(1L));

        // Configurando o pagamento para já ter sido aprovado
        when(pagamento.getStPagamento()).thenReturn(SituacaoPagamento.APROVADO);

        // Executa o método e verifica se a exceção é lançada
        assertThrows(IllegalStateException.class, () -> pagamentoAtualizaUseCase.atualizaPagamento(nrProtocolo));

        verify(pedidoDetalhaUseCase).detalhaPedido(nrProtocolo);
        verifyNoInteractions(pagamentoValidaUseCase, pagamentoIntegracaoGateway, pagamentoGateway);
    }
}
