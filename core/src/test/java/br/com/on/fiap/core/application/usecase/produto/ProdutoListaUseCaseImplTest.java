package br.com.on.fiap.core.application.usecase.produto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import br.com.on.fiap.core.application.dto.filtro.ProdutoFiltroEntrada;
import br.com.on.fiap.core.application.dto.resposta.Direcao;
import br.com.on.fiap.core.application.dto.resposta.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.PaginacaoResposta;
import br.com.on.fiap.core.application.exception.CategoriaNaoEncontradaExcecao;
import br.com.on.fiap.core.application.gateway.ProdutoGateway;
import br.com.on.fiap.core.application.usecase.produto.impl.ProdutoListaUseCaseImpl;
import br.com.on.fiap.core.domain.*;
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
        ProdutoFiltroEntrada filtro = ProdutoFiltroEntradaDataPool.criarProdutoFiltro(null, "LANCHE");
        ProdutoFiltro produtoFiltro = ProdutoFiltroDataPool.criarProdutoFiltro(null, "LANCHE");
        PaginacaoResposta paginacaoResposta = PaginacaoResposta.create(0, 10, null);
        List<Produto> produtos = ProdutoDataPool.criarProdutosComIdsDinamicos(1);
        PaginaResposta<Produto> produtoPaginaResposta =
                PaginaRespostaDataPool.criarPaginaComPaginacao(produtos, 1L, 0, 10, 0);
        when(produtoGateway.listarComFiltros(produtoFiltro, paginacaoResposta)).thenReturn(produtoPaginaResposta);

        PaginaResposta<Produto> result = produtoListaUseCase.listarComFiltro(filtro, paginacaoResposta);

        assertEquals(1, result.getTotalElementos());
        assertEquals(Categoria.LANCHE, result.getConteudo().getFirst().getCategoria());
        verify(produtoGateway).listarComFiltros(produtoFiltro, paginacaoResposta);
    }

    @Test
    @DisplayName(
            "Dado filtro por categoria e nome, quando listar produtos, então os produtos da categoria e nome devem ser retornados")
    void dadoProdutosExistentes_quandoFiltrarProdutos_entaoDevemSerRetornados() {
        ProdutoFiltroEntrada filtro = ProdutoFiltroEntradaDataPool.criarProdutoFiltro("x-burguer", "LANCHE");
        ProdutoFiltro produtoFiltro = ProdutoFiltroDataPool.criarProdutoFiltro("x-burguer", "LANCHE");
        PaginacaoResposta paginacaoResposta = PaginacaoResposta.create(0, 10, null);
        List<Produto> produtos = ProdutoDataPool.criarProdutosComIdsDinamicos(10);
        PaginaResposta<Produto> produtoPaginaResposta =
                PaginaRespostaDataPool.criarPaginaComPaginacao(produtos, 10L, 0, 10, 0);
        when(produtoGateway.listarComFiltros(produtoFiltro, paginacaoResposta)).thenReturn(produtoPaginaResposta);

        PaginaResposta<Produto> result = produtoListaUseCase.listarComFiltro(filtro, paginacaoResposta);
        assertEquals(10, result.getTotalElementos());
        verify(produtoGateway).listarComFiltros(produtoFiltro, paginacaoResposta);
    }

    @Test
    @DisplayName("Dado filtro vazio, quando listar produtos, então todos os produtos devem ser retornados")
    void dadoFiltroVazio_quandoListarProdutos_entaoTodosProdutosDevemSerRetornados() {
        ProdutoFiltroEntrada filtro = ProdutoFiltroEntradaDataPool.filtroVazio();
        ProdutoFiltro produtoFiltro = ProdutoFiltroDataPool.criarFiltroVazio();
        PaginacaoResposta paginacaoResposta = PaginacaoResposta.create(0, 10, null);
        List<Produto> produtos = ProdutoDataPool.criarProdutosComIdsDinamicos(2);
        PaginaResposta<Produto> produtoPaginaResposta =
                PaginaRespostaDataPool.criarPaginaComPaginacao(produtos, 2L, 0, 10, 0);
        when(produtoGateway.listarComFiltros(produtoFiltro, paginacaoResposta)).thenReturn(produtoPaginaResposta);

        PaginaResposta<Produto> result = produtoListaUseCase.listarComFiltro(filtro, paginacaoResposta);

        assertEquals(2, result.getTotalElementos());
        verify(produtoGateway).listarComFiltros(produtoFiltro, paginacaoResposta);
    }

    @Test
    @DisplayName("Dado filtro de produto inexistente, quando listar produtos, então nenhuma produto deve ser retornado")
    void dadoFiltroDeProdutoInexistente_quandoListarProdutos_entaoNenhumProdutoDeveSerRetornado() {
        ProdutoFiltroEntrada filtro = ProdutoFiltroEntradaDataPool.criarProdutoFiltro("inexistente", "LANCHE");
        ProdutoFiltro produtoFiltro = ProdutoFiltroDataPool.criarProdutoFiltro("inexistente", "LANCHE");
        PaginacaoResposta paginacaoResposta =
                PaginacaoResposta.create(0, 10, OrdenacaoDataPool.criarOrdenacaoPorCampoEDirecao("nome", Direcao.ASC));
        List<Produto> produtos = Collections.emptyList();
        PaginaResposta<Produto> produtoPaginaResposta =
                PaginaRespostaDataPool.criarPaginaComPaginacao(produtos, 0L, 0, 10, 0);
        when(produtoGateway.listarComFiltros(produtoFiltro, paginacaoResposta)).thenReturn(produtoPaginaResposta);

        PaginaResposta<Produto> result = produtoListaUseCase.listarComFiltro(filtro, paginacaoResposta);

        assertEquals(0, result.getTotalElementos());
        verify(produtoGateway).listarComFiltros(produtoFiltro, paginacaoResposta);
    }

    @Test
    @DisplayName(
            "Dado filtro e ordenação, quando listar produtos, então os produtos devem ser retornados na ordem correta")
    void dadoFiltroComOrdenacao_quandoListarProdutos_entaoProdutosDevemSerOrdenados() {
        ProdutoFiltroEntrada filtro = ProdutoFiltroEntradaDataPool.criarProdutoFiltro(null, "LANCHE");
        ProdutoFiltro produtoFiltro = ProdutoFiltroDataPool.criarProdutoFiltro(null, "LANCHE");
        PaginacaoResposta paginacaoResposta = PaginacaoRespostaDataPool.criarPaginacaoComOrdenacao(
                0, 10, OrdenacaoDataPool.criarOrdenacaoPorCampoEDirecao("nome", Direcao.ASC));
        List<Produto> produtos = ProdutoDataPool.criarProdutosComIdsDinamicos(2);
        PaginaResposta<Produto> produtoPaginaResposta =
                PaginaRespostaDataPool.criarPaginaComPaginacao(produtos, 2L, 0, 10, 0);
        when(produtoGateway.listarComFiltros(produtoFiltro, paginacaoResposta)).thenReturn(produtoPaginaResposta);

        PaginaResposta<Produto> result = produtoListaUseCase.listarComFiltro(filtro, paginacaoResposta);

        assertEquals(2, result.getTotalElementos());
        verify(produtoGateway).listarComFiltros(produtoFiltro, paginacaoResposta);
    }

    @Test
    @DisplayName(
            "Dado uma  categoria inválida no filtro, quando listar os produtos, então uma exceção deve ser lançada")
    void dadoUmaCategoriaInvalida_quandoListarProdutos_entaoUmaExcecaoDeveSerLancada() {
        ProdutoFiltroEntrada filtro = ProdutoFiltroEntradaDataPool.criarProdutoFiltro(null, "INVALIDA");
        PaginacaoResposta paginacaoResposta = PaginacaoRespostaDataPool.criarPaginacaoComOrdenacao(
                0, 10, OrdenacaoDataPool.criarOrdenacaoPorCampoEDirecao("nome", Direcao.ASC));

        CategoriaNaoEncontradaExcecao exception = assertThrows(
                CategoriaNaoEncontradaExcecao.class,
                () -> produtoListaUseCase.listarComFiltro(filtro, paginacaoResposta));

        assertEquals("Categoria (INVALIDA) não encontrada", exception.getMessage());
        verify(produtoGateway, never()).listarComFiltros(any(), eq(paginacaoResposta));
    }
}
