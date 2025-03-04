package br.com.on.fiap.hexagono.casodeuso.cliente.entrada;

import br.com.on.fiap.hexagono.casodeuso.cliente.dto.ClienteSaidaDTO;

public interface BuscaClientePorCpfCasoDeUso {
    ClienteSaidaDTO buscar(String cpf);
}
