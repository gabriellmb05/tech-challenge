package br.com.on.fiap.hexagono.casosdeuso.cliente;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.on.fiap.hexagono.datapool.DataPoolCliente;
import br.com.on.fiap.hexagono.dominio.Cliente;
import br.com.on.fiap.hexagono.excecao.ClienteExistenteExcecao;
import br.com.on.fiap.hexagono.portas.saida.cliente.PersisteClientePortaSaida;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InsereClienteCasoDeUsoTest {

    @Mock
    private PersisteClientePortaSaida persisteClientePortaSaida;

    @InjectMocks
    private InsereClienteCasoDeUso insereClienteCasoDeUso;

    @Test
    @DisplayName("Dado um cliente válido, quando inserir o cliente, então o cliente deve ser salvo com sucesso")
    void dadoClienteValido_quandoInserirCliente_entaoClienteDeveSerSalvo() {
        Cliente cliente = DataPoolCliente.clienteValido();

        when(persisteClientePortaSaida.buscaClientePorCpf(cliente.getCpf())).thenReturn(Optional.empty());
        when(persisteClientePortaSaida.buscaClientePorEmail(cliente.getEmail())).thenReturn(Optional.empty());
        when(persisteClientePortaSaida.salvaCliente(cliente)).thenReturn(cliente);

        Cliente clienteSalvo = insereClienteCasoDeUso.inserir(cliente);

        assertNotNull(clienteSalvo);
        assertEquals(cliente.getCpf(), clienteSalvo.getCpf());
        assertEquals(cliente.getEmail(), clienteSalvo.getEmail());

        verify(persisteClientePortaSaida).buscaClientePorCpf(cliente.getCpf());
        verify(persisteClientePortaSaida).buscaClientePorEmail(cliente.getEmail());
        verify(persisteClientePortaSaida).salvaCliente(cliente);
    }

    @Test
    @DisplayName(
            "Dado um cliente com CPF existente, quando inserir o cliente, então deve lançar exceção ClienteExistenteExcecao")
    void dadoClienteComCpfExistente_quandoInserirCliente_entaoDeveLancarExcecao() {
        Cliente cliente = DataPoolCliente.clienteValido();

        when(persisteClientePortaSaida.buscaClientePorCpf(cliente.getCpf())).thenReturn(Optional.of(cliente));

        ClienteExistenteExcecao exception =
                assertThrows(ClienteExistenteExcecao.class, () -> insereClienteCasoDeUso.inserir(cliente));

        assertEquals("O CPF de número 123.456.789-00 já foi utilizado", exception.getMessage());

        verify(persisteClientePortaSaida).buscaClientePorCpf(cliente.getCpf());
        verify(persisteClientePortaSaida, never()).salvaCliente(cliente);
    }

    @Test
    @DisplayName(
            "Dado um cliente com email existente, quando inserir o cliente, então deve lançar exceção ClienteExistenteExcecao")
    void dadoClienteComEmailExistente_quandoInserirCliente_entaoDeveLancarExcecao() {
        Cliente cliente = DataPoolCliente.clienteValido();

        when(persisteClientePortaSaida.buscaClientePorCpf(cliente.getCpf())).thenReturn(Optional.empty());
        when(persisteClientePortaSaida.buscaClientePorEmail(cliente.getEmail())).thenReturn(Optional.of(cliente));

        ClienteExistenteExcecao exception = assertThrows(ClienteExistenteExcecao.class, () -> {
            insereClienteCasoDeUso.inserir(cliente);
        });

        assertEquals("O E-mail carlos.souza@example.com já foi utilizado", exception.getMessage());

        verify(persisteClientePortaSaida).buscaClientePorCpf(cliente.getCpf());
        verify(persisteClientePortaSaida).buscaClientePorEmail(cliente.getEmail());
        verify(persisteClientePortaSaida, never()).salvaCliente(cliente);
    }
}
