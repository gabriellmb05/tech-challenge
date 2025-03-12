package br.com.on.fiap.config;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.on.fiap.core.application.usecase.categoria.CategoriaBuscaUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class CategoriaUseCaseBeanConfigTest {

    @Test
    @DisplayName("Dado a configuração de beans, quando bucar categorias, então deve retornar a instância correta")
    void dadoConfiguracaoDeBeans_quandoListarCategorias_entaoDeveRetornarInstanciaCorreta() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(CategoriaUseCaseBeanConfig.class);
        CategoriaBuscaUseCase bean = context.getBean(CategoriaBuscaUseCase.class);

        assertNotNull(bean);
        assertInstanceOf(CategoriaBuscaUseCase.class, bean);
    }
}
