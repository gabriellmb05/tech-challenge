package br.com.on.fiap.hexagono.adaptadores.controladores;

import br.com.on.fiap.hexagono.adaptadores.dto.ClienteRespostaDTO;
import br.com.on.fiap.hexagono.casodeuso.cliente.dto.ClienteEntradaDTO;

public interface ClienteControlador {
    ClienteRespostaDTO buscaClientePorCpf(String cpf);

    ClienteRespostaDTO insereCliente(ClienteEntradaDTO cliente);
}
