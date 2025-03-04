package br.com.on.fiap.config;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.on.fiap.hexagono.application.usecase.produto.impl.ProdutoAlteraUseCaseImpl;
import br.com.on.fiap.hexagono.application.usecase.produto.impl.ProdutoBuscaPorIdUseCaseImpl;
import br.com.on.fiap.hexagono.application.usecase.produto.impl.ProdutoDeletaUseCaseImpl;
import br.com.on.fiap.hexagono.application.usecase.produto.impl.ProdutoInsereUseCaseImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ProdutoConfigTest {

    @Test
    @DisplayName("Dado a configuração de beans, quando buscar ProdutoPorId, então deve retornar a instância correta")
    void dadoConfiguracaoDeBeans_quandoBuscarProdutoPorId_entaoDeveRetornarInstanciaCorreta() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProdutoConfig.class);
        ProdutoBuscaPorIdUseCase bean = context.getBean(ProdutoBuscaPorIdUseCase.class);

        assertNotNull(bean);
        assertInstanceOf(ProdutoBuscaPorIdUseCaseImpl.class, bean);
    }

    @Test
    @DisplayName("Dado a configuração de beans, quando inserir Produto, então deve retornar a instância correta")
    void dadoConfiguracaoDeBeans_quandoInserirProduto_entaoDeveRetornarInstanciaCorreta() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProdutoConfig.class);
        ProdutoInsereUseCase bean = context.getBean(ProdutoInsereUseCase.class);

        assertNotNull(bean);
        assertInstanceOf(ProdutoInsereUseCaseImpl.class, bean);
    }

    @Test
    @DisplayName("Dado a configuração de beans, quando alterar Produto, então deve retornar a instância correta")
    void dadoConfiguracaoDeBeans_quandoAlterarProduto_entaoDeveRetornarInstanciaCorreta() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProdutoConfig.class);
        ProdutoAlteraUseCase bean = context.getBean(ProdutoAlteraUseCase.class);

        assertNotNull(bean);
        assertInstanceOf(ProdutoAlteraUseCaseImpl.class, bean);
    }

    @Test
    @DisplayName("Dado a configuração de beans, quando deletar Produto, então deve retornar a instância correta")
    void dadoConfiguracaoDeBeans_quandoDeletarProduto_entaoDeveRetornarInstanciaCorreta() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProdutoConfig.class);
        ProdutoDeletaUseCase bean = context.getBean(ProdutoDeletaUseCase.class);

        assertNotNull(bean);
        assertInstanceOf(ProdutoDeletaUseCaseImpl.class, bean);
    }

    @Test
    @DisplayName("Dado a configuração de beans, quando listar Produtos, então deve retornar a instância correta")
    void dadoConfiguracaoDeBeans_quandoListarProdutos_entaoDeveRetornarInstanciaCorreta() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProdutoConfig.class);
        ProdutoListaUseCase bean = context.getBean(ProdutoListaUseCase.class);

        assertNotNull(bean);
        assertInstanceOf(ProdutoListaUseCase.class, bean);
    }
}
