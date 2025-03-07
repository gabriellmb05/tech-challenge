package br.com.on.fiap.core.application.usecase.produto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.on.fiap.core.application.dto.entrada.produto.ProdutoEntrada;
import br.com.on.fiap.core.application.exception.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.core.application.usecase.produto.impl.ProdutoAlteraUseCaseImpl;
import br.com.on.fiap.core.domain.Produto;
import br.com.on.fiap.datapool.DataPoolProduto;
import br.com.on.fiap.datapool.DataPoolProdutoEntrada;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProdutoAlteraUseCaseImplTest {

    @Mock
    private ProdutoGateway produtoGateway;

    @InjectMocks
    private ProdutoAlteraUseCaseImpl produtoAlteraUseCase;

    @Test
    @DisplayName("Dado um produto existente, quando alterar o produto, então ele deve ser atualizado")
    void dadoProdutoExistente_quandoAlterarProduto_entaoDeveSerAtualizado() {
        Long id = 1L;
        Produto produto = DataPoolProduto.produtoExistente(id);
        ProdutoEntrada produtoEntrada = DataPoolProdutoEntrada.produtoExistente(id);

        Produto produtoAlterado = DataPoolProduto.produtoExistente(id);
        produtoAlterado.setNome("Produto Alterado");

        when(produtoGateway.buscaProdutoPorId(id)).thenReturn(Optional.of(produto));
        when(produtoGateway.salvaProduto(produto)).thenReturn(produtoAlterado);

        Produto resultado = produtoAlteraUseCase.alterar(id, produtoEntrada);

        assertAll(
                () -> assertEquals(id, resultado.getId()), () -> assertEquals("Produto Alterado", resultado.getNome()));
        verify(produtoGateway).buscaProdutoPorId(id);
        verify(produtoGateway).salvaProduto(produto);
    }

    @Test
    @DisplayName("Dado um produto não existente, quando alterar o produto, então deve lançar uma exceção")
    void dadoProdutoNaoExistente_quandoAlterarProduto_entaoDeveLancarExcecao() {
        Long id = 1L;
        Produto produto = DataPoolProduto.produtoExistente(id);
        ProdutoEntrada produtoEntrada = DataPoolProdutoEntrada.produtoExistente(id);

        when(produtoGateway.buscaProdutoPorId(id)).thenReturn(Optional.empty());

        assertThrows(ProdutoNaoEncontradoExcecao.class, () -> produtoAlteraUseCase.alterar(id, produtoEntrada));

        verify(produtoGateway).buscaProdutoPorId(id);
        verify(produtoGateway, never()).salvaProduto(produto);
    }
}
