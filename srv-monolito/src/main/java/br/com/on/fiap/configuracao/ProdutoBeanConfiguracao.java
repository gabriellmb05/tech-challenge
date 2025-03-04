package br.com.on.fiap.configuracao;

import br.com.on.fiap.hexagono.adaptadores.gateways.ProdutoGateway;
import br.com.on.fiap.hexagono.casodeuso.produto.*;
import br.com.on.fiap.hexagono.casodeuso.produto.entrada.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoBeanConfiguracao {

    private final ProdutoGateway produtoGateway;

    public ProdutoBeanConfiguracao(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Bean
    public BuscaProdutoPorIdCasoDeUso buscaProduto() {
        return new BuscaProdutoCasoDeUsoImpl(produtoGateway);
    }

    @Bean
    public InsereProdutoCasoDeUso insereProduto() {
        return new InsereProdutoCasoDeUsoImpl(produtoGateway);
    }

    @Bean
    public AlteraProdutoCasoDeUso alteraProduto() {
        return new AlteraProdutoCasoDeUsoImpl(produtoGateway);
    }

    @Bean
    public DeletaProdutoCasoDeUso deletaProduto() {
        return new DeletaProdutoCasoDeUsoImpl(produtoGateway);
    }

    @Bean
    public BuscaProdutosCasoDeUso listarProduto() {
        return new BuscaProdutosCasoDeUsoImpl(produtoGateway);
    }

    @Bean
    public ValidaProdutosDoPedidoCasoDeUso validaProdutosCasoDeUso() {
        return new ValidaProdutosDoPedidoCasoDeUsoImpl(produtoGateway);
    }
}
