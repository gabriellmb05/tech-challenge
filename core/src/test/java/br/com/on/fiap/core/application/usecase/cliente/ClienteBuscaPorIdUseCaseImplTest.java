package br.com.on.fiap.core.application.usecase.cliente;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.core.application.exception.ClienteNaoEncontradoExcecao;
import br.com.on.fiap.core.application.gateway.ClienteGateway;
import br.com.on.fiap.core.application.usecase.cliente.impl.ClienteBuscaPorIdUseCaseImpl;
import br.com.on.fiap.core.domain.Cliente;
import br.com.on.fiap.datapool.ClienteDataPool;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClienteBuscaPorIdUseCaseImplTest {

    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private ClienteBuscaPorIdUseCaseImpl clienteBuscaPorIdUseCase;

    @Test
    @DisplayName("Quando o cliente existir, deve retornar o cliente corretamente")
    void quandoClienteExistir_deveRetornarCliente() {
        when(clienteGateway.buscaClientePorId(1L)).thenReturn(Optional.of(ClienteDataPool.criarClienteValido()));

        Cliente resultado = clienteBuscaPorIdUseCase.buscar(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Carlos Souza", resultado.getNome());

        verify(clienteGateway).buscaClientePorId(1L);
    }

    @Test
    @DisplayName("Quando o cliente não existir, deve lançar a exceção ClienteNaoEncontradoExcecao")
    void quandoClienteNaoExistir_deveLancarClienteNaoEncontradoExcecao() {
        when(clienteGateway.buscaClientePorId(1L)).thenReturn(Optional.empty());

        ClienteNaoEncontradoExcecao exception =
                assertThrows(ClienteNaoEncontradoExcecao.class, () -> clienteBuscaPorIdUseCase.buscar(1L));

        assertTrue(exception.getMessage().contains("Não foi encontrado o Cliente para o id: 1"));

        verify(clienteGateway).buscaClientePorId(1L);
    }
}
