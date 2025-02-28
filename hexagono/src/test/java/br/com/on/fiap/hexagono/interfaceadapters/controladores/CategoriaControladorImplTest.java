package br.com.on.fiap.hexagono.interfaceadapters.controladores;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.hexagono.entities.entidades.Categoria;
import br.com.on.fiap.hexagono.interfaceadapters.interfaces.dto.CategoriaSaidaDTO;
import br.com.on.fiap.hexagono.interfaceadapters.interfaces.presenter.CategoriaApresentador;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.categoria.BuscaCategoriaCasoDeUso;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CategoriaControladorImplTest {

    @Mock
    private BuscaCategoriaCasoDeUso buscaCategoriaCasoDeUso;

    @Mock
    private CategoriaApresentador categoriaApresentador;

    @InjectMocks
    private CategoriaControladorImpl categoriaControlador;

    @Test
    @DisplayName(
            "Dado um cpf, quando buscar o cliente pelo CPF, então um cliente deve ser retornado no formato esperado")
    void dadoDesejoDeBuscarCategorias_quandoBuscar_entaoCategoriasDevemSerRetornadas() {
        List<Categoria> categorias = Arrays.asList(Categoria.BEBIDA, Categoria.SOBREMESA);
        CategoriaSaidaDTO categoriaSaidaDTO = new CategoriaSaidaDTO(Arrays.asList("BEBIDA", "SOBREMESA"));
        when(buscaCategoriaCasoDeUso.buscaCategorias()).thenReturn(categorias);
        when(categoriaApresentador.formatar(categorias)).thenReturn(categoriaSaidaDTO);

        CategoriaSaidaDTO resultado = categoriaControlador.buscaCategorias();

        assertNotNull(resultado);
        assertEquals(categoriaSaidaDTO, resultado);
        verify(buscaCategoriaCasoDeUso).buscaCategorias();
        verify(categoriaApresentador).formatar(categorias);
    }
}
