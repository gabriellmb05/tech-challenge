package br.com.on.fiap.configuracao;

import br.com.on.fiap.hexagono.adaptadores.apresentadores.CategoriaApresentador;
import br.com.on.fiap.hexagono.adaptadores.apresentadores.impl.CategoriaApresentadorImpl;
import br.com.on.fiap.hexagono.adaptadores.controladores.CategoriaControlador;
import br.com.on.fiap.hexagono.adaptadores.controladores.impl.CategoriaControladorImpl;
import br.com.on.fiap.hexagono.adaptadores.gateways.CategoriaGateway;
import br.com.on.fiap.hexagono.adaptadores.gateways.impl.CategoriaGatewayImpl;
import br.com.on.fiap.hexagono.casodeuso.categoria.BuscaCategoriasCasoDeUsoImpl;
import br.com.on.fiap.hexagono.casodeuso.categoria.entrada.BuscaCategoriaCasoDeUso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class CategoriaBeanConfiguracao {

    private final CategoriaGateway categoriaGateway;
    private final CategoriaApresentador categoriaApresentador;

    @Lazy
    public CategoriaBeanConfiguracao(CategoriaGateway categoriaGateway, CategoriaApresentador categoriaApresentador) {
        this.categoriaGateway = categoriaGateway;
        this.categoriaApresentador = categoriaApresentador;
    }

    @Bean
    public BuscaCategoriaCasoDeUso buscaCategorias() {
        return new BuscaCategoriasCasoDeUsoImpl(categoriaGateway);
    }

    @Bean
    public CategoriaControlador categoriaControlador() {
        return new CategoriaControladorImpl(buscaCategorias(), categoriaApresentador);
    }

    @Bean
    public CategoriaGateway categoriaGateway() {
        return new CategoriaGatewayImpl();
    }

    @Bean
    public CategoriaApresentador categoriaApresentador() {
        return new CategoriaApresentadorImpl();
    }
}
