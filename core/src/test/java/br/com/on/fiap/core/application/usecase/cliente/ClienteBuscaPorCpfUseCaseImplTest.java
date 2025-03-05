package br.com.on.fiap.core.application.usecase.cliente;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.core.adapter.gateway.ClienteGateway;
import br.com.on.fiap.core.domain.exception.ClienteNaoEncontradoExcecao;
import br.com.on.fiap.core.domain.model.Cliente;
import br.com.on.fiap.core.usecase.cliente.impl.ClienteBuscaPorCpfUseCaseImpl;
import br.com.on.fiap.datapool.DataPoolCliente;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClienteBuscaPorCpfUseCaseImplTest {

    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private ClienteBuscaPorCpfUseCaseImpl clienteBuscaPorCpfUseCase;

    @Test
    @DisplayName("Dado um CPF válido, quando buscar cliente, então o cliente deve ser encontrado")
    void dadoCpfValido_quandoBuscarCliente_entaoClienteDeveSerEncontrado() {
        String cpf = "12345678900";
        Cliente clienteEsperado = DataPoolCliente.clienteComCpf(cpf);
        when(clienteGateway.buscaClientePorCpf(cpf)).thenReturn(Optional.of(clienteEsperado));

        Cliente cliente = clienteBuscaPorCpfUseCase.buscar(cpf);

        assertNotNull(cliente);
        assertEquals(cpf, cliente.getCpf());
        assertEquals(clienteEsperado.getNome(), cliente.getNome());
        verify(clienteGateway).buscaClientePorCpf(cpf);
    }

    @Test
    @DisplayName("Dado um CPF inválido, quando buscar cliente, então deve lançar exceção ClienteNaoEncontradoExcecao")
    void dadoCpfInvalido_quandoBuscarCliente_entaoDeveLancarExcecao() {
        String cpf = "00000000000";

        when(clienteGateway.buscaClientePorCpf(cpf)).thenReturn(Optional.empty());

        ClienteNaoEncontradoExcecao exception =
                assertThrows(ClienteNaoEncontradoExcecao.class, () -> clienteBuscaPorCpfUseCase.buscar(cpf));

        assertEquals("Não foi encontrado Cliente para o cpf: 000.000.000-00", exception.getMessage());

        verify(clienteGateway).buscaClientePorCpf(cpf);
    }
}
