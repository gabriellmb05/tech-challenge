package br.com.on.fiap.config;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.on.fiap.core.adapter.gateway.CategoriaGatewayImpl;
import br.com.on.fiap.core.application.gateway.CategoriaGateway;
import br.com.on.fiap.core.application.usecase.categoria.CategoriaBuscaUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class CategoriaUseCaseBeanConfigTest {

    private AnnotationConfigApplicationContext context;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext();
        context.registerBean(CategoriaGateway.class, CategoriaGatewayImpl::new);
        context.register(CategoriaUseCaseBeanConfig.class);
        context.refresh();
    }

    @Test
    @DisplayName("Dado a configuração de beans, quando bucar categorias, então deve retornar a instância correta")
    void dadoConfiguracaoDeBeans_quandoListarCategorias_entaoDeveRetornarInstanciaCorreta() {
        CategoriaBuscaUseCase bean = context.getBean(CategoriaBuscaUseCase.class);

        assertNotNull(bean);
        assertInstanceOf(CategoriaBuscaUseCase.class, bean);
    }
}
