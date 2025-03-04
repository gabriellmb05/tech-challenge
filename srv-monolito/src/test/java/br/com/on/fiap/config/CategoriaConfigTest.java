package br.com.on.fiap.config;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.on.fiap.hexagono.usecase.categoria.entrada.BuscaCategoriaCasoDeUso;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class CategoriaConfigTest {

    @Test
    @DisplayName("Dado a configuração de beans, quando bucar categorias, então deve retornar a instância correta")
    void dadoConfiguracaoDeBeans_quandoListarCategorias_entaoDeveRetornarInstanciaCorreta() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CategoriaConfig.class);
        BuscaCategoriaCasoDeUso bean = context.getBean(BuscaCategoriaCasoDeUso.class);

        assertNotNull(bean);
        assertInstanceOf(BuscaCategoriaCasoDeUso.class, bean);
    }
}
