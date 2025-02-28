package br.com.on.fiap.hexagono.usecases.cliente;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.on.fiap.hexagono.datapool.DataPoolCliente;
import br.com.on.fiap.hexagono.entities.entidades.Cliente;
import br.com.on.fiap.hexagono.usecases.casodeuso.cliente.BuscaClienteCasoDeUsoImpl;
import br.com.on.fiap.hexagono.usecases.dto.ClienteSaidaDTO;
import br.com.on.fiap.hexagono.usecases.excecao.ClienteNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.usecases.interfaces.gateway.cliente.ClienteGateway;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BuscaClienteCasoDeUsoImplTest {

    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private BuscaClienteCasoDeUsoImpl buscaClienteCasoDeUsoImpl;

    @Test
    @DisplayName("Dado um CPF válido, quando buscar cliente, então o cliente deve ser encontrado")
    void dadoCpfValido_quandoBuscarCliente_entaoClienteDeveSerEncontrado() {
        String cpf = "12345678900";
        Cliente clienteEsperado = DataPoolCliente.clienteComCpf(cpf);
        when(clienteGateway.buscaClientePorCpf(cpf)).thenReturn(java.util.Optional.of(clienteEsperado));

        ClienteSaidaDTO clienteSaidaDTO = buscaClienteCasoDeUsoImpl.buscar(cpf);

        assertNotNull(clienteSaidaDTO);
        assertEquals(cpf, clienteSaidaDTO.getCpf());
        assertEquals(clienteEsperado.getNome(), clienteSaidaDTO.getNome());
        verify(clienteGateway).buscaClientePorCpf(cpf);
    }

    @Test
    @DisplayName("Dado um CPF inválido, quando buscar cliente, então deve lançar exceção ClienteNaoEncontradoExcecao")
    void dadoCpfInvalido_quandoBuscarCliente_entaoDeveLancarExcecao() {
        String cpf = "00000000000";

        when(clienteGateway.buscaClientePorCpf(cpf)).thenReturn(Optional.empty());

        ClienteNaoEncontradoExcecao exception =
                assertThrows(ClienteNaoEncontradoExcecao.class, () -> buscaClienteCasoDeUsoImpl.buscar(cpf));

        assertEquals("Não foi encontrado Cliente para o cpf: 000.000.000-00", exception.getMessage());

        verify(clienteGateway).buscaClientePorCpf(cpf);
    }
}
