package br.com.on.fiap.hexagono.usecase.cliente;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.on.fiap.hexagono.adapter.gateway.ClienteGateway;
import br.com.on.fiap.hexagono.application.dto.ClienteEntradaDTO;
import br.com.on.fiap.hexagono.application.dto.ClienteSaidaDTO;
import br.com.on.fiap.hexagono.usecase.cliente.impl.ClienteInsereUseCaseImpl;
import br.com.on.fiap.hexagono.datapool.DataPoolCliente;
import br.com.on.fiap.hexagono.datapool.DataPoolClienteEntradaDTO;
import br.com.on.fiap.hexagono.domain.entity.Cliente;
import br.com.on.fiap.hexagono.domain.exception.ClienteExistenteExcecao;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClienteInsereUseCaseImplTest {

    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private ClienteInsereUseCaseImpl clienteInsereUseCase;

    @Test
    @DisplayName("Dado um cliente válido, quando inserir o cliente, então o cliente deve ser salvo com sucesso")
    void dadoClienteValido_quandoInserirCliente_entaoClienteDeveSerSalvo() {
        Cliente cliente = DataPoolCliente.clienteValidoSemId();
        ClienteEntradaDTO clienteEntradaDTO = DataPoolClienteEntradaDTO.clienteValido();

        when(clienteGateway.buscaClientePorCpf(cliente.getCpf())).thenReturn(Optional.empty());
        when(clienteGateway.buscaClientePorEmail(cliente.getEmail())).thenReturn(Optional.empty());
        when(clienteGateway.salvaCliente(any())).thenReturn(cliente);

        ClienteSaidaDTO clienteSaidaDTO = clienteInsereUseCase.inserir(clienteEntradaDTO);

        assertNotNull(clienteSaidaDTO);
        assertEquals(cliente.getCpf(), clienteSaidaDTO.getCpf());
        assertEquals(cliente.getEmail(), clienteSaidaDTO.getEmail());

        verify(clienteGateway).buscaClientePorCpf(cliente.getCpf());
        verify(clienteGateway).buscaClientePorEmail(cliente.getEmail());
        verify(clienteGateway).salvaCliente(any());
    }

    @Test
    @DisplayName(
            "Dado um cliente com CPF existente, quando inserir o cliente, então deve lançar exceção ClienteExistenteExcecao")
    void dadoClienteComCpfExistente_quandoInserirCliente_entaoDeveLancarExcecao() {
        Cliente cliente = DataPoolCliente.clienteValido();
        ClienteEntradaDTO clienteEntradaDTO = DataPoolClienteEntradaDTO.clienteValido();

        when(clienteGateway.buscaClientePorCpf(cliente.getCpf())).thenReturn(Optional.of(cliente));

        ClienteExistenteExcecao exception =
                assertThrows(ClienteExistenteExcecao.class, () -> clienteInsereUseCase.inserir(clienteEntradaDTO));

        assertEquals("O CPF de número 123.456.789-00 já foi utilizado", exception.getMessage());

        verify(clienteGateway).buscaClientePorCpf(cliente.getCpf());
        verify(clienteGateway, never()).salvaCliente(cliente);
    }

    @Test
    @DisplayName(
            "Dado um cliente com email existente, quando inserir o cliente, então deve lançar exceção ClienteExistenteExcecao")
    void dadoClienteComEmailExistente_quandoInserirCliente_entaoDeveLancarExcecao() {
        Cliente cliente = DataPoolCliente.clienteValido();
        ClienteEntradaDTO clienteEntradaDTO = DataPoolClienteEntradaDTO.clienteValido();

        when(clienteGateway.buscaClientePorCpf(cliente.getCpf())).thenReturn(Optional.empty());
        when(clienteGateway.buscaClientePorEmail(cliente.getEmail())).thenReturn(Optional.of(cliente));

        ClienteExistenteExcecao exception = assertThrows(ClienteExistenteExcecao.class, () -> {
            clienteInsereUseCase.inserir(clienteEntradaDTO);
        });

        assertEquals("O E-mail carlos.souza@example.com já foi utilizado", exception.getMessage());

        verify(clienteGateway).buscaClientePorCpf(cliente.getCpf());
        verify(clienteGateway).buscaClientePorEmail(cliente.getEmail());
        verify(clienteGateway, never()).salvaCliente(cliente);
    }
}
