package br.com.on.fiap.core.usecase.produto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.on.fiap.core.adapter.gateway.ProdutoGateway;
import br.com.on.fiap.core.application.usecase.produto.impl.ProdutoValidaPedidoUseCaseImpl;
import br.com.on.fiap.core.datapool.DataPoolPedido;
import br.com.on.fiap.core.datapool.DataPoolProduto;
import br.com.on.fiap.core.domain.entity.Pedido;
import br.com.on.fiap.core.domain.exception.ProdutoNaoEncontradoExcecao;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProdutoValidaPedidoUseCaseImplTest {

    @Mock
    private ProdutoGateway produtoGateway;

    @InjectMocks
    private ProdutoValidaPedidoUseCaseImpl produtoValidaPedidoUseCase;

    @Test
    @DisplayName(
            "Dado um pedido com produtos válidos, quando validar, então os produtos devem ser validados corretamente")
    void dadoPedidoComProdutosValidos_quandoValidarProdutos_entaoDeveSerValido() {
        Pedido pedido = DataPoolPedido.pedidoComProdutosValidos();

        when(produtoGateway.buscaProdutoPorIdsLista(List.of(1L, 2L)))
                .thenReturn(DataPoolProduto.produtosComIdsDinamicos(2));

        Pedido resultado = produtoValidaPedidoUseCase.validarProdutosDoPedido(pedido);

        assertEquals(pedido, resultado);
        verify(produtoGateway).buscaProdutoPorIdsLista(List.of(1L, 2L));
    }

    @Test
    @DisplayName(
            "Dado um pedido com produtos inválidos, quando validar, então deve lançar exceção de Os seguintes produtos informados para o pedido, não existem no estoque: [1]")
    void dadoPedidoComProdutosInvalidos_quandoValidarProdutos_entaoDeveLancarExcecaoProdutoNaoEncontrado() {
        Pedido pedido = DataPoolPedido.pedidoComProdutosInvalidos();

        when(produtoGateway.buscaProdutoPorIdsLista(List.of(1L))).thenReturn(List.of());

        ProdutoNaoEncontradoExcecao exception = assertThrows(
                ProdutoNaoEncontradoExcecao.class, () -> produtoValidaPedidoUseCase.validarProdutosDoPedido(pedido));

        assertEquals(
                "Os seguintes produtos informados para o pedido, não existem no estoque: [1]", exception.getMessage());
        verify(produtoGateway).buscaProdutoPorIdsLista(List.of(1L));
    }
}
