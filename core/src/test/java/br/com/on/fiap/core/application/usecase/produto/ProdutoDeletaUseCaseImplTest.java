package br.com.on.fiap.core.application.usecase.produto;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import br.com.on.fiap.core.application.exception.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.core.application.gateway.ProdutoGateway;
import br.com.on.fiap.core.application.usecase.produto.impl.ProdutoDeletaUseCaseImpl;
import br.com.on.fiap.core.domain.Produto;
import br.com.on.fiap.datapool.ProdutoDataPool;
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
    private ProdutoGateway produtoGateway;

    @InjectMocks
    private ProdutoDeletaUseCaseImpl produtoDeletaUseCase;

    @Test
    @DisplayName("Dado um produto existente, quando deletar o produto, então ele deve ser removido")
    void dadoProdutoExistente_quandoDeletarProduto_entaoDeveSerRemovido() {
        Long id = 1L;
        Produto produto = ProdutoDataPool.criarProdutoExistente(id);
        when(produtoGateway.buscaProdutoPorId(id)).thenReturn(Optional.of(produto));

        produtoDeletaUseCase.deleta(id);

        verify(produtoGateway).buscaProdutoPorId(id);
        verify(produtoGateway).deletaProdutoPorId(id);
    }

    @Test
    @DisplayName("Dado um produto não existente, quando deletar o produto, então deve lançar uma exceção")
    void dadoProdutoNaoExistente_quandoDeletarProduto_entaoDeveLancarExcecao() {
        Long id = 1L;
        when(produtoGateway.buscaProdutoPorId(id)).thenReturn(Optional.empty());

        assertThrows(ProdutoNaoEncontradoExcecao.class, () -> produtoDeletaUseCase.deleta(id));
        verify(produtoGateway).buscaProdutoPorId(id);
        verify(produtoGateway, never()).deletaProdutoPorId(id);
    }
}
