package br.com.on.fiap.hexagono.usecases.produto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.on.fiap.hexagono.datapool.DataPoolProduto;
import br.com.on.fiap.hexagono.entities.entidades.Produto;
import br.com.on.fiap.hexagono.usecases.casodeuso.produto.AlteraProdutoCasoDeUsoImpl;
import br.com.on.fiap.hexagono.usecases.excecao.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.usecases.interfaces.saida.produto.PersisteProdutoPortaSaida;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AlteraProdutoCasoDeUsoImplTest {

    @Mock
    private PersisteProdutoPortaSaida persisteProdutoPortaSaida;

    @InjectMocks
    private AlteraProdutoCasoDeUsoImpl alteraProdutoCasoDeUsoImpl;

    @Test
    @DisplayName("Dado um produto existente, quando alterar o produto, então ele deve ser atualizado")
    void dadoProdutoExistente_quandoAlterarProduto_entaoDeveSerAtualizado() {
        Long id = 1L;
        Produto produto = DataPoolProduto.produtoExistente(id);

        Produto produtoAlterado = DataPoolProduto.produtoExistente(id);
        produtoAlterado.setNome("Produto Alterado");

        when(persisteProdutoPortaSaida.buscaProdutoPorId(id)).thenReturn(Optional.of(produto));
        when(persisteProdutoPortaSaida.salvaProduto(produto)).thenReturn(produtoAlterado);

        Produto resultado = alteraProdutoCasoDeUsoImpl.alterar(id, produto);

        assertAll(
                () -> assertEquals(id, resultado.getId()), () -> assertEquals("Produto Alterado", resultado.getNome()));
        verify(persisteProdutoPortaSaida).buscaProdutoPorId(id);
        verify(persisteProdutoPortaSaida).salvaProduto(produto);
    }

    @Test
    @DisplayName("Dado um produto não existente, quando alterar o produto, então deve lançar uma exceção")
    void dadoProdutoNaoExistente_quandoAlterarProduto_entaoDeveLancarExcecao() {
        Long id = 1L;
        Produto produto = DataPoolProduto.produtoExistente(id);

        when(persisteProdutoPortaSaida.buscaProdutoPorId(id)).thenReturn(Optional.empty());

        assertThrows(ProdutoNaoEncontradoExcecao.class, () -> alteraProdutoCasoDeUsoImpl.alterar(id, produto));

        verify(persisteProdutoPortaSaida).buscaProdutoPorId(id);
        verify(persisteProdutoPortaSaida, never()).salvaProduto(produto);
    }
}
