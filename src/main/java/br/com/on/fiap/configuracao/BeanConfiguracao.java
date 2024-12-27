package br.com.on.fiap.configuracao;

import br.com.on.fiap.adaptadores.saida.persistencia.PersistenciaProdutoAdapter;
import br.com.on.fiap.hexagono.casosdeuso.*;
import br.com.on.fiap.hexagono.portas.entrada.*;
import br.com.on.fiap.hexagono.portas.saida.PersisteProdutoPortaSaida;
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

    @Bean
    public AlteraProdutoPortaEntrada alteraProduto(PersistenciaProdutoAdapter persistenciaProdutoAdapter ){
        return new AlteraProdutoCasoDeUso(persistenciaProdutoAdapter);
    }

    @Bean
    public DeletaProdutoPortaEntrada deletaProduto(PersistenciaProdutoAdapter persistenciaProdutoAdapter ){
        return new DeletaProdutoCasoDeUso(persistenciaProdutoAdapter);
    }

    @Bean
    public ListarProdutoPortaEntrada listarProduto(PersistenciaProdutoAdapter persistenciaProdutoAdapter){
        return new ListarProdutoCasoDeUso(persistenciaProdutoAdapter);
    }
}