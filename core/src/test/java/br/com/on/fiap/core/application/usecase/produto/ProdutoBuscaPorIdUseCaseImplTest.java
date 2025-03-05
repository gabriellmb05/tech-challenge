package br.com.on.fiap.core.application.usecase.produto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.core.adapter.gateway.ProdutoGateway;
import br.com.on.fiap.core.domain.exception.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.core.domain.model.Produto;
import br.com.on.fiap.core.usecase.produto.impl.ProdutoBuscaPorIdUseCaseImpl;
import br.com.on.fiap.datapool.DataPoolProduto;
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
