package br.com.on.fiap.hexagono.casosdeuso;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.hexagono.casosdeuso.produto.ListarProdutoCasoDeUso;
import br.com.on.fiap.hexagono.dominio.Categoria;
import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.dominio.ProdutoFiltro;
import br.com.on.fiap.hexagono.portas.saida.produto.PersisteProdutoPortaSaida;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class ListarProdutoCasoDeUsoTest {

    @Mock
    private PersisteProdutoPortaSaida persisteProdutoPortaSaida;

    @InjectMocks
    private ListarProdutoCasoDeUso listarProdutoCasoDeUso;

    @Test
    @DisplayName("Dado filtro por categoria, quando listar produtos, então produtos da categoria devem ser retornados")
    void dadoFiltroPorCategoria_quandoListarProdutos_entaoProdutosDaCategoriaDevemSerRetornados() {
        ProdutoFiltro filtro = new ProdutoFiltro(null, Categoria.LANCHE);
        Pageable paginacao = PageRequest.of(0, 10);
        Produto produto = new Produto(1L, "x-burguer", Categoria.LANCHE, BigDecimal.TEN);
        PageImpl<Produto> page = new PageImpl<>(List.of(produto), paginacao, 1L);
        when(persisteProdutoPortaSaida.listarComFiltros(filtro, paginacao)).thenReturn(page);

        Page<Produto> result = listarProdutoCasoDeUso.listarComFiltro(filtro, paginacao);

        assertEquals(1, result.getTotalElements());
        assertEquals(Categoria.LANCHE, result.getContent().getFirst().getCategoria());
        verify(persisteProdutoPortaSaida).listarComFiltros(filtro, paginacao);
    }

    @Test
    @DisplayName(
            "Dado filtro por categoria e nome, quando listar produtos, então os produtos da categoria e nome devem ser retornados")
    void dadoProdutosExistentes_quandoFiltrarProdutos_entaoDevemSerRetornados() {

        ProdutoFiltro filtro = new ProdutoFiltro("x-burguer", Categoria.LANCHE);
        Pageable paginacao = PageRequest.of(0, 10);
        Produto produto = new Produto(1L, "x-burguer", Categoria.LANCHE, BigDecimal.TEN);
        PageImpl<Produto> page = new PageImpl<>(List.of(produto), paginacao, 10L);
        when(persisteProdutoPortaSaida.listarComFiltros(filtro, paginacao)).thenReturn(page);

        listarProdutoCasoDeUso.listarComFiltro(filtro, paginacao);

        assertEquals(produto, page.getContent().getFirst());
        verify(persisteProdutoPortaSaida).listarComFiltros(filtro, paginacao);
    }

    @Test
    @DisplayName("Dado filtro vazio, quando listar produtos, então todos os produtos devem ser retornados")
    void dadoFiltroVazio_quandoListarProdutos_entaoTodosProdutosDevemSerRetornados() {
        ProdutoFiltro filtro = new ProdutoFiltro();
        Pageable paginacao = PageRequest.of(0, 10);
        Produto produto1 = new Produto(1L, "x-burguer", Categoria.LANCHE, BigDecimal.TEN);
        Produto produto2 = new Produto(2L, "pizza", Categoria.LANCHE, BigDecimal.valueOf(20));
        PageImpl<Produto> page = new PageImpl<>(List.of(produto1, produto2), paginacao, 2L);
        when(persisteProdutoPortaSaida.listarComFiltros(filtro, paginacao)).thenReturn(page);

        Page<Produto> result = listarProdutoCasoDeUso.listarComFiltro(filtro, paginacao);

        assertEquals(2, result.getTotalElements());
        verify(persisteProdutoPortaSaida).listarComFiltros(filtro, paginacao);
    }

    @Test
    @DisplayName("Dado filtro de produto inexistente, quando listar produtos, então nenhuma produto deve ser retornado")
    void dadoFiltroDeProdutoInexistente_quandoListarProdutos_entaoNenhumProdutoDeveSerRetornado() {
        ProdutoFiltro filtro = new ProdutoFiltro("inexistente", Categoria.LANCHE);
        Pageable paginacao = PageRequest.of(0, 10);
        PageImpl<Produto> page = new PageImpl<>(Collections.emptyList(), paginacao, 0L);
        when(persisteProdutoPortaSaida.listarComFiltros(filtro, paginacao)).thenReturn(page);

        Page<Produto> result = listarProdutoCasoDeUso.listarComFiltro(filtro, paginacao);

        assertEquals(0, result.getTotalElements());
        verify(persisteProdutoPortaSaida).listarComFiltros(filtro, paginacao);
    }
}
