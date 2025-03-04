package br.com.on.fiap.adaptadores.cliente.entrada;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.adaptadores.cliente.entrada.dto.solicitacao.ClienteSolicitacaoDTO;
import br.com.on.fiap.adaptadores.cliente.entrada.mapeador.ClienteEntradaMapeador;
import br.com.on.fiap.datapool.DataPoolClienteEntradaDTO;
import br.com.on.fiap.datapool.DataPoolClienteRespostaDTO;
import br.com.on.fiap.datapool.DataPoolClienteSolicitacaoDTO;
import br.com.on.fiap.hexagono.adapter.controller.ClienteController;
import br.com.on.fiap.hexagono.usecase.dto.ClienteEntradaDTO;
import br.com.on.fiap.hexagono.usecase.dto.ClienteRespostaDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ClienteManipuladorTest {

    @Mock
    private ClienteEntradaMapeador clienteEntradaMapeador;

    @Mock
    private ClienteController clienteController;

    @InjectMocks
    private ClienteManipulador clienteManipulador;

    @Test
    @DisplayName("Dado um cliente existente, quando buscar o cliente por cpf, então ele deve ser retornado")
    void dadoClienteExistente_quandoBuscarCliente_entaoDeveSerRetornado() {
        String cpf = "43316652616";
        ClienteRespostaDTO clienteRespostaDTO = DataPoolClienteRespostaDTO.gerarCliente();
        when(clienteController.buscaClientePorCpf(cpf)).thenReturn(clienteRespostaDTO);
        ResponseEntity<ClienteRespostaDTO> response = clienteManipulador.buscaClientePorCpf(cpf);

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
        when(clienteEntradaMapeador.paraClienteDTO(clienteSolicitacaoDTO)).thenReturn(clienteEntradaDTO);
        when(clienteController.insereCliente(clienteEntradaDTO)).thenReturn(clienteRespostaDTO);

        ResponseEntity<ClienteRespostaDTO> response = clienteManipulador.insereCliente(clienteSolicitacaoDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(clienteRespostaDTO, response.getBody());
        verify(clienteEntradaMapeador).paraClienteDTO(clienteSolicitacaoDTO);
    }
}
