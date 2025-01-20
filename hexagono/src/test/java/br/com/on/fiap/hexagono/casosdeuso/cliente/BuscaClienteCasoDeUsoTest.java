package br.com.on.fiap.hexagono.casosdeuso.cliente;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.on.fiap.hexagono.datapool.DataPoolCliente;
import br.com.on.fiap.hexagono.dominio.Cliente;
import br.com.on.fiap.hexagono.excecao.ClienteNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.portas.saida.cliente.PersisteClientePortaSaida;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BuscaClienteCasoDeUsoTest {

    @Mock
    private PersisteClientePortaSaida persisteClientePortaSaida;

    @InjectMocks
    private BuscaClienteCasoDeUso buscaClienteCasoDeUso;

    @Test
    @DisplayName("Dado um CPF válido, quando buscar cliente, então o cliente deve ser encontrado")
    void dadoCpfValido_quandoBuscarCliente_entaoClienteDeveSerEncontrado() {
        String cpf = "12345678900";
        Cliente clienteEsperado = DataPoolCliente.clienteComCpf(cpf);

        when(persisteClientePortaSaida.buscaClientePorCpf(cpf)).thenReturn(java.util.Optional.of(clienteEsperado));

        Cliente cliente = buscaClienteCasoDeUso.buscar(cpf);

        assertNotNull(cliente);
        assertEquals(cpf, cliente.getCpf());
        assertEquals(clienteEsperado.getNome(), cliente.getNome());

        verify(persisteClientePortaSaida).buscaClientePorCpf(cpf);
    }

    @Test
    @DisplayName("Dado um CPF inválido, quando buscar cliente, então deve lançar exceção ClienteNaoEncontradoExcecao")
    void dadoCpfInvalido_quandoBuscarCliente_entaoDeveLancarExcecao() {
        String cpf = "00000000000";

        when(persisteClientePortaSaida.buscaClientePorCpf(cpf)).thenReturn(Optional.empty());

        ClienteNaoEncontradoExcecao exception =
                assertThrows(ClienteNaoEncontradoExcecao.class, () -> buscaClienteCasoDeUso.buscar(cpf));

        assertEquals("Não foi encontrado Cliente para o cpf: 000.000.000-00", exception.getMessage());

        verify(persisteClientePortaSaida).buscaClientePorCpf(cpf);
    }
}
