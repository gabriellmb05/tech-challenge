package br.com.on.fiap.hexagono.adaptadores.controladores;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.hexagono.adaptadores.apresentadores.ClienteApresentador;
import br.com.on.fiap.hexagono.adaptadores.controladores.impl.ClienteControladorImpl;
import br.com.on.fiap.hexagono.adaptadores.dto.ClienteRespostaDTO;
import br.com.on.fiap.hexagono.casodeuso.cliente.dto.ClienteEntradaDTO;
import br.com.on.fiap.hexagono.casodeuso.cliente.dto.ClienteSaidaDTO;
import br.com.on.fiap.hexagono.casodeuso.cliente.entrada.BuscaClientePorCpfCasoDeUso;
import br.com.on.fiap.hexagono.casodeuso.cliente.entrada.InsereClienteCasoDeUso;
import br.com.on.fiap.hexagono.datapool.DataPoolClienteEntradaDTO;
import br.com.on.fiap.hexagono.datapool.DataPoolClienteRespostaDTO;
import br.com.on.fiap.hexagono.datapool.DataPoolClienteSaidaDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClienteControladorImplTest {

    @Mock
    private InsereClienteCasoDeUso insereClienteCasoDeUso;

    @Mock
    private BuscaClientePorCpfCasoDeUso buscaClientePorCpfCasoDeUso;

    @Mock
    private ClienteApresentador clienteApresentador;

    @InjectMocks
    private ClienteControladorImpl clienteControlador;

    @Test
    @DisplayName(
            "Dado um cpf, quando buscar o cliente pelo CPF, então um cliente deve ser retornado no formato esperado")
    void dadoCpf_quandoBuscarClientePorCpf_entaoDeveSerRetornadoClienteFormatado() {
        ClienteSaidaDTO clienteSaidaDTO = DataPoolClienteSaidaDTO.clienteValido();
        ClienteRespostaDTO clienteRespostaDTO = DataPoolClienteRespostaDTO.clienteValido();
        String cpf = clienteSaidaDTO.getCpf();
        when(buscaClientePorCpfCasoDeUso.buscar(cpf)).thenReturn(clienteSaidaDTO);
        when(clienteApresentador.formatar(clienteSaidaDTO)).thenReturn(clienteRespostaDTO);

        ClienteRespostaDTO resultado = clienteControlador.buscaClientePorCpf(cpf);

        assertNotNull(resultado);
        assertEquals(clienteRespostaDTO.getCpf(), resultado.getCpf());
        verify(buscaClientePorCpfCasoDeUso).buscar(cpf);
        verify(clienteApresentador).formatar(clienteSaidaDTO);
    }

    @Test
    @DisplayName(
            "Dado um objecto de dados de entrada, quando inserir ele, então um objeto de dados de saída deve ser retornado no formato esperado")
    void dadoObjetoDeDadosDeEntrada_quandoInserir_entaoDeveSerRetornadoNoFormatoEsperado() {
        ClienteEntradaDTO clienteEntradaDTO = DataPoolClienteEntradaDTO.clienteValido();
        ClienteSaidaDTO clienteSaidaDTO = DataPoolClienteSaidaDTO.clienteValido();
        ClienteRespostaDTO clienteRespostaDTO = DataPoolClienteRespostaDTO.clienteValido();
        when(insereClienteCasoDeUso.inserir(clienteEntradaDTO)).thenReturn(clienteSaidaDTO);
        when(clienteApresentador.formatar(clienteSaidaDTO)).thenReturn(clienteRespostaDTO);

        ClienteRespostaDTO resultado = clienteControlador.insereCliente(clienteEntradaDTO);

        assertNotNull(resultado);
        assertEquals(clienteRespostaDTO.getCpf(), resultado.getCpf());
        verify(insereClienteCasoDeUso).inserir(clienteEntradaDTO);
        verify(clienteApresentador).formatar(clienteSaidaDTO);
    }
}
