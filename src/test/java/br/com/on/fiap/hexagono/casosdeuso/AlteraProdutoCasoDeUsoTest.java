package br.com.on.fiap.hexagono.casosdeuso;

import br.com.on.fiap.hexagono.casosdeuso.excecao.ProdutoNaoEncontratoExcecao;
import br.com.on.fiap.hexagono.dominio.Categoria;
import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.portas.saida.PersisteProdutoPortaSaida;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AlteraProdutoCasoDeUsoTest {


    @Mock
    private PersisteProdutoPortaSaida persisteProdutoPortaSaida;

    AlteraProdutoCasoDeUso alteraProdutoCasoDeUso;

    @BeforeEach
    void iniciaCasoDeUuso() {
        this.alteraProdutoCasoDeUso = new AlteraProdutoCasoDeUso(persisteProdutoPortaSaida);
    }

    @Test
    public void Deveria_AlterarProduto_ComSucesso(){

        Long idProduto = 1L;
        Optional<Produto> produtoMockado = Optional.of(new Produto(idProduto, "Batata frita", Categoria.Acompanhamento, BigDecimal.TEN));
        Produto produtoEntrada = new Produto("Batata frita com bacon", Categoria.Acompanhamento, BigDecimal.valueOf(15.0));
        Produto produtoSaidaMockado = new Produto(idProduto, "Batata frita com bacon", Categoria.Acompanhamento, BigDecimal.valueOf(15.0));

        when(persisteProdutoPortaSaida.buscaProdutoPorId(idProduto)).thenReturn(produtoMockado);
        when(persisteProdutoPortaSaida.salvaProduto(produtoEntrada)).thenReturn(produtoSaidaMockado);


        Produto produtoAlterado = alteraProdutoCasoDeUso.alterar(1L, produtoEntrada);

        assertNotNull(produtoAlterado);
        assertEquals(idProduto, produtoAlterado.getId());
        assertEquals("Batata frita com bacon", produtoAlterado.getNome());
        assertEquals(Categoria.Acompanhamento, produtoAlterado.getCategoria());
        assertEquals(BigDecimal.valueOf(15.0), produtoAlterado.getPreco());
        verify(persisteProdutoPortaSaida, times(1)).buscaProdutoPorId(eq(idProduto));
        verify(persisteProdutoPortaSaida, times(1)).salvaProduto(eq(produtoEntrada));
    }

    @Test
    public void Deveria_LancarExcecao_Quando_IdDeProdutoInexistente(){

        Long idProduto = 1L;
        Optional<Produto> produtoMockado = Optional.empty();
        Produto produtoEntrada = new Produto("Batata frita com bacon", Categoria.Acompanhamento, BigDecimal.valueOf(15.0));

        when(persisteProdutoPortaSaida.buscaProdutoPorId(idProduto)).thenReturn(produtoMockado);

        Exception excecao = assertThrows(ProdutoNaoEncontratoExcecao.class, () ->  alteraProdutoCasoDeUso.alterar(1L, produtoEntrada));
        assertEquals("Produto (1) não encontrado", excecao.getMessage());
        verify(persisteProdutoPortaSaida, times(1)).buscaProdutoPorId(eq(idProduto));
        verify(persisteProdutoPortaSaida, times(0)).salvaProduto(produtoEntrada);

    }

}