package br.com.on.fiap.adapter.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.datapool.DataPoolClienteRespostaDTO;
import br.com.on.datapool.DataPoolClienteSolicitacaoDTO;
import br.com.on.fiap.adapter.input.dto.entrada.ClienteRequest;
import br.com.on.fiap.core.adapter.controller.ClienteController;
import br.com.on.fiap.core.application.dto.resposta.ClienteResposta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ClienteApiTest {

    @Mock
    private ClienteController clienteController;

    @InjectMocks
    private ClienteApi clienteApi;

    @Test
    @DisplayName("Dado um cliente existente, quando buscar o cliente por cpf, então ele deve ser retornado")
    void dadoClienteExistente_quandoBuscarCliente_entaoDeveSerRetornado() {
        String cpf = "43316652616";
        ClienteResposta clienteResposta = DataPoolClienteRespostaDTO.gerarCliente();

        when(clienteController.buscaClientePorCpf(cpf)).thenReturn(clienteResposta);

        ResponseEntity<ClienteResposta> response = clienteApi.buscaClientePorCpf(cpf);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clienteResposta, response.getBody());
        verify(clienteController).buscaClientePorCpf(cpf);
    }

    @Test
    @DisplayName("Dado um cliente novo, quando inserir o cliente, então ele deve ser salvo")
    void dadoClienteNovo_quandoInserirCliente_entaoDeveSerSalvo() {
        ClienteRequest clienteRequest = DataPoolClienteSolicitacaoDTO.gerarCliente();
        ClienteResposta clienteResposta = DataPoolClienteRespostaDTO.gerarCliente();

        when(clienteController.insereCliente(clienteRequest)).thenReturn(clienteResposta);

        ResponseEntity<ClienteResposta> response = clienteApi.insereCliente(clienteRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(clienteResposta, response.getBody());
        verify(clienteController).insereCliente(clienteRequest);
    }
}
