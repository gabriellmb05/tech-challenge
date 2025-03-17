package br.com.on.fiap.core.application.usecase.pedido;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.on.fiap.core.application.dto.entrada.PedidoEntrada;
import br.com.on.fiap.core.application.usecase.pedido.impl.PedidoCriaUseCaseImpl;
import br.com.on.fiap.core.domain.Cliente;
import br.com.on.fiap.core.domain.Pagamento;
import br.com.on.fiap.core.domain.Pedido;
import br.com.on.fiap.core.domain.SituacaoPedido;
import br.com.on.fiap.datapool.ClienteDataPool;
import br.com.on.fiap.datapool.PagamentoDataPool;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PedidoCriaUseCaseImplTest {

    @Mock
    private final Cliente cliente = ClienteDataPool.criarClienteValido();

    @Mock
    private final Pagamento pagamento = PagamentoDataPool.criarPagamentoValido();

    @InjectMocks
    private PedidoCriaUseCaseImpl pedidoCriaUseCase;

    @Test
    @DisplayName(
            "Dado um pedido de entrada, cliente e pagamento, quando criar o pedido, então o pedido deve ser criado corretamente com dados associados")
    void dadoPedidoEntradaClienteEPagamento_quandoCriarPedido_entaoPedidoDeveSerCriadoComDadosCorretos() {
        PedidoEntrada pedidoEntrada = PedidoEntrada.create(1L, null, null);

        Pedido pedido = pedidoCriaUseCase.criaPedido(pedidoEntrada, cliente, pagamento);

        assertNotNull(pedido);
        assertEquals(cliente, pedido.getCliente());
        assertEquals(pagamento, pedido.getPagamento());
        assertEquals(SituacaoPedido.REALIZADO, pedido.getSituacao());
        assertNotNull(pedido.getDataHora());
    }

    @Test
    @DisplayName("Quando criar o pedido, então a data e hora devem ser definidas como o momento atual")
    void quandoCriarPedido_entaoDataHoraDeveSerOAtual() {
        PedidoEntrada pedidoEntrada = PedidoEntrada.create(1L, null, null);

        Pedido pedido = pedidoCriaUseCase.criaPedido(pedidoEntrada, cliente, pagamento);

        assertNotNull(pedido.getDataHora());
        assertEquals(LocalDateTime.now().getYear(), pedido.getDataHora().getYear());
        assertEquals(LocalDateTime.now().getMonth(), pedido.getDataHora().getMonth());
        assertEquals(LocalDateTime.now().getDayOfMonth(), pedido.getDataHora().getDayOfMonth());
    }
}
