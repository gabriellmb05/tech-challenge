package br.com.on.fiap.core.adapter.gateway;

import static org.mockito.Mockito.verify;

import br.com.on.fiap.core.adapter.datasource.PagamentoIntegracaoDataSource;
import br.com.on.fiap.core.domain.Pagamento;
import br.com.on.fiap.datapool.PagamentoDataPool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PagamentoIntegracaoGatewayImplTest {

    @Mock
    private PagamentoIntegracaoDataSource pagamentoIntegracaoDataSource;

    @InjectMocks
    private PagamentoIntegracaoGatewayImpl pagamentoIntegracaoGateway;

    @Test
    @DisplayName("Dado um pagamento válido, quando enviar pagamento para integração, então deve invocar a integração")
    void dadoPagamentoValido_quandoEnviarPagamentoParaIntegracao_entaoDeveInvocarIntegracao() {
        Pagamento pagamento = PagamentoDataPool.criarPagamentoValido();

        pagamentoIntegracaoGateway.integracaoEnviaPagamento(pagamento);

        verify(pagamentoIntegracaoDataSource).integracaoEnviaPagamento(pagamento);
    }
}
