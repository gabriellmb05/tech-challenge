package br.com.on.fiap.adapter.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.adapter.input.dto.request.ClienteSolicitacaoDTO;
import br.com.on.fiap.adapter.input.mapper.ClienteInputMapper;
import br.com.on.fiap.datapool.DataPoolClienteEntradaDTO;
import br.com.on.fiap.datapool.DataPoolClienteRespostaDTO;
import br.com.on.fiap.datapool.DataPoolClienteSolicitacaoDTO;
import br.com.on.fiap.hexagono.adapter.controller.ClienteController;
import br.com.on.fiap.hexagono.application.dto.ClienteEntradaDTO;
import br.com.on.fiap.hexagono.application.dto.ClienteRespostaDTO;
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
    private ClienteInputMapper clienteInputMapper;

    @Mock
    private ClienteController clienteController;

    @InjectMocks
    private ClienteApi clienteApi;

    @Test
    @DisplayName("Dado um cliente existente, quando buscar o cliente por cpf, então ele deve ser retornado")
    void dadoClienteExistente_quandoBuscarCliente_entaoDeveSerRetornado() {
        String cpf = "43316652616";
        ClienteRespostaDTO clienteRespostaDTO = DataPoolClienteRespostaDTO.gerarCliente();
        when(clienteController.buscaClientePorCpf(cpf)).thenReturn(clienteRespostaDTO);
        ResponseEntity<ClienteRespostaDTO> response = clienteApi.buscaClientePorCpf(cpf);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clienteRespostaDTO, response.getBody());
        verify(clienteController).buscaClientePorCpf(cpf);
    }

    @Test
    @DisplayName("Dado um cliente novo, quando inserir o cliente, então ele deve ser salvo")
    void dadoClienteNovo_quandoInserirCliente_entaoDeveSerSalvo() {
        ClienteEntradaDTO clienteEntradaDTO = DataPoolClienteEntradaDTO.gerarCliente();
        ClienteSolicitacaoDTO clienteSolicitacaoDTO = DataPoolClienteSolicitacaoDTO.gerarCliente();
        ClienteRespostaDTO clienteRespostaDTO = DataPoolClienteRespostaDTO.gerarCliente();
        when(clienteInputMapper.paraClienteDTO(clienteSolicitacaoDTO)).thenReturn(clienteEntradaDTO);
        when(clienteController.insereCliente(clienteEntradaDTO)).thenReturn(clienteRespostaDTO);

        ResponseEntity<ClienteRespostaDTO> response = clienteApi.insereCliente(clienteSolicitacaoDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(clienteRespostaDTO, response.getBody());
        verify(clienteInputMapper).paraClienteDTO(clienteSolicitacaoDTO);
    }
}
