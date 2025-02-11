package br.com.on.fiap.configuracao;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.on.fiap.hexagono.portas.entrada.categoria.BuscaCategoriaPortaEntrada;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class CategoriaBeanConfiguracaoTest {

    @Test
    @DisplayName("Dado a configuração de beans, quando bucar categorias, então deve retornar a instância correta")
    void dadoConfiguracaoDeBeans_quandoListarCategorias_entaoDeveRetornarInstanciaCorreta() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(CategoriaBeanConfiguracao.class);
        BuscaCategoriaPortaEntrada bean = context.getBean(BuscaCategoriaPortaEntrada.class);

        assertNotNull(bean);
        assertInstanceOf(BuscaCategoriaPortaEntrada.class, bean);
    }
}
