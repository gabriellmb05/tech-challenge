package br.com.on.fiap.core.application.usecase.produto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import br.com.on.fiap.core.application.dto.entrada.ProdutoEntrada;
import br.com.on.fiap.core.application.exception.ProdutoExistenteExcecao;
import br.com.on.fiap.core.application.gateway.ProdutoGateway;
import br.com.on.fiap.core.application.usecase.produto.impl.ProdutoInsereUseCaseImpl;
import br.com.on.fiap.core.domain.Produto;
import br.com.on.fiap.datapool.ProdutoDataPool;
import br.com.on.fiap.datapool.ProdutoEntradaDataPool;
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
        Produto produto = ProdutoDataPool.criarProdutoNovo();
        ProdutoEntrada produtoEntrada = ProdutoEntradaDataPool.criarProdutoEntrada(
                produto.getNome(), produto.getCategoria().name(), produto.getPreco());

        when(produtoGateway.buscaProdutoPorNome(produtoEntrada.getNome())).thenReturn(Optional.empty());
        when(produtoGateway.salvaProduto(any(Produto.class))).thenReturn(produto);

        Produto resultado = produtoInsereUseCase.inserir(produtoEntrada);

        assertEquals(produto.getNome(), resultado.getNome());
        verify(produtoGateway).buscaProdutoPorNome(produto.getNome());
        verify(produtoGateway).salvaProduto(any(Produto.class));
    }

    @Test
    @DisplayName("Dado um produto existente, quando inserir o produto, então deve lançar uma exceção")
    void dadoProdutoExistente_quandoInserirProduto_entaoDeveLancarExcecao() {
        Produto produto = ProdutoDataPool.criarProdutoNovo();
        ProdutoEntrada produtoEntrada = ProdutoEntradaDataPool.criarProdutoEntrada(
                produto.getNome(), produto.getCategoria().name(), produto.getPreco());
        when(produtoGateway.buscaProdutoPorNome(produtoEntrada.getNome())).thenReturn(Optional.of(produto));

        assertThrows(ProdutoExistenteExcecao.class, () -> produtoInsereUseCase.inserir(produtoEntrada));
        verify(produtoGateway).buscaProdutoPorNome(produto.getNome());
        verify(produtoGateway, never()).salvaProduto(produto);
    }
}
