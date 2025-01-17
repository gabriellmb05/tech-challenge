package br.com.on.fiap.configuracao;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import br.com.on.fiap.adaptadores.saida.persistencia.mapeador.ProdutoSaidaMapeador;
import br.com.on.fiap.adaptadores.saida.persistencia.repositorio.ProdutoRepositorio;
import br.com.on.fiap.adaptadores.saida.servico.PersistenciaProdutoAdaptador;
import br.com.on.fiap.hexagono.casosdeuso.produto.AlteraProdutoCasoDeUso;
import br.com.on.fiap.hexagono.casosdeuso.produto.BuscaProdutoCasoDeUso;
import br.com.on.fiap.hexagono.casosdeuso.produto.DeletaProdutoCasoDeUso;
import br.com.on.fiap.hexagono.casosdeuso.produto.InsereProdutoCasoDeUso;
import br.com.on.fiap.hexagono.portas.entrada.produto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ExtendWith(MockitoExtension.class)
class ProdutoBeanConfiguracaoTest {

    @Mock
    private ProdutoRepositorio produtoRepositorio;

    @Mock
    private ProdutoSaidaMapeador produtoSaidaMapeador;

    @Test
    @DisplayName("Dado a configuração de beans, quando buscar ProdutoPorId, então deve retornar a instância correta")
    void dadoConfiguracaoDeBeans_quandoBuscarProdutoPorId_entaoDeveRetornarInstanciaCorreta() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        BuscaProdutoPorIdPortaEntrada bean = context.getBean(BuscaProdutoPorIdPortaEntrada.class);

        assertNotNull(bean);
        assertInstanceOf(BuscaProdutoCasoDeUso.class, bean);
    }

    @Test
    @DisplayName("Dado a configuração de beans, quando inserir Produto, então deve retornar a instância correta")
    void dadoConfiguracaoDeBeans_quandoInserirProduto_entaoDeveRetornarInstanciaCorreta() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        InsereProdutoPortaEntrada bean = context.getBean(InsereProdutoPortaEntrada.class);

        assertNotNull(bean);
        assertInstanceOf(InsereProdutoCasoDeUso.class, bean);
    }

    @Test
    @DisplayName("Dado a configuração de beans, quando alterar Produto, então deve retornar a instância correta")
    void dadoConfiguracaoDeBeans_quandoAlterarProduto_entaoDeveRetornarInstanciaCorreta() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        AlteraProdutoPortaEntrada bean = context.getBean(AlteraProdutoPortaEntrada.class);

        assertNotNull(bean);
        assertInstanceOf(AlteraProdutoCasoDeUso.class, bean);
    }

    @Test
    @DisplayName("Dado a configuração de beans, quando deletar Produto, então deve retornar a instância correta")
    void dadoConfiguracaoDeBeans_quandoDeletarProduto_entaoDeveRetornarInstanciaCorreta() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        DeletaProdutoPortaEntrada bean = context.getBean(DeletaProdutoPortaEntrada.class);

        assertNotNull(bean);
        assertInstanceOf(DeletaProdutoCasoDeUso.class, bean);
    }

    @Test
    @DisplayName("Dado a configuração de beans, quando listar Produtos, então deve retornar a instância correta")
    void dadoConfiguracaoDeBeans_quandoListarProdutos_entaoDeveRetornarInstanciaCorreta() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        ListarProdutoPortaEntrada bean = context.getBean(ListarProdutoPortaEntrada.class);

        assertNotNull(bean);
        assertInstanceOf(ListarProdutoPortaEntrada.class, bean);
    }

    @Test
    @DisplayName("Dado a configuração de beans, quando bucar categorias, então deve retornar a instância correta")
    void dadoConfiguracaoDeBeans_quandoListarCategorias_entaoDeveRetornarInstanciaCorreta() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        BuscaCategoriaPortaEntrada bean = context.getBean(BuscaCategoriaPortaEntrada.class);

        assertNotNull(bean);
        assertInstanceOf(BuscaCategoriaPortaEntrada.class, bean);
    }

    @Configuration
    static class TestConfig extends ProdutoBeanConfiguracao {

        @Bean
        public PersistenciaProdutoAdaptador persistenciaProdutoAdaptador() {
            return new PersistenciaProdutoAdaptador(mock(ProdutoRepositorio.class), mock(ProdutoSaidaMapeador.class));
        }
    }
}
