package br.com.on.fiap.config;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import br.com.on.fiap.adapter.output.db.component.PageableComponent;
import br.com.on.fiap.adapter.output.db.datasource.ProdutoDataSourceImpl;
import br.com.on.fiap.adapter.output.db.repository.ProdutoRepository;
import br.com.on.fiap.core.adapter.datasource.ProdutoDataSource;
import br.com.on.fiap.core.application.usecase.produto.*;
import br.com.on.fiap.core.application.usecase.produto.impl.ProdutoAlteraUseCaseImpl;
import br.com.on.fiap.core.application.usecase.produto.impl.ProdutoBuscaPorIdUseCaseImpl;
import br.com.on.fiap.core.application.usecase.produto.impl.ProdutoDeletaUseCaseImpl;
import br.com.on.fiap.core.application.usecase.produto.impl.ProdutoInsereUseCaseImpl;
import br.com.on.fiap.infrastructure.config.ProdutoUseCaseBeanConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ProdutoUseCaseBeanConfigTest {

    private AnnotationConfigApplicationContext context;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext();
        context.registerBean(
                ProdutoDataSource.class,
                () -> new ProdutoDataSourceImpl(mock(ProdutoRepository.class), mock(PageableComponent.class)));
        context.register(ProdutoUseCaseBeanConfig.class);
        context.refresh();
    }

    @Test
    @DisplayName("Dado a configuração de beans, quando buscar ProdutoPorId, então deve retornar a instância correta")
    void dadoConfiguracaoDeBeans_quandoBuscarProdutoPorId_entaoDeveRetornarInstanciaCorreta() {
        ProdutoBuscaPorIdUseCase bean = context.getBean(ProdutoBuscaPorIdUseCase.class);

        assertNotNull(bean);
        assertInstanceOf(ProdutoBuscaPorIdUseCaseImpl.class, bean);
    }

    @Test
    @DisplayName("Dado a configuração de beans, quando inserir Produto, então deve retornar a instância correta")
    void dadoConfiguracaoDeBeans_quandoInserirProduto_entaoDeveRetornarInstanciaCorreta() {
        ProdutoInsereUseCase bean = context.getBean(ProdutoInsereUseCase.class);

        assertNotNull(bean);
        assertInstanceOf(ProdutoInsereUseCaseImpl.class, bean);
    }

    @Test
    @DisplayName("Dado a configuração de beans, quando alterar Produto, então deve retornar a instância correta")
    void dadoConfiguracaoDeBeans_quandoAlterarProduto_entaoDeveRetornarInstanciaCorreta() {
        ProdutoAlteraUseCase bean = context.getBean(ProdutoAlteraUseCase.class);

        assertNotNull(bean);
        assertInstanceOf(ProdutoAlteraUseCaseImpl.class, bean);
    }

    @Test
    @DisplayName("Dado a configuração de beans, quando deletar Produto, então deve retornar a instância correta")
    void dadoConfiguracaoDeBeans_quandoDeletarProduto_entaoDeveRetornarInstanciaCorreta() {
        ProdutoDeletaUseCase bean = context.getBean(ProdutoDeletaUseCase.class);

        assertNotNull(bean);
        assertInstanceOf(ProdutoDeletaUseCaseImpl.class, bean);
    }

    @Test
    @DisplayName("Dado a configuração de beans, quando listar Produtos, então deve retornar a instância correta")
    void dadoConfiguracaoDeBeans_quandoListarProdutos_entaoDeveRetornarInstanciaCorreta() {
        ProdutoListaUseCase bean = context.getBean(ProdutoListaUseCase.class);

        assertNotNull(bean);
        assertInstanceOf(ProdutoListaUseCase.class, bean);
    }
}
