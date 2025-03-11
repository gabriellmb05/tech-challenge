package br.com.on.fiap.core.adapter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.core.adapter.controller.impl.ClienteControllerImpl;
import br.com.on.fiap.core.adapter.presenter.ClientePresenter;
import br.com.on.fiap.core.application.dto.entrada.ClienteEntrada;
import br.com.on.fiap.core.application.dto.resposta.ClienteResposta;
import br.com.on.fiap.core.application.usecase.cliente.ClienteBuscaPorCpfUseCase;
import br.com.on.fiap.core.application.usecase.cliente.ClienteInsereUseCase;
import br.com.on.fiap.core.domain.Cliente;
import br.com.on.fiap.datapool.ClienteDataPool;
import br.com.on.fiap.datapool.ClienteEntradaDataPool;
import br.com.on.fiap.datapool.ClienteRespostaDataPool;
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
        Cliente cliente = ClienteDataPool.criarClienteValido();
        ClienteResposta clienteResposta = ClienteRespostaDataPool.criarClienteValido();
        String cpf = cliente.getCpf();
        when(clienteBuscaPorCpfUseCase.buscar(cpf)).thenReturn(cliente);
        when(clientePresenter.formatar(cliente)).thenReturn(clienteResposta);

        ClienteResposta resultado = clienteController.buscaClientePorCpf(cpf);

        assertNotNull(resultado);
        assertEquals(clienteResposta.getCpf(), resultado.getCpf());
        verify(clienteBuscaPorCpfUseCase).buscar(cpf);
        verify(clientePresenter).formatar(cliente);
    }

    @Test
    @DisplayName(
            "Dado um objecto de dados de entrada, quando inserir ele, então um objeto de dados de saída deve ser retornado no formato esperado")
    void dadoObjetoDeDadosDeEntrada_quandoInserir_entaoDeveSerRetornadoNoFormatoEsperado() {
        ClienteEntrada clienteEntrada = ClienteEntradaDataPool.criarClienteValido();
        Cliente cliente = ClienteDataPool.criarClienteValido();
        ClienteResposta clienteResposta = ClienteRespostaDataPool.criarClienteValido();
        when(clienteInsereUseCase.inserir(clienteEntrada)).thenReturn(cliente);
        when(clientePresenter.formatar(cliente)).thenReturn(clienteResposta);

        ClienteResposta resultado = clienteController.insereCliente(clienteEntrada);

        assertNotNull(resultado);
        assertEquals(clienteResposta.getCpf(), resultado.getCpf());
        verify(clienteInsereUseCase).inserir(clienteEntrada);
        verify(clientePresenter).formatar(cliente);
    }
}
