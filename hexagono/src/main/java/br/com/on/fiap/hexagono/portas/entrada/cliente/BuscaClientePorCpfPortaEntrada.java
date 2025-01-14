package br.com.on.fiap.hexagono.portas.entrada.cliente;

import br.com.on.fiap.hexagono.dominio.Cliente;

public interface BuscaClientePorCpfPortaEntrada {
	Cliente buscar(String cpf);
}
