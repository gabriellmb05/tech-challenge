package br.com.on.fiap.hexagono.usecase.produto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.hexagono.adapter.gateway.ProdutoGateway;
import br.com.on.fiap.hexagono.application.usecase.produto.ProdutoBuscaPorIdUseCaseImpl;
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
class ProdutoBuscaPorIdUseCaseImplTest {

    @Mock
    private ProdutoGateway produtoGateway;

    @InjectMocks
    private ProdutoBuscaPorIdUseCaseImpl produtoBuscaPorIdUseCase;

    @Test
    @DisplayName("Dado um produto existente, quando buscar o produto, então ele deve ser retornado")
    void dadoProdutoExistente_quandoBuscarProduto_entaoDeveSerRetornado() {
        Long id = 1L;
        Produto produto = DataPoolProduto.produtoExistente(id);
        when(produtoGateway.buscaProdutoPorId(id)).thenReturn(Optional.of(produto));

        Produto resultado = produtoBuscaPorIdUseCase.buscar(id);

        assertEquals(id, resultado.getId());
        verify(produtoGateway).buscaProdutoPorId(id);
    }

    @Test
    @DisplayName("Dado um produto não existente, quando buscar o produto, então deve lançar uma exceção")
    void dadoProdutoNaoExistente_quandoBuscarProduto_entaoDeveLancarExcecao() {
        Long id = 1L;
        when(produtoGateway.buscaProdutoPorId(id)).thenReturn(Optional.empty());

        assertThrows(ProdutoNaoEncontradoExcecao.class, () -> produtoBuscaPorIdUseCase.buscar(id));
        verify(produtoGateway).buscaProdutoPorId(id);
    }
}
