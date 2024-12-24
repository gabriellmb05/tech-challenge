package br.com.on.fiap.configuracao;

import br.com.on.fiap.adaptadores.saida.persistencia.PersistenciaProdutoAdapter;
import br.com.on.fiap.hexagono.casosdeuso.BuscaProdutoCasoDeUso;
import br.com.on.fiap.hexagono.casosdeuso.InsereProdutoCasoDeUso;
import br.com.on.fiap.hexagono.portas.entrada.BuscaProdutoPorIdPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.InsereProdutoPortaEntrada;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguracao {

    @Bean
    public BuscaProdutoPorIdPortaEntrada buscaProduto(PersistenciaProdutoAdapter persistenciaProdutoAdapter ){
        return new BuscaProdutoCasoDeUso(persistenciaProdutoAdapter);
    }

    @Bean
    public InsereProdutoPortaEntrada insereProduto(PersistenciaProdutoAdapter persistenciaProdutoAdapter ){
        return new InsereProdutoCasoDeUso(persistenciaProdutoAdapter);
    }
}
