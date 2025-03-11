package br.com.on.fiap.core.adapter.gateway;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.core.adapter.datasource.ProdutoDataSource;
import br.com.on.fiap.core.application.dto.resposta.Direcao;
import br.com.on.fiap.core.application.dto.resposta.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.PaginacaoResposta;
import br.com.on.fiap.core.domain.Produto;
import br.com.on.fiap.core.domain.ProdutoFiltro;
import br.com.on.fiap.datapool.OrdenacaoDataPool;
import br.com.on.fiap.datapool.PaginaRespostaDataPool;
import br.com.on.fiap.datapool.ProdutoDataPool;
import br.com.on.fiap.datapool.ProdutoFiltroDataPool;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProdutoGatewayImplTest {

    @Mock
    private ProdutoDataSource produtoDataSource;

    @InjectMocks
    private ProdutoGatewayImpl produtoGateway;

    @Test
    @DisplayName("Dado um ID válido, quando buscar produto por ID, então deve retornar o produto")
    void dadoIdValido_quandoBuscarProdutoPorId_entaoDeveRetornarProduto() {
        Produto produto = ProdutoDataPool.criarProdutoNovo();
        Long id = produto.getId();
        when(produtoDataSource.buscaProdutoPorId(id)).thenReturn(Optional.of(produto));

        Optional<Produto> resultado = produtoGateway.buscaProdutoPorId(id);

        assertTrue(resultado.isPresent());
        assertEquals(produto, resultado.get());
        verify(produtoDataSource).buscaProdutoPorId(id);
    }

    @Test
    @DisplayName(
            "Dada uma lista de IDs válidos, quando buscar produtos por IDs, então deve retornar a lista de produtos")
    void dadaListaIdsValidos_quandoBuscarProdutosPorIds_entaoDeveRetornarListaDeProdutos() {
        List<Long> ids = List.of(1L, 2L, 3L);
        List<Produto> produtos = ProdutoDataPool.criarProdutosComIdsDinamicos(3);
        when(produtoDataSource.buscaProdutoPorIdsLista(ids)).thenReturn(produtos);

        List<Produto> resultado = produtoGateway.buscaProdutoPorIdsLista(ids);

        assertNotNull(resultado);
        assertEquals(produtos, resultado);
        verify(produtoDataSource).buscaProdutoPorIdsLista(ids);
    }

    @Test
    @DisplayName("Dado um produto válido, quando salvar produto, então deve retornar o produto salvo")
    void dadoProdutoValido_quandoSalvarProduto_entaoDeveRetornarProdutoSalvo() {
        Produto produto = ProdutoDataPool.criarProdutoNovo();
        when(produtoDataSource.salvaProduto(produto)).thenReturn(produto);

        Produto resultado = produtoGateway.salvaProduto(produto);

        assertNotNull(resultado);
        assertEquals(produto, resultado);
        verify(produtoDataSource).salvaProduto(produto);
    }

    @Test
    @DisplayName("Dado um nome válido, quando buscar produto por nome, então deve retornar o produto")
    void dadoNomeValido_quandoBuscarProdutoPorNome_entaoDeveRetornarProduto() {
        Produto produto = ProdutoDataPool.criarProdutoNovo();
        String nome = produto.getNome();
        when(produtoDataSource.buscaProdutoPorNome(nome)).thenReturn(Optional.of(produto));

        Optional<Produto> resultado = produtoGateway.buscaProdutoPorNome(nome);

        assertTrue(resultado.isPresent());
        assertEquals(produto, resultado.get());
        verify(produtoDataSource).buscaProdutoPorNome(nome);
    }

    @Test
    @DisplayName("Dado um ID válido, quando deletar produto por ID, então deve chamar o método deletaProdutoPorId")
    void dadoIdValido_quandoDeletarProdutoPorId_entaoDeveChamarDeletaProdutoPorId() {
        Long id = 1L;

        produtoGateway.deletaProdutoPorId(id);

        verify(produtoDataSource).deletaProdutoPorId(id);
    }

    @Test
    @DisplayName(
            "Dado um filtro e uma paginação válidos, quando listar produtos com filtros, então deve retornar a página de produtos")
    void dadoFiltroEPaginacaoValidos_quandoListarProdutosComFiltros_entaoDeveRetornarPaginaDeProdutos() {
        ProdutoFiltro filtro = ProdutoFiltroDataPool.criarFiltroVazio();
        PaginacaoResposta paginacaoResposta =
                PaginacaoResposta.create(0, 10, OrdenacaoDataPool.criarOrdenacaoPorCampoEDirecao("nome", Direcao.ASC));
        PaginaResposta<Produto> paginaResposta =
                PaginaRespostaDataPool.criarPaginaComPaginacao(Collections.emptyList(), 0L, 0, 10, 0);

        when(produtoDataSource.listarComFiltros(filtro, paginacaoResposta)).thenReturn(paginaResposta);

        PaginaResposta<Produto> resultado = produtoGateway.listarComFiltros(filtro, paginacaoResposta);

        assertNotNull(resultado);
        verify(produtoDataSource).listarComFiltros(filtro, paginacaoResposta);
    }
}
