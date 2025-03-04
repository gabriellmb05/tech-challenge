package br.com.on.fiap.core.usecase.produto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import br.com.on.fiap.core.adapter.gateway.ProdutoGateway;
import br.com.on.fiap.core.application.usecase.produto.impl.ProdutoInsereUseCaseImpl;
import br.com.on.fiap.core.datapool.DataPoolProduto;
import br.com.on.fiap.core.domain.entity.Produto;
import br.com.on.fiap.core.domain.exception.ProdutoExistenteExcecao;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProdutoInsereUseCaseImplTest {

    @Mock
    private ProdutoGateway produtoGateway;

    @InjectMocks
    private ProdutoInsereUseCaseImpl produtoInsereUseCase;

    @Test
    @DisplayName("Dado um produto novo, quando inserir o produto, então ele deve ser salvo")
    void dadoProdutoNovo_quandoInserirProduto_entaoDeveSerSalvo() {
        Produto produto = DataPoolProduto.produtoNovo();
        when(produtoGateway.buscaProdutoPorNome(produto.getNome())).thenReturn(Optional.empty());
        when(produtoGateway.salvaProduto(produto)).thenReturn(produto);

        Produto resultado = produtoInsereUseCase.inserir(produto);

        assertEquals(produto.getNome(), resultado.getNome());
        verify(produtoGateway).buscaProdutoPorNome(produto.getNome());
        verify(produtoGateway).salvaProduto(produto);
    }

    @Test
    @DisplayName("Dado um produto existente, quando inserir o produto, então deve lançar uma exceção")
    void dadoProdutoExistente_quandoInserirProduto_entaoDeveLancarExcecao() {
        Produto produto = DataPoolProduto.produtoNovo();
        when(produtoGateway.buscaProdutoPorNome(produto.getNome())).thenReturn(Optional.of(produto));

        assertThrows(ProdutoExistenteExcecao.class, () -> produtoInsereUseCase.inserir(produto));
        verify(produtoGateway).buscaProdutoPorNome(produto.getNome());
        verify(produtoGateway, never()).salvaProduto(produto);
    }
}
