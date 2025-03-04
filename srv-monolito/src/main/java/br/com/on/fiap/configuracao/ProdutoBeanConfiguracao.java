package br.com.on.fiap.configuracao;

import br.com.on.fiap.hexagono.casodeuso.produto.*;
import br.com.on.fiap.hexagono.casodeuso.produto.entrada.*;
import br.com.on.fiap.hexagono.casodeuso.produto.saida.PersisteProdutoPortaSaida;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoBeanConfiguracao {

    @Bean
    public BuscaProdutoPorIdCasoDeUso buscaProduto(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
        return new BuscaProdutoCasoDeUsoImpl(persisteProdutoPortaSaida);
    }

    @Bean
    public InsereProdutoCasoDeUso insereProduto(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
        return new InsereProdutoCasoDeUsoImpl(persisteProdutoPortaSaida);
    }

    @Bean
    public AlteraProdutoCasoDeUso alteraProduto(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
        return new AlteraProdutoCasoDeUsoImpl(persisteProdutoPortaSaida);
    }

    @Bean
    public DeletaProdutoCasoDeUso deletaProduto(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
        return new DeletaProdutoCasoDeUsoImpl(persisteProdutoPortaSaida);
    }

    @Bean
    public BuscaProdutosCasoDeUso listarProduto(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
        return new BuscaProdutosCasoDeUsoImpl(persisteProdutoPortaSaida);
    }

    @Bean
    public ValidaProdutosDoPedidoCasoDeUso validaProdutosCasoDeUso(
            PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
        return new ValidaProdutosDoPedidoCasoDeUsoImpl(persisteProdutoPortaSaida);
    }
}
