package br.com.on.fiap.hexagono.interfaceadapters.controladores;

import br.com.on.fiap.hexagono.interfaceadapters.interfaces.controller.ClienteControlador;
import br.com.on.fiap.hexagono.interfaceadapters.interfaces.dto.ClienteRespostaDTO;
import br.com.on.fiap.hexagono.interfaceadapters.interfaces.presenter.ClienteApresentador;
import br.com.on.fiap.hexagono.usecases.dto.ClienteEntradaDTO;
import br.com.on.fiap.hexagono.usecases.dto.ClienteSaidaDTO;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.cliente.BuscaClientePorCpfCasoDeUso;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.cliente.InsereClienteCasoDeUso;

public class ClienteControladorImpl implements ClienteControlador {

    private final InsereClienteCasoDeUso insereClienteCasoDeUso;
    private final BuscaClientePorCpfCasoDeUso buscaClientePorCpfCasoDeUso;
    private final ClienteApresentador clienteApresentador;

    public ClienteControladorImpl(
            InsereClienteCasoDeUso insereClienteCasoDeUso,
            BuscaClientePorCpfCasoDeUso buscaClientePorCpfCasoDeUso,
            ClienteApresentador clienteApresentador) {
        this.insereClienteCasoDeUso = insereClienteCasoDeUso;
        this.buscaClientePorCpfCasoDeUso = buscaClientePorCpfCasoDeUso;
        this.clienteApresentador = clienteApresentador;
    }

    @Override
    public ClienteRespostaDTO buscaClientePorCpf(String cpf) {
        ClienteSaidaDTO clienteSaidaDTO = buscaClientePorCpfCasoDeUso.buscar(cpf);
        return clienteApresentador.formatar(clienteSaidaDTO);
    }

    @Override
    public ClienteRespostaDTO insereCliente(ClienteEntradaDTO clienteEntradaDTO) {
        ClienteSaidaDTO clienteSaidaDTO = insereClienteCasoDeUso.inserir(clienteEntradaDTO);
        return clienteApresentador.formatar(clienteSaidaDTO);
    }
}
