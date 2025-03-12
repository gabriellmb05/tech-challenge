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

    private final PaginacaoResposta paginacaoResposta =
            PaginacaoResposta.create(0, 10, OrdenacaoDataPool.criarOrdenacaoPorCampoEDirecao("nome", Direcao.ASC));

    private ProdutoFiltroEntrada criarFiltro(String nome) {
        return ProdutoFiltroEntradaDataPool.criarProdutoFiltro(nome, "LANCHE");
    }

    private ProdutoFiltro criarProdutoFiltro(String nome) {
        return ProdutoFiltroDataPool.criarProdutoFiltro(nome, "LANCHE");
    }

    private PaginaResposta<Produto> criarPaginaResposta(Long quantidade) {
        return PaginaRespostaDataPool.criarPaginaComPaginacao(
                ProdutoDataPool.criarProdutosComIdsDinamicos(quantidade.intValue()), quantidade, 0, 10, 0);
    }

    @Test
    @DisplayName("Dado filtro por categoria, quando listar produtos, então produtos da categoria devem ser retornados")
    void dadoFiltroPorCategoria_quandoListarProdutos_entaoProdutosDaCategoriaDevemSerRetornados() {
        ProdutoFiltro produtoFiltro = criarProdutoFiltro(null);
        when(produtoGateway.listarComFiltros(produtoFiltro, paginacaoResposta)).thenReturn(criarPaginaResposta(1L));

        PaginaResposta<Produto> result = produtoListaUseCase.listarComFiltro(criarFiltro(null), paginacaoResposta);

        assertEquals(1, result.getTotalElementos());
        assertEquals(Categoria.LANCHE, result.getConteudo().getFirst().getCategoria());
        verify(produtoGateway).listarComFiltros(produtoFiltro, paginacaoResposta);
    }

    @Test
    @DisplayName(
            "Dado filtro por categoria e nome, quando listar produtos, então os produtos da categoria e nome devem ser retornados")
    void dadoProdutosExistentes_quandoFiltrarProdutos_entaoDevemSerRetornados() {
        ProdutoFiltro produtoFiltro = criarProdutoFiltro("x-burguer");
        PaginaResposta<Produto> produtoPaginaResposta = criarPaginaResposta(10L);
        when(produtoGateway.listarComFiltros(produtoFiltro, paginacaoResposta)).thenReturn(produtoPaginaResposta);

        PaginaResposta<Produto> result =
                produtoListaUseCase.listarComFiltro(criarFiltro("x-burguer"), paginacaoResposta);
        assertEquals(10, result.getTotalElementos());
        verify(produtoGateway).listarComFiltros(produtoFiltro, paginacaoResposta);
    }

    @Test
    @DisplayName("Dado filtro vazio, quando listar produtos, então todos os produtos devem ser retornados")
    void dadoFiltroVazio_quandoListarProdutos_entaoTodosProdutosDevemSerRetornados() {
        ProdutoFiltro produtoFiltro = ProdutoFiltroDataPool.criarFiltroVazio();
        when(produtoGateway.listarComFiltros(produtoFiltro, paginacaoResposta)).thenReturn(criarPaginaResposta(2L));

        PaginaResposta<Produto> result =
                produtoListaUseCase.listarComFiltro(ProdutoFiltroEntradaDataPool.filtroVazio(), paginacaoResposta);

        assertEquals(2, result.getTotalElementos());
        verify(produtoGateway).listarComFiltros(produtoFiltro, paginacaoResposta);
    }

    @Test
    @DisplayName("Dado filtro de produto inexistente, quando listar produtos, então nenhuma produto deve ser retornado")
    void dadoFiltroDeProdutoInexistente_quandoListarProdutos_entaoNenhumProdutoDeveSerRetornado() {
        ProdutoFiltroEntrada filtro = criarFiltro("inexistente");
        ProdutoFiltro produtoFiltro = criarProdutoFiltro("inexistente");
        when(produtoGateway.listarComFiltros(produtoFiltro, paginacaoResposta))
                .thenReturn(PaginaRespostaDataPool.criarPaginaComPaginacao(Collections.emptyList(), 0L, 0, 10, 0));

        PaginaResposta<Produto> result = produtoListaUseCase.listarComFiltro(filtro, paginacaoResposta);

        assertEquals(0, result.getTotalElementos());
        verify(produtoGateway).listarComFiltros(produtoFiltro, paginacaoResposta);
    }

    @Test
    @DisplayName(
            "Dado filtro e ordenação, quando listar produtos, então os produtos devem ser retornados na ordem correta")
    void dadoFiltroComOrdenacao_quandoListarProdutos_entaoProdutosDevemSerOrdenados() {
        when(produtoGateway.listarComFiltros(any(ProdutoFiltro.class), any(PaginacaoResposta.class)))
                .thenReturn(criarPaginaResposta(2L));

        PaginaResposta<Produto> result = produtoListaUseCase.listarComFiltro(criarFiltro(null), paginacaoResposta);

        assertEquals(2, result.getTotalElementos());
        verify(produtoGateway).listarComFiltros(criarProdutoFiltro(null), paginacaoResposta);
    }

    @Test
    @DisplayName(
            "Dado uma  categoria inválida no filtro, quando listar os produtos, então uma exceção deve ser lançada")
    void dadoUmaCategoriaInvalida_quandoListarProdutos_entaoUmaExcecaoDeveSerLancada() {
        CategoriaNaoEncontradaExcecao exception = assertThrows(
                CategoriaNaoEncontradaExcecao.class,
                () -> produtoListaUseCase.listarComFiltro(
                        ProdutoFiltroEntradaDataPool.criarProdutoFiltro(null, "INVALIDA"), paginacaoResposta));

        assertEquals("Categoria (INVALIDA) não encontrada", exception.getMessage());
        verify(produtoGateway, never()).listarComFiltros(any(), eq(paginacaoResposta));
    }
}
