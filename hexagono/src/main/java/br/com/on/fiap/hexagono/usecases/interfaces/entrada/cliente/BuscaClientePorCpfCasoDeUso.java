package br.com.on.fiap.hexagono.usecases.interfaces.entrada.cliente;

import br.com.on.fiap.hexagono.usecases.dto.ClienteSaidaDTO;

public interface BuscaClientePorCpfCasoDeUso {
    ClienteSaidaDTO buscar(String cpf);
}
