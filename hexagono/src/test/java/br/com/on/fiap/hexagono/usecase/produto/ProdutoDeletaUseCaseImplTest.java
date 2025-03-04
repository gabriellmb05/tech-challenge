package br.com.on.fiap.hexagono.usecase.produto;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import br.com.on.fiap.hexagono.datapool.DataPoolProduto;
import br.com.on.fiap.hexagono.domain.entity.Produto;
import br.com.on.fiap.hexagono.domain.exception.ProdutoNaoEncontradoExcecao;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProdutoDeletaUseCaseImplTest {

    @Mock
    private PersisteProdutoPortaSaida persisteProdutoPortaSaida;

    @InjectMocks
    private ProdutoDeletaUseCaseImpl deletaProdutoCasoDeUso;

    @Test
    @DisplayName("Dado um produto existente, quando deletar o produto, então ele deve ser removido")
    void dadoProdutoExistente_quandoDeletarProduto_entaoDeveSerRemovido() {
        Long id = 1L;
        Produto produto = DataPoolProduto.produtoExistente(id);
        when(persisteProdutoPortaSaida.buscaProdutoPorId(id)).thenReturn(Optional.of(produto));

        deletaProdutoCasoDeUso.deleta(id);

        verify(persisteProdutoPortaSaida).buscaProdutoPorId(id);
        verify(persisteProdutoPortaSaida).deletaProdutoPorId(id);
    }

    @Test
    @DisplayName("Dado um produto não existente, quando deletar o produto, então deve lançar uma exceção")
    void dadoProdutoNaoExistente_quandoDeletarProduto_entaoDeveLancarExcecao() {
        Long id = 1L;
        when(persisteProdutoPortaSaida.buscaProdutoPorId(id)).thenReturn(Optional.empty());

        assertThrows(ProdutoNaoEncontradoExcecao.class, () -> deletaProdutoCasoDeUso.deleta(id));
        verify(persisteProdutoPortaSaida).buscaProdutoPorId(id);
        verify(persisteProdutoPortaSaida, never()).deletaProdutoPorId(id);
    }
}
