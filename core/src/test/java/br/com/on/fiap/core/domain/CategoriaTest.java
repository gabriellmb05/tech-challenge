package br.com.on.fiap.core.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.on.fiap.core.application.exception.CategoriaNaoEncontradaExcecao;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CategoriaTest {

    @Test
    @DisplayName("Dado nome de categoria valido, quando buscar a categoria pelo nome, então ela deve ser retornada")
    void dadoNomeDeCategoriaValido_quandoBuscarCategoria_entaoDeveSerRetornado() {

        List<String> nomes = Arrays.asList("LANCHE", "ACOMPANHAMENTO", "BEBIDA", "SOBREMESA");
        for (String nome : nomes) {
            Categoria categoria = Categoria.deString(nome);
            assertEquals(nomes.get(categoria.ordinal()), categoria.name());
        }
    }

    @Test
    @DisplayName(
            "Dado nome de categoria inválido, quando buscar a categoria pelo nome, então deve lançar exceção de Categoria ({0}) não encontrada")
    void dadoNomeDeCategoriaInvalido_quandoBuscarCategoria_entaoDeveLancarExcecao() {
        String categoriaInvalida = "INVALIDA";

        CategoriaNaoEncontradaExcecao exception =
                assertThrows(CategoriaNaoEncontradaExcecao.class, () -> Categoria.deString(categoriaInvalida));

        assertEquals("Categoria (INVALIDA) não encontrada", exception.getMessage());
    }
}
