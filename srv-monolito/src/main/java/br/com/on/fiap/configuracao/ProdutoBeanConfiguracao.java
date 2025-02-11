package br.com.on.fiap.configuracao;

import br.com.on.fiap.hexagono.casosdeuso.produto.*;
import br.com.on.fiap.hexagono.portas.entrada.produto.*;
import br.com.on.fiap.hexagono.portas.saida.produto.PersisteProdutoPortaSaida;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoBeanConfiguracao {

    @Bean
    public BuscaProdutoPorIdPortaEntrada buscaProduto(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
        return new BuscaProdutoCasoDeUso(persisteProdutoPortaSaida);
    }

    @Bean
    public InsereProdutoPortaEntrada insereProduto(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
        return new InsereProdutoCasoDeUso(persisteProdutoPortaSaida);
    }

    @Bean
    public AlteraProdutoPortaEntrada alteraProduto(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
        return new AlteraProdutoCasoDeUso(persisteProdutoPortaSaida);
    }

    @Bean
    public DeletaProdutoPortaEntrada deletaProduto(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
        return new DeletaProdutoCasoDeUso(persisteProdutoPortaSaida);
    }

    @Bean
    public BuscaProdutosPortaEntrada listarProduto(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
        return new BuscaProdutosCasoDeUso(persisteProdutoPortaSaida);
    }

    @Bean
    public ValidaProdutosDoPedidoPortaEntrada validaProdutosCasoDeUso(
            PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
        return new ValidaProdutosDoPedidoCasoDeUso(persisteProdutoPortaSaida);
    }
}
