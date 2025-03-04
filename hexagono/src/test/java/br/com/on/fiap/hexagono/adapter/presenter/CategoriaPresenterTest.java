package br.com.on.fiap.hexagono.adapter.presenter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.on.fiap.hexagono.adapter.dto.CategoriaSaidaDTO;
import br.com.on.fiap.hexagono.adapter.presenter.base.CategoriaBasePresenter;
import br.com.on.fiap.hexagono.domain.entity.Categoria;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CategoriaPresenterTest {

    @Test
    @DisplayName("Dado um objeto de dados de saída, quando formatá-lo, então um objeto formatado deve ser retornado")
    void dadoUmObjetoDeDadosDeSaida_quandoFormatalo_entaoUmObjetoFormatadoDeveSerRetornado() {
        List<Categoria> categorias = Arrays.asList(Categoria.values());
        CategoriaBasePresenter categoriaBasePresenter = new CategoriaPresenter();

        CategoriaSaidaDTO resultado = categoriaBasePresenter.formatar(categorias);

        assertNotNull(resultado);
        assertEquals(Categoria.values().length, resultado.categorias().size());
        for (Categoria categoria : categorias) {
            assertEquals(categoria.name(), resultado.categorias().get(categoria.ordinal()));
        }
    }
}
