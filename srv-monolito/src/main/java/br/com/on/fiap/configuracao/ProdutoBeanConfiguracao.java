package br.com.on.fiap.configuracao;

import br.com.on.fiap.hexagono.casodeuso.produto.*;
import br.com.on.fiap.hexagono.casodeuso.produto.entrada.*;
import br.com.on.fiap.hexagono.casodeuso.produto.saida.PersisteProdutoPortaSaida;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoBeanConfiguracao {

    private final PersisteProdutoPortaSaida persisteProdutoPortaSaida;

    public ProdutoBeanConfiguracao(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
        this.persisteProdutoPortaSaida = persisteProdutoPortaSaida;
    }

    @Bean
    public BuscaProdutoPorIdCasoDeUso buscaProduto() {
        return new BuscaProdutoCasoDeUsoImpl(persisteProdutoPortaSaida);
    }

    @Bean
    public InsereProdutoCasoDeUso insereProduto() {
        return new InsereProdutoCasoDeUsoImpl(persisteProdutoPortaSaida);
    }

    @Bean
    public AlteraProdutoCasoDeUso alteraProduto() {
        return new AlteraProdutoCasoDeUsoImpl(persisteProdutoPortaSaida);
    }

    @Bean
    public DeletaProdutoCasoDeUso deletaProduto() {
        return new DeletaProdutoCasoDeUsoImpl(persisteProdutoPortaSaida);
    }

    @Bean
    public BuscaProdutosCasoDeUso listarProduto() {
        return new BuscaProdutosCasoDeUsoImpl(persisteProdutoPortaSaida);
    }

    @Bean
    public ValidaProdutosDoPedidoCasoDeUso validaProdutosCasoDeUso() {
        return new ValidaProdutosDoPedidoCasoDeUsoImpl(persisteProdutoPortaSaida);
    }
}
