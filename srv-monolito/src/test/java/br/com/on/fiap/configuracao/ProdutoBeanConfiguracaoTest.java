package br.com.on.fiap.configuracao;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import br.com.on.fiap.adaptadores.produto.saida.persistencia.PersistenciaProdutoAdaptador;
import br.com.on.fiap.adaptadores.produto.saida.persistencia.mapeador.ProdutoSaidaMapeador;
import br.com.on.fiap.adaptadores.produto.saida.persistencia.repositorio.ProdutoRepositorio;
import br.com.on.fiap.hexagono.casodeuso.produto.AlteraProdutoCasoDeUsoImpl;
import br.com.on.fiap.hexagono.casodeuso.produto.BuscaProdutoCasoDeUsoImpl;
import br.com.on.fiap.hexagono.casodeuso.produto.DeletaProdutoCasoDeUsoImpl;
import br.com.on.fiap.hexagono.casodeuso.produto.InsereProdutoCasoDeUsoImpl;
import br.com.on.fiap.hexagono.casodeuso.produto.entrada.*;
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
        BuscaProdutoPorIdCasoDeUso bean = context.getBean(BuscaProdutoPorIdCasoDeUso.class);

        assertNotNull(bean);
        assertInstanceOf(BuscaProdutoCasoDeUsoImpl.class, bean);
    }

    @Test
    @DisplayName("Dado a configuração de beans, quando inserir Produto, então deve retornar a instância correta")
    void dadoConfiguracaoDeBeans_quandoInserirProduto_entaoDeveRetornarInstanciaCorreta() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        InsereProdutoCasoDeUso bean = context.getBean(InsereProdutoCasoDeUso.class);

        assertNotNull(bean);
        assertInstanceOf(InsereProdutoCasoDeUsoImpl.class, bean);
    }

    @Test
    @DisplayName("Dado a configuração de beans, quando alterar Produto, então deve retornar a instância correta")
    void dadoConfiguracaoDeBeans_quandoAlterarProduto_entaoDeveRetornarInstanciaCorreta() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        AlteraProdutoCasoDeUso bean = context.getBean(AlteraProdutoCasoDeUso.class);

        assertNotNull(bean);
        assertInstanceOf(AlteraProdutoCasoDeUsoImpl.class, bean);
    }

    @Test
    @DisplayName("Dado a configuração de beans, quando deletar Produto, então deve retornar a instância correta")
    void dadoConfiguracaoDeBeans_quandoDeletarProduto_entaoDeveRetornarInstanciaCorreta() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        DeletaProdutoCasoDeUso bean = context.getBean(DeletaProdutoCasoDeUso.class);

        assertNotNull(bean);
        assertInstanceOf(DeletaProdutoCasoDeUsoImpl.class, bean);
    }

    @Test
    @DisplayName("Dado a configuração de beans, quando listar Produtos, então deve retornar a instância correta")
    void dadoConfiguracaoDeBeans_quandoListarProdutos_entaoDeveRetornarInstanciaCorreta() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        BuscaProdutosCasoDeUso bean = context.getBean(BuscaProdutosCasoDeUso.class);

        assertNotNull(bean);
        assertInstanceOf(BuscaProdutosCasoDeUso.class, bean);
    }

    @Configuration
    static class TestConfig extends ProdutoBeanConfiguracao {

        @Bean
        public PersistenciaProdutoAdaptador persistenciaProdutoAdaptador() {
            return new PersistenciaProdutoAdaptador(mock(ProdutoRepositorio.class), mock(ProdutoSaidaMapeador.class));
        }
    }
}
