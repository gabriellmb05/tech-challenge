package br.com.on.fiap.core.adapter.gateway;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.core.adapter.datasource.ClienteDataSource;
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
class ClienteGatewayImplTest {

    @Mock
    private ClienteDataSource clienteDataSource;

    @InjectMocks
    private ClienteGatewayImpl clienteGateway;

    @Test
    @DisplayName("Dado um CPF válido, quando buscar cliente por CPF, então deve retornar o cliente")
    void dadoCpfValido_quandoBuscarClientePorCpf_entaoDeveRetornarCliente() {
        Cliente cliente = ClienteDataPool.criarClienteValido();
        String cpf = cliente.getCpf();
        when(clienteDataSource.findByNmCpf(cpf)).thenReturn(Optional.of(cliente));

        Optional<Cliente> resultado = clienteGateway.buscaClientePorCpf(cpf);

        assertTrue(resultado.isPresent());
        assertEquals(cliente, resultado.get());
        verify(clienteDataSource).findByNmCpf(cpf);
    }

    @Test
    @DisplayName("Dado um ID válido, quando buscar cliente por ID, então deve retornar o cliente")
    void dadoIdValido_quandoBuscarClientePorId_entaoDeveRetornarCliente() {
        Cliente cliente = ClienteDataPool.criarClienteValido();
        Long id = cliente.getId();
        when(clienteDataSource.findById(id)).thenReturn(Optional.of(cliente));

        Optional<Cliente> resultado = clienteGateway.buscaClientePorId(id);

        assertTrue(resultado.isPresent());
        assertEquals(cliente, resultado.get());
        verify(clienteDataSource).findById(id);
    }

    @Test
    @DisplayName("Dado um email válido, quando buscar cliente por email, então deve retornar o cliente")
    void dadoEmailValido_quandoBuscarClientePorEmail_entaoDeveRetornarCliente() {
        Cliente cliente = ClienteDataPool.criarClienteValido();
        String email = cliente.getEmail();
        when(clienteDataSource.findByNmEmail(email)).thenReturn(Optional.of(cliente));

        Optional<Cliente> resultado = clienteGateway.buscaClientePorEmail(email);

        assertTrue(resultado.isPresent());
        assertEquals(cliente, resultado.get());
        verify(clienteDataSource).findByNmEmail(email);
    }

    @Test
    @DisplayName("Dado um cliente válido, quando salvar cliente, então deve retornar o cliente salvo")
    void dadoClienteValido_quandoSalvarCliente_entaoDeveRetornarClienteSalvo() {
        Cliente cliente = ClienteDataPool.criarClienteValido();
        when(clienteDataSource.persist(cliente)).thenReturn(cliente);

        Cliente resultado = clienteGateway.salvaCliente(cliente);

        assertNotNull(resultado);
        assertEquals(cliente, resultado);
        verify(clienteDataSource).persist(cliente);
    }
}
