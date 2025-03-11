package br.com.on.fiap.core.adapter.gateway;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.core.adapter.datasource.PagamentoDataSource;
import br.com.on.fiap.core.domain.Pagamento;
import br.com.on.fiap.datapool.PagamentoDataPool;  // Supondo que você tenha um DataPool para criar dados de teste
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PagamentoGatewayImplTest {

    @Mock
    private PagamentoDataSource pagamentoDataSource;

    @InjectMocks
    private PagamentoGatewayImpl pagamentoGateway;

    @Test
    @DisplayName("Dado um pagamento válido, quando salvar pagamento, então deve retornar o pagamento salvo")
    void dadoPagamentoValido_quandoSalvarPagamento_entaoDeveRetornarPagamentoSalvo() {
        Pagamento pagamento = PagamentoDataPool.criarPagamentoValido();

        when(pagamentoDataSource.salvaPagamento(pagamento)).thenReturn(pagamento);

        Pagamento resultado = pagamentoGateway.salvaPagamento(pagamento);

        assertNotNull(resultado);
        assertEquals(pagamento, resultado);

        verify(pagamentoDataSource).salvaPagamento(pagamento);
    }
}
