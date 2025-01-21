package br.com.on.fiap.adaptadores.entrada.controlador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta.ClienteRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.solicitacao.ClienteSolicitacaoDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.mapeador.ClienteEntradaMapeador;
import br.com.on.fiap.datapool.DataPoolCliente;
import br.com.on.fiap.datapool.DataPoolClienteRespostaDTO;
import br.com.on.fiap.datapool.DataPoolClienteSolicitacaoDTO;
import br.com.on.fiap.hexagono.dominio.Cliente;
import br.com.on.fiap.hexagono.portas.entrada.cliente.BuscaClientePorCpfPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.cliente.InsereClientePortaEntrada;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ClienteControladorTest {

    @Mock
    private InsereClientePortaEntrada insereClientePortaEntrada;

    @Mock
    private ClienteEntradaMapeador clienteEntradaMapeador;

    @Mock
    private BuscaClientePorCpfPortaEntrada buscaClientePorCpfPortaEntrada;

    @InjectMocks
    private ClienteControlador clienteControlador;

    @Test
    @DisplayName("Dado um cliente existente, quando buscar o cliente por cpf, então ele deve ser retornado")
    void dadoClienteExistente_quandoBuscarCliente_entaoDeveSerRetornado() {
        String cpf = "43316652616";
        Cliente cliente = DataPoolCliente.gerarCliente();
        ClienteRespostaDTO clienteRespostaDTO = DataPoolClienteRespostaDTO.gerarCliente();
        when(buscaClientePorCpfPortaEntrada.buscar(cpf)).thenReturn(cliente);
        when(clienteEntradaMapeador.paraClienteDTO(cliente)).thenReturn(clienteRespostaDTO);

        ResponseEntity<ClienteRespostaDTO> response = clienteControlador.buscaClientePorCpf(cpf);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clienteRespostaDTO, response.getBody());
        verify(buscaClientePorCpfPortaEntrada).buscar(cpf);
        verify(clienteEntradaMapeador).paraClienteDTO(cliente);
    }

    @Test
    @DisplayName("Dado um cliente novo, quando inserir o cliente, então ele deve ser salvo")
    void dadoClienteNovo_quandoInserirCliente_entaoDeveSerSalvo() {
        Cliente cliente = DataPoolCliente.gerarCliente();
        ClienteSolicitacaoDTO clienteSolicitacaoDTO = DataPoolClienteSolicitacaoDTO.gerarCliente();
        ClienteRespostaDTO clienteRespostaDTO = DataPoolClienteRespostaDTO.gerarCliente();
        when(clienteEntradaMapeador.paraCliente(clienteSolicitacaoDTO)).thenReturn(cliente);
        when(insereClientePortaEntrada.inserir(cliente)).thenReturn(cliente);
        when(clienteEntradaMapeador.paraClienteDTO(cliente)).thenReturn(clienteRespostaDTO);

        ResponseEntity<ClienteRespostaDTO> response = clienteControlador.insereCliente(clienteSolicitacaoDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(clienteRespostaDTO, response.getBody());
        verify(clienteEntradaMapeador).paraCliente(clienteSolicitacaoDTO);
        verify(insereClientePortaEntrada).inserir(cliente);
        verify(clienteEntradaMapeador).paraClienteDTO(cliente);
    }
}
