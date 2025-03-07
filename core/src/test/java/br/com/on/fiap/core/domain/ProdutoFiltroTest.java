package br.com.on.fiap.core.domain;

import static org.junit.jupiter.api.Assertions.*;

import br.com.on.fiap.core.application.exception.CategoriaNaoEncontradaExcecao;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProdutoFiltroTest {

    @Test
    @DisplayName(
            "Dado uma categoria nula, quando instanciar um ProdutoFiltro, então ela deve ser retornado com categoria nula")
    void dadoUmaCategoriaNula_quandoInstanciarUmProdutoFiltro_entaoDeveSerRetornado() {
        ProdutoFiltro produtoFiltro = new ProdutoFiltro("nome", null);

        assertNull(produtoFiltro.getCategoria());
        assertEquals("nome", produtoFiltro.getNome());
    }

    @Test
    @DisplayName(
            "Dado um nome de categoria válido, quando instanciar um ProdutoFiltro, então ela deve ser retornado com a categoria")
    void dadoNomeDeCategoriaValido_quandoInstanciarProdutoFiltro_entaoDeveSerRetornado() {
        List<String> categorias = Arrays.asList("LANCHE", "ACOMPANHAMENTO", "BEBIDA", "SOBREMESA");

        for (String categoria : categorias) {
            ProdutoFiltro produtoFiltro = new ProdutoFiltro("nome", categoria);
            assertEquals(
                    categorias.get(produtoFiltro.getCategoria().ordinal()),
                    produtoFiltro.getCategoria().name());
            assertEquals("nome", produtoFiltro.getNome());
        }
    }

    @Test
    @DisplayName(
            "Dado um nome de categoria inválido, quando instanciar um ProdutoFiltro, então deve lançar uma exceção")
    void dadoNomeDeCategoriaInvalido_quandoInstanciarProdutoFiltro_entaoDeveLancarUmaExcecao() {
        String categoriaInvalida = "INVALIDA";

        CategoriaNaoEncontradaExcecao exception =
                assertThrows(CategoriaNaoEncontradaExcecao.class, () -> new ProdutoFiltro("nome", categoriaInvalida));

        assertEquals("Categoria (INVALIDA) não encontrada", exception.getMessage());
    }
}
