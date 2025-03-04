package br.com.on.fiap.hexagono.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import br.com.on.fiap.hexagono.domain.exception.CategoriaNaoEncontradaExcecao;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CategoriaTest {

    @Test
    @DisplayName("Dado um valor de código quando buscar uma categoria, então a categoria deve ser retornada")
    void dadoCodigoValido_quandoBuscarCategoria_entaoCategoriaDeveSerRetornada() {
        List<Integer> codigos =
                Arrays.stream(Categoria.values()).map(Categoria::getCodigo).toList();
        for (Integer codigo : codigos) {
            Categoria categoria = Categoria.deCodigo(codigo);
            assertNotNull(categoria);
            assertEquals(codigo, categoria.getCodigo());
        }
    }

    @Test
    @DisplayName("Dado um valor de código inválido quando buscar uma categoria, então um erro deve ser retornado")
    void dadoCodigoInvalido_quandoBuscarCategoria_entaoErroDeveSerRetornado() {
        int codigoInvalido = 5;
        CategoriaNaoEncontradaExcecao exception =
                assertThrows(CategoriaNaoEncontradaExcecao.class, () -> Categoria.deCodigo(codigoInvalido));
        assertEquals("Categoria (5) não encontrada", exception.getMessage());
    }

    @Test
    @DisplayName("Dado um nome de código quando buscar uma categoria, então a categoria deve ser retornada")
    void dadoNomeValido_quandoBuscarCategoria_entaoCategoriaDeveSerRetornada() {
        List<String> codigos =
                Arrays.stream(Categoria.values()).map(Categoria::name).toList();
        for (String nome : codigos) {
            Categoria categoria = Categoria.deString(nome);
            assertNotNull(categoria);
            assertEquals(nome, categoria.name());
        }
    }

    @Test
    @DisplayName("Dado um nome de categoria inválido quando buscar uma categoria, então um erro deve ser retornado")
    void dadoNomeInvalido_quandoBuscarCategoria_entaoErroDeveSerRetornado() {
        String nomeInvalido = "INVALIDO";
        CategoriaNaoEncontradaExcecao exception =
                assertThrows(CategoriaNaoEncontradaExcecao.class, () -> Categoria.deString(nomeInvalido));
        assertEquals("Categoria (INVALIDO) não encontrada", exception.getMessage());
    }
}
