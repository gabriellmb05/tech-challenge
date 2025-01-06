package br.com.on.fiap.configuracao;

import br.com.on.fiap.hexagono.casosdeuso.BuscaClienteCasoDeUso;
import br.com.on.fiap.hexagono.casosdeuso.InsereClienteCasoDeUso;
import br.com.on.fiap.hexagono.portas.saida.PersisteClientePortaSaida;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteBeanConfiguracao {

  @Bean
  public BuscaClienteCasoDeUso buscaCliente(PersisteClientePortaSaida persisteClientePortaSaida) {
    return new BuscaClienteCasoDeUso(persisteClientePortaSaida);
  }

  @Bean
  public InsereClienteCasoDeUso insereCliente(PersisteClientePortaSaida persisteClientePortaSaida) {
    return new InsereClienteCasoDeUso(persisteClientePortaSaida);
  }
}
