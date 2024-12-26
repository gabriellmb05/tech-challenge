package br.com.on.fiap.configuracao;

import br.com.on.fiap.adaptadores.saida.persistencia.PersistenciaProdutoAdaptador;
import br.com.on.fiap.hexagono.casosdeuso.AlteraProdutoCasoDeUso;
import br.com.on.fiap.hexagono.casosdeuso.BuscaProdutoCasoDeUso;
import br.com.on.fiap.hexagono.casosdeuso.DeletaProdutoCasoDeUso;
import br.com.on.fiap.hexagono.casosdeuso.InsereProdutoCasoDeUso;
import br.com.on.fiap.hexagono.portas.entrada.AlteraProdutoPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.BuscaProdutoPorIdPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.DeletaProdutoPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.InsereProdutoPortaEntrada;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoBeanConfiguracao {

  @Bean
  public BuscaProdutoPorIdPortaEntrada buscaProduto(
      PersistenciaProdutoAdaptador persistenciaProdutoAdaptador) {
    return new BuscaProdutoCasoDeUso(persistenciaProdutoAdaptador);
  }

  @Bean
  public InsereProdutoPortaEntrada insereProduto(
      PersistenciaProdutoAdaptador persistenciaProdutoAdaptador) {
    return new InsereProdutoCasoDeUso(persistenciaProdutoAdaptador);
  }

  @Bean
  public AlteraProdutoPortaEntrada alteraProduto(
      PersistenciaProdutoAdaptador persistenciaProdutoAdaptador) {
    return new AlteraProdutoCasoDeUso(persistenciaProdutoAdaptador);
  }

  @Bean
  public DeletaProdutoPortaEntrada deletaProduto(
      PersistenciaProdutoAdaptador persistenciaProdutoAdaptador) {
    return new DeletaProdutoCasoDeUso(persistenciaProdutoAdaptador);
  }
}
