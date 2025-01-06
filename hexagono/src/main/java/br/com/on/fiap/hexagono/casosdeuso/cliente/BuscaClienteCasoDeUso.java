package br.com.on.fiap.hexagono.casosdeuso.cliente;

import br.com.on.fiap.hexagono.dominio.Cliente;
import br.com.on.fiap.hexagono.excecao.ApplicationExcecaoPadrao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
import br.com.on.fiap.hexagono.portas.entrada.BuscaClientePorCpfPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.PersisteClientePortaSaida;
import java.util.Optional;

public class BuscaClienteCasoDeUso implements BuscaClientePorCpfPortaEntrada {

  private final PersisteClientePortaSaida persisteClientePortaSaida;

  public BuscaClienteCasoDeUso(PersisteClientePortaSaida persisteClientePortaSaida) {
    this.persisteClientePortaSaida = persisteClientePortaSaida;
  }

  @Override
  public Cliente buscar(String cpf) {
    Optional<Cliente> cliente = persisteClientePortaSaida.buscaClientePorCpf(cpf);
    return cliente.orElseThrow(
        () ->
            new ApplicationExcecaoPadrao(
                MessageError.MSG_ERR_CLIENTE_NAO_CADASTRADO.getMensagem(), cpf));
  }
}
