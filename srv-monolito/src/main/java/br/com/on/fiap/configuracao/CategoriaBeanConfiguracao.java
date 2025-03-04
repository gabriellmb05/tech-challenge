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

@Configuration
public class CategoriaBeanConfiguracao {

    @Bean
    public BuscaCategoriaCasoDeUso buscaCategorias(CategoriaGateway categoriaGateway) {
        return new BuscaCategoriasCasoDeUsoImpl(categoriaGateway);
    }

    @Bean
    public CategoriaGateway categoriaGateway() {
        return new CategoriaGatewayImpl();
    }

    @Bean
    public CategoriaApresentador categoriaApresentador() {
        return new CategoriaApresentadorImpl();
    }

    @Bean
    public CategoriaControlador categoriaControlador(
            BuscaCategoriaCasoDeUso buscaCategoriaCasoDeUso, CategoriaApresentador categoriaApresentador) {
        return new CategoriaControladorImpl(buscaCategoriaCasoDeUso, categoriaApresentador);
    }
}
