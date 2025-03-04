package br.com.on.fiap.core.adapter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.core.adapter.controller.impl.ClienteControllerImpl;
import br.com.on.fiap.core.adapter.presenter.ClientePresenter;
import br.com.on.fiap.core.domain.model.ClienteEntradaDTO;
import br.com.on.fiap.core.domain.model.ClienteRespostaDTO;
import br.com.on.fiap.core.usecase.cliente.ClienteBuscaPorCpfUseCase;
import br.com.on.fiap.core.usecase.cliente.ClienteInsereUseCase;
import br.com.on.fiap.core.domain.model.Cliente;
import br.com.on.fiap.datapool.DataPoolCliente;
import br.com.on.fiap.datapool.DataPoolClienteEntradaDTO;
import br.com.on.fiap.datapool.DataPoolClienteRespostaDTO;
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
        Cliente cliente = DataPoolCliente.clienteValido();
        ClienteRespostaDTO clienteRespostaDTO = DataPoolClienteRespostaDTO.clienteValido();
        String cpf = cliente.getCpf();
        when(clienteBuscaPorCpfUseCase.buscar(cpf)).thenReturn(cliente);
        when(clientePresenter.formatar(cliente)).thenReturn(clienteRespostaDTO);

        ClienteRespostaDTO resultado = clienteController.buscaClientePorCpf(cpf);

        assertNotNull(resultado);
        assertEquals(clienteRespostaDTO.getCpf(), resultado.getCpf());
        verify(clienteBuscaPorCpfUseCase).buscar(cpf);
        verify(clientePresenter).formatar(cliente);
    }

    @Test
    @DisplayName(
            "Dado um objecto de dados de entrada, quando inserir ele, então um objeto de dados de saída deve ser retornado no formato esperado")
    void dadoObjetoDeDadosDeEntrada_quandoInserir_entaoDeveSerRetornadoNoFormatoEsperado() {
        ClienteEntradaDTO clienteEntradaDTO = DataPoolClienteEntradaDTO.clienteValido();
        Cliente cliente = DataPoolCliente.clienteValido();
        ClienteRespostaDTO clienteRespostaDTO = DataPoolClienteRespostaDTO.clienteValido();
        when(clienteInsereUseCase.inserir(clienteEntradaDTO)).thenReturn(cliente);
        when(clientePresenter.formatar(cliente)).thenReturn(clienteRespostaDTO);

        ClienteRespostaDTO resultado = clienteController.insereCliente(clienteEntradaDTO);

        assertNotNull(resultado);
        assertEquals(clienteRespostaDTO.getCpf(), resultado.getCpf());
        verify(clienteInsereUseCase).inserir(clienteEntradaDTO);
        verify(clientePresenter).formatar(cliente);
    }
}
