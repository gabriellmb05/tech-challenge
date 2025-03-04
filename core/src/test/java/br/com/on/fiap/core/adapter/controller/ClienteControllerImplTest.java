package br.com.on.fiap.core.adapter.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.core.adapter.controller.impl.ClienteControllerImpl;
import br.com.on.fiap.core.adapter.presenter.ClientePresenter;
import br.com.on.fiap.core.application.dto.ClienteEntradaDTO;
import br.com.on.fiap.core.application.dto.ClienteRespostaDTO;
import br.com.on.fiap.core.application.dto.ClienteSaidaDTO;
import br.com.on.fiap.core.datapool.DataPoolClienteEntradaDTO;
import br.com.on.fiap.core.datapool.DataPoolClienteRespostaDTO;
import br.com.on.fiap.core.datapool.DataPoolClienteSaidaDTO;
import br.com.on.fiap.core.application.usecase.cliente.ClienteBuscaPorCpfUseCase;
import br.com.on.fiap.core.application.usecase.cliente.ClienteInsereUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClienteControllerImplTest {

    @Mock
    private ClienteInsereUseCase clienteInsereUseCase;

    @Mock
    private ClienteBuscaPorCpfUseCase clienteBuscaPorCpfUseCase;

    @Mock
    private ClientePresenter clientePresenter;

    @InjectMocks
    private ClienteControllerImpl clienteController;

    @Test
    @DisplayName(
            "Dado um cpf, quando buscar o cliente pelo CPF, então um cliente deve ser retornado no formato esperado")
    void dadoCpf_quandoBuscarClientePorCpf_entaoDeveSerRetornadoClienteFormatado() {
        ClienteSaidaDTO clienteSaidaDTO = DataPoolClienteSaidaDTO.clienteValido();
        ClienteRespostaDTO clienteRespostaDTO = DataPoolClienteRespostaDTO.clienteValido();
        String cpf = clienteSaidaDTO.getCpf();
        when(clienteBuscaPorCpfUseCase.buscar(cpf)).thenReturn(clienteSaidaDTO);
        when(clientePresenter.formatar(clienteSaidaDTO)).thenReturn(clienteRespostaDTO);

        ClienteRespostaDTO resultado = clienteController.buscaClientePorCpf(cpf);

        assertNotNull(resultado);
        assertEquals(clienteRespostaDTO.getCpf(), resultado.getCpf());
        verify(clienteBuscaPorCpfUseCase).buscar(cpf);
        verify(clientePresenter).formatar(clienteSaidaDTO);
    }

    @Test
    @DisplayName(
            "Dado um objecto de dados de entrada, quando inserir ele, então um objeto de dados de saída deve ser retornado no formato esperado")
    void dadoObjetoDeDadosDeEntrada_quandoInserir_entaoDeveSerRetornadoNoFormatoEsperado() {
        ClienteEntradaDTO clienteEntradaDTO = DataPoolClienteEntradaDTO.clienteValido();
        ClienteSaidaDTO clienteSaidaDTO = DataPoolClienteSaidaDTO.clienteValido();
        ClienteRespostaDTO clienteRespostaDTO = DataPoolClienteRespostaDTO.clienteValido();
        when(clienteInsereUseCase.inserir(clienteEntradaDTO)).thenReturn(clienteSaidaDTO);
        when(clientePresenter.formatar(clienteSaidaDTO)).thenReturn(clienteRespostaDTO);

        ClienteRespostaDTO resultado = clienteController.insereCliente(clienteEntradaDTO);

        assertNotNull(resultado);
        assertEquals(clienteRespostaDTO.getCpf(), resultado.getCpf());
        verify(clienteInsereUseCase).inserir(clienteEntradaDTO);
        verify(clientePresenter).formatar(clienteSaidaDTO);
    }
}
