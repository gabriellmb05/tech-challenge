package br.com.on.fiap.hexagono.interfaceadapters.apresentadores;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.on.fiap.hexagono.entities.entidades.Categoria;
import br.com.on.fiap.hexagono.interfaceadapters.interfaces.dto.CategoriaSaidaDTO;
import br.com.on.fiap.hexagono.interfaceadapters.interfaces.presenter.CategoriaApresentador;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CategoriaApresentadorImplTest {

    @Test
    @DisplayName("Dado um objeto de dados de saída, quando formatá-lo, então um objeto formatado deve ser retornado")
    void dadoUmObjetoDeDadosDeSaida_quandoFormatalo_entaoUmObjetoFormatadoDeveSerRetornado() {
        List<Categoria> categorias = Arrays.asList(Categoria.values());
        CategoriaApresentador categoriaApresentador = new CategoriaApresentadorImpl();

        CategoriaSaidaDTO resultado = categoriaApresentador.formatar(categorias);

        assertNotNull(resultado);
        assertEquals(Categoria.values().length, resultado.categorias().size());
        for (Categoria categoria : categorias) {
            assertEquals(categoria.name(), resultado.categorias().get(categoria.ordinal()));
        }
    }
}
