package br.com.on.fiap.core.adapter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.core.adapter.controller.impl.ProdutoControllerImpl;
import br.com.on.fiap.core.adapter.presenter.ProdutoPresenter;
import br.com.on.fiap.core.application.dto.entrada.ProdutoEntrada;
import br.com.on.fiap.core.application.dto.filtro.ProdutoFiltroEntrada;
import br.com.on.fiap.core.application.dto.resposta.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.PaginacaoResposta;
import br.com.on.fiap.core.application.dto.resposta.ProdutoResposta;
import br.com.on.fiap.core.application.usecase.produto.*;
import br.com.on.fiap.core.domain.Produto;
import br.com.on.fiap.datapool.ProdutoDataPool;
import br.com.on.fiap.datapool.ProdutoEntradaDataPool;
import br.com.on.fiap.datapool.ProdutoFiltroEntradaDataPool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProdutoControllerImplTest {

    @Mock
    private ProdutoBuscaPorIdUseCase produtoBuscaPorIdUseCase;

    @Mock
    private ProdutoListaUseCase produtoListaUseCase;

    @Mock
    private ProdutoInsereUseCase produtoInsereUseCase;

    @Mock
    private ProdutoAlteraUseCase produtoAlteraUseCase;

    @Mock
    private ProdutoDeletaUseCase produtoDeletaUseCase;

    @Mock
    private ProdutoPresenter produtoPresenter;

    @InjectMocks
    private ProdutoControllerImpl produtoController;

    private final PaginacaoResposta paginacaoResposta = PaginacaoResposta.create(0, 10, null);

    @Test
    @DisplayName("Dado um id, quando buscar o produto por id, então deve retornar o produto formatado")
    void dadoId_quandoBuscarProdutoPorId_entaoDeveRetornarProdutoFormatado() {
        Long id = 1L;
        Produto produto = ProdutoDataPool.criarProdutoNovo();
        ProdutoResposta produtoResposta = ProdutoResposta.create(produto);

        when(produtoBuscaPorIdUseCase.buscar(id)).thenReturn(produto);
        when(produtoPresenter.formatar(produto)).thenReturn(produtoResposta);

        ProdutoResposta resultado = produtoController.buscaProdutoPorId(id);

        assertNotNull(resultado);
        assertEquals(produtoResposta.getId(), resultado.getId());
        verify(produtoBuscaPorIdUseCase).buscar(id);
        verify(produtoPresenter).formatar(produto);
    }

    @Test
    @DisplayName(
            "Dado um filtro e paginação, quando listar produtos, então deve retornar uma lista de produtos formatados")
    void dadoFiltroEPaginacao_quandoListarProdutos_entaoDeveRetornarPaginaComProdutosFormatados() {
        ProdutoFiltroEntrada produtoFiltroEntrada = ProdutoFiltroEntradaDataPool.filtroVazio(); // Exemplo de filtro
        PaginaResposta<Produto> produtoPaginaResposta = ProdutoDataPool.criarPaginaRespostaProdutosValida();
        PaginaResposta<ProdutoResposta> paginaRespostaFormatada =
                ProdutoDataPool.criarPaginaRespostaProdutosFormatada();

        when(produtoListaUseCase.listarComFiltro(produtoFiltroEntrada, paginacaoResposta))
                .thenReturn(produtoPaginaResposta);
        when(produtoPresenter.formatar(produtoPaginaResposta)).thenReturn(paginaRespostaFormatada);

        PaginaResposta<ProdutoResposta> resultado =
                produtoController.listarProdutosComFiltro(produtoFiltroEntrada, paginacaoResposta);

        assertNotNull(resultado);
        assertEquals(
                paginaRespostaFormatada.getConteudo().size(),
                resultado.getConteudo().size());
        verify(produtoListaUseCase).listarComFiltro(produtoFiltroEntrada, paginacaoResposta);
        verify(produtoPresenter).formatar(produtoPaginaResposta);
    }

    @Test
    @DisplayName("Dado um produto de entrada, quando inserir produto, então deve retornar o produto inserido formatado")
    void dadoProdutoEntrada_quandoInserirProduto_entaoDeveRetornarProdutoInseridoFormatado() {
        ProdutoEntrada produtoEntrada = ProdutoEntradaDataPool.criarProdutoEntradaExistente(1L);
        Produto produtoPersistido = ProdutoDataPool.criarProdutoExistente(1L);
        ProdutoResposta produtoResposta = ProdutoResposta.create(produtoPersistido);

        when(produtoInsereUseCase.inserir(produtoEntrada)).thenReturn(produtoPersistido);
        when(produtoPresenter.formatar(produtoPersistido)).thenReturn(produtoResposta);

        ProdutoResposta resultado = produtoController.insereProduto(produtoEntrada);

        assertNotNull(resultado);
        assertEquals(produtoResposta.getId(), resultado.getId());
        verify(produtoInsereUseCase).inserir(produtoEntrada);
        verify(produtoPresenter).formatar(produtoPersistido);
    }

    @Test
    @DisplayName(
            "Dado um id e produto de entrada, quando alterar produto, então deve retornar o produto alterado formatado")
    void dadoIdEProdutoEntrada_quandoAlterarProduto_entaoDeveRetornarProdutoAlteradoFormatado() {
        Long id = 1L;
        ProdutoEntrada produtoEntrada = ProdutoEntradaDataPool.criarProdutoEntradaExistente(1L);
        Produto produtoAtualizado = ProdutoDataPool.criarProdutoExistente(1L);
        ProdutoResposta produtoResposta = ProdutoResposta.create(produtoAtualizado);

        when(produtoAlteraUseCase.alterar(id, produtoEntrada)).thenReturn(produtoAtualizado);
        when(produtoPresenter.formatar(produtoAtualizado)).thenReturn(produtoResposta);

        ProdutoResposta resultado = produtoController.alteraProduto(id, produtoEntrada);

        assertNotNull(resultado);
        assertEquals(produtoResposta.getId(), resultado.getId());
        verify(produtoAlteraUseCase).alterar(id, produtoEntrada);
        verify(produtoPresenter).formatar(produtoAtualizado);
    }

    @Test
    @DisplayName("Dado um id, quando deletar produto, então o método deleta deve ser chamado")
    void dadoId_quandoDeletarProduto_entaoDeveChamarDeletaProduto() {
        Long id = 1L;

        produtoController.deletaProduto(id);

        verify(produtoDeletaUseCase).deleta(id);
    }
}
