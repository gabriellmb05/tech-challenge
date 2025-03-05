package br.com.on.fiap.core.application.usecase.produto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.core.adapter.gateway.ProdutoGateway;
import br.com.on.fiap.core.application.usecase.produto.impl.ProdutoListaUseCaseImpl;
import br.com.on.fiap.core.domain.model.*;
import br.com.on.fiap.datapool.*;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProdutoListaUseCaseImplTest {

    @Mock
    private ProdutoGateway produtoGateway;

    @InjectMocks
    private ProdutoListaUseCaseImpl produtoListaUseCase;

    @Test
    @DisplayName("Dado filtro por categoria, quando listar produtos, então produtos da categoria devem ser retornados")
    void dadoFiltroPorCategoria_quandoListarProdutos_entaoProdutosDaCategoriaDevemSerRetornados() {
        ProdutoFiltro filtro = DataPoolProdutoFiltro.criarProdutoFiltro(null, Categoria.LANCHE);
        Paginacao paginacao = Paginacao.create(0, 10, null);
        List<Produto> produtos = DataPoolProduto.produtosComIdsDinamicos(1);
        Pagina<Produto> produtoPagina = DataPoolPagina.criarPaginacao(produtos, 1L, 0, 10, 0);
        when(produtoGateway.listarComFiltros(filtro, paginacao)).thenReturn(produtoPagina);

        Pagina<Produto> result = produtoListaUseCase.listarComFiltro(filtro, paginacao);

        assertEquals(1, result.getTotalElementos());
        assertEquals(Categoria.LANCHE, result.getConteudo().getFirst().getCategoria());
        verify(produtoGateway).listarComFiltros(filtro, paginacao);
    }

    @Test
    @DisplayName(
            "Dado filtro por categoria e nome, quando listar produtos, então os produtos da categoria e nome devem ser retornados")
    void dadoProdutosExistentes_quandoFiltrarProdutos_entaoDevemSerRetornados() {
        ProdutoFiltro filtro = DataPoolProdutoFiltro.criarProdutoFiltro("x-burguer", Categoria.LANCHE);
        Paginacao paginacao = Paginacao.create(0, 10, null);
        List<Produto> produtos = DataPoolProduto.produtosComIdsDinamicos(10);
        Pagina<Produto> produtoPagina = DataPoolPagina.criarPaginacao(produtos, 10L, 0, 10, 0);
        when(produtoGateway.listarComFiltros(filtro, paginacao)).thenReturn(produtoPagina);

        Pagina<Produto> result = produtoListaUseCase.listarComFiltro(filtro, paginacao);
        assertEquals(10, result.getTotalElementos());
        verify(produtoGateway).listarComFiltros(filtro, paginacao);
    }

    @Test
    @DisplayName("Dado filtro vazio, quando listar produtos, então todos os produtos devem ser retornados")
    void dadoFiltroVazio_quandoListarProdutos_entaoTodosProdutosDevemSerRetornados() {
        ProdutoFiltro filtro = DataPoolProdutoFiltro.filtroVazio();
        Paginacao paginacao = Paginacao.create(0, 10, null);
        List<Produto> produtos = DataPoolProduto.produtosComIdsDinamicos(2);
        Pagina<Produto> produtoPagina = DataPoolPagina.criarPaginacao(produtos, 2L, 0, 10, 0);
        when(produtoGateway.listarComFiltros(filtro, paginacao)).thenReturn(produtoPagina);

        Pagina<Produto> result = produtoListaUseCase.listarComFiltro(filtro, paginacao);

        assertEquals(2, result.getTotalElementos());
        verify(produtoGateway).listarComFiltros(filtro, paginacao);
    }

    @Test
    @DisplayName("Dado filtro de produto inexistente, quando listar produtos, então nenhuma produto deve ser retornado")
    void dadoFiltroDeProdutoInexistente_quandoListarProdutos_entaoNenhumProdutoDeveSerRetornado() {
        ProdutoFiltro filtro = DataPoolProdutoFiltro.criarProdutoFiltro("inexistente", Categoria.LANCHE);
        Paginacao paginacao = Paginacao.create(0, 10, DataPoolOrdenacao.criarOrdenacao("nome", Direcao.ASC));
        List<Produto> produtos = Collections.emptyList();
        Pagina<Produto> produtoPagina = DataPoolPagina.criarPaginacao(produtos, 0L, 0, 10, 0);
        when(produtoGateway.listarComFiltros(filtro, paginacao)).thenReturn(produtoPagina);

        Pagina<Produto> result = produtoListaUseCase.listarComFiltro(filtro, paginacao);

        assertEquals(0, result.getTotalElementos());
        verify(produtoGateway).listarComFiltros(filtro, paginacao);
    }

    @Test
    @DisplayName(
            "Dado filtro e ordenação, quando listar produtos, então os produtos devem ser retornados na ordem correta")
    void dadoFiltroComOrdenacao_quandoListarProdutos_entaoProdutosDevemSerOrdenados() {
        ProdutoFiltro filtro = DataPoolProdutoFiltro.criarProdutoFiltro(null, Categoria.LANCHE);
        Paginacao paginacao =
                DataPoolPaginacao.criarPaginacao(0, 10, DataPoolOrdenacao.criarOrdenacao("nome", Direcao.ASC));
        List<Produto> produtos = DataPoolProduto.produtosComIdsDinamicos(2);
        Pagina<Produto> produtoPagina = DataPoolPagina.criarPaginacao(produtos, 2L, 0, 10, 0);
        when(produtoGateway.listarComFiltros(filtro, paginacao)).thenReturn(produtoPagina);

        Pagina<Produto> result = produtoListaUseCase.listarComFiltro(filtro, paginacao);

        assertEquals(2, result.getTotalElementos());
        verify(produtoGateway).listarComFiltros(filtro, paginacao);
    }
}
