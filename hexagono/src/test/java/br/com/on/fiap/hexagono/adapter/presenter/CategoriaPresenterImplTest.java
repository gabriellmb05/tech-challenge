package br.com.on.fiap.hexagono.adapter.presenter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.on.fiap.hexagono.adapter.presenter.impl.CategoriaPresenterImpl;
import br.com.on.fiap.hexagono.application.dto.CategoriaSaidaDTO;
import br.com.on.fiap.hexagono.domain.entity.Categoria;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CategoriaPresenterImplTest {

    @InjectMocks
    private CategoriaPresenterImpl categoriaPresenter;

    @Test
    @DisplayName("Dado um objeto de dados de saída, quando formatá-lo, então um objeto formatado deve ser retornado")
    void dadoUmObjetoDeDadosDeSaida_quandoFormatalo_entaoUmObjetoFormatadoDeveSerRetornado() {
        List<Categoria> categorias = Arrays.asList(Categoria.values());
        CategoriaSaidaDTO resultado = categoriaPresenter.formatar(categorias);

        assertNotNull(resultado);
        assertEquals(Categoria.values().length, resultado.categorias().size());
        for (Categoria categoria : categorias) {
            assertEquals(categoria.name(), resultado.categorias().get(categoria.ordinal()));
        }
    }
}
