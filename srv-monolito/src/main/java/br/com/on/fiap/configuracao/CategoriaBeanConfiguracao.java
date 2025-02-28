package br.com.on.fiap.configuracao;

import br.com.on.fiap.hexagono.interfaceadapters.apresentadores.CategoriaApresentadorImpl;
import br.com.on.fiap.hexagono.interfaceadapters.controladores.CategoriaControladorImpl;
import br.com.on.fiap.hexagono.interfaceadapters.gateways.CategoriaGatewayImpl;
import br.com.on.fiap.hexagono.interfaceadapters.interfaces.controller.CategoriaControlador;
import br.com.on.fiap.hexagono.interfaceadapters.interfaces.presenter.CategoriaApresentador;
import br.com.on.fiap.hexagono.usecases.casodeuso.categoria.BuscaCategoriasCasoDeUsoImpl;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.categoria.BuscaCategoriaCasoDeUso;
import br.com.on.fiap.hexagono.usecases.interfaces.gateway.categoria.CategoriaGateway;
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
            BuscaCategoriaCasoDeUso buscaCategoriaCasoDeUso,
            CategoriaGateway categoriaGateway,
            CategoriaApresentador categoriaApresentador) {
        return new CategoriaControladorImpl(buscaCategoriaCasoDeUso, categoriaApresentador);
    }
}
