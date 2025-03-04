package br.com.on.fiap.hexagono.adaptadores.controladores.impl;

import br.com.on.fiap.hexagono.adaptadores.apresentadores.ClienteApresentador;
import br.com.on.fiap.hexagono.adaptadores.controladores.ClienteControlador;
import br.com.on.fiap.hexagono.adaptadores.dto.ClienteRespostaDTO;
import br.com.on.fiap.hexagono.casodeuso.cliente.dto.ClienteEntradaDTO;
import br.com.on.fiap.hexagono.casodeuso.cliente.dto.ClienteSaidaDTO;
import br.com.on.fiap.hexagono.casodeuso.cliente.entrada.BuscaClientePorCpfCasoDeUso;
import br.com.on.fiap.hexagono.casodeuso.cliente.entrada.InsereClienteCasoDeUso;

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
