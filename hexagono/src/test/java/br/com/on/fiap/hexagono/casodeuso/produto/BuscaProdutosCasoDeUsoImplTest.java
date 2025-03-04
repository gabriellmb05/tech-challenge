package br.com.on.fiap.hexagono.casodeuso.produto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.hexagono.casodeuso.produto.saida.PersisteProdutoPortaSaida;
import br.com.on.fiap.hexagono.datapool.DataPoolProduto;
import br.com.on.fiap.hexagono.datapool.DataPoolProdutoFiltro;
import br.com.on.fiap.hexagono.entidades.Categoria;
import br.com.on.fiap.hexagono.entidades.Produto;
import br.com.on.fiap.hexagono.entidades.ProdutoFiltro;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Order;

@ExtendWith(MockitoExtension.class)
class BuscaProdutosCasoDeUsoImplTest {

    @Mock
    private PersisteProdutoPortaSaida persisteProdutoPortaSaida;

    @InjectMocks
    private BuscaProdutosCasoDeUsoImpl buscaProdutosCasoDeUsoImpl;

    @Test
    @DisplayName("Dado filtro por categoria, quando listar produtos, então produtos da categoria devem ser retornados")
    void dadoFiltroPorCategoria_quandoListarProdutos_entaoProdutosDaCategoriaDevemSerRetornados() {
        ProdutoFiltro filtro = DataPoolProdutoFiltro.filtroPorNomeECategoria(null, Categoria.LANCHE);
        Pageable paginacao = PageRequest.of(0, 10);
        List<Produto> produtos = DataPoolProduto.produtosComIdsDinamicos(1);
        when(persisteProdutoPortaSaida.listarComFiltros(filtro, paginacao))
                .thenReturn(new PageImpl<>(produtos, paginacao, produtos.size()));

        Page<Produto> result = buscaProdutosCasoDeUsoImpl.listarComFiltro(filtro, paginacao);

        assertEquals(1, result.getTotalElements());
        assertEquals(Categoria.LANCHE, result.getContent().getFirst().getCategoria());
        verify(persisteProdutoPortaSaida).listarComFiltros(filtro, paginacao);
    }

    @Test
    @DisplayName(
            "Dado filtro por categoria e nome, quando listar produtos, então os produtos da categoria e nome devem ser retornados")
    void dadoProdutosExistentes_quandoFiltrarProdutos_entaoDevemSerRetornados() {
        ProdutoFiltro filtro = DataPoolProdutoFiltro.filtroPorNomeECategoria("x-burguer", Categoria.LANCHE);
        Pageable paginacao = PageRequest.of(0, 10);
        List<Produto> produtos = DataPoolProduto.produtosComIdsDinamicos(10);
        when(persisteProdutoPortaSaida.listarComFiltros(filtro, paginacao))
                .thenReturn(new PageImpl<>(produtos, paginacao, produtos.size()));

        Page<Produto> result = buscaProdutosCasoDeUsoImpl.listarComFiltro(filtro, paginacao);
        assertEquals(10, result.getTotalElements());
        verify(persisteProdutoPortaSaida).listarComFiltros(filtro, paginacao);
    }

    @Test
    @DisplayName("Dado filtro vazio, quando listar produtos, então todos os produtos devem ser retornados")
    void dadoFiltroVazio_quandoListarProdutos_entaoTodosProdutosDevemSerRetornados() {
        ProdutoFiltro filtro = DataPoolProdutoFiltro.filtroVazio();
        Pageable paginacao = PageRequest.of(0, 10);
        List<Produto> produtos = DataPoolProduto.produtosComIdsDinamicos(2);
        when(persisteProdutoPortaSaida.listarComFiltros(filtro, paginacao))
                .thenReturn(new PageImpl<>(produtos, paginacao, produtos.size()));

        Page<Produto> result = buscaProdutosCasoDeUsoImpl.listarComFiltro(filtro, paginacao);

        assertEquals(2, result.getTotalElements());
        verify(persisteProdutoPortaSaida).listarComFiltros(filtro, paginacao);
    }

    @Test
    @DisplayName("Dado filtro de produto inexistente, quando listar produtos, então nenhuma produto deve ser retornado")
    void dadoFiltroDeProdutoInexistente_quandoListarProdutos_entaoNenhumProdutoDeveSerRetornado() {
        ProdutoFiltro filtro = DataPoolProdutoFiltro.filtroPorNomeECategoria("inexistente", Categoria.LANCHE);
        Pageable paginacao = PageRequest.of(0, 10);
        when(persisteProdutoPortaSaida.listarComFiltros(filtro, paginacao))
                .thenReturn(new PageImpl<>(Collections.emptyList(), paginacao, 0L));

        Page<Produto> result = buscaProdutosCasoDeUsoImpl.listarComFiltro(filtro, paginacao);

        assertEquals(0, result.getTotalElements());
        verify(persisteProdutoPortaSaida).listarComFiltros(filtro, paginacao);
    }

    @Test
    @DisplayName(
            "Dado filtro e ordenação, quando listar produtos, então os produtos devem ser retornados na ordem correta")
    void dadoFiltroComOrdenacao_quandoListarProdutos_entaoProdutosDevemSerOrdenados() {
        ProdutoFiltro filtro = DataPoolProdutoFiltro.filtroPorNomeECategoria(null, Categoria.LANCHE);
        Pageable paginacao = PageRequest.of(0, 10, Sort.by(Order.asc("nome")));
        List<Produto> produtos = DataPoolProduto.produtosComIdsDinamicos(2);
        when(persisteProdutoPortaSaida.listarComFiltros(filtro, paginacao))
                .thenReturn(new PageImpl<>(produtos, paginacao, produtos.size()));

        Page<Produto> result = buscaProdutosCasoDeUsoImpl.listarComFiltro(filtro, paginacao);

        assertEquals(2, result.getTotalElements());
        verify(persisteProdutoPortaSaida).listarComFiltros(filtro, paginacao);
    }
}
