package br.com.on.fiap.core.application.usecase.pedido;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import br.com.on.fiap.core.application.dto.entrada.ProdutoQuantidadeEntrada;
import br.com.on.fiap.core.application.exception.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.core.application.gateway.ProdutoGateway;
import br.com.on.fiap.core.application.usecase.pedido.impl.PedidoValidaProdutoUseCaseImpl;
import br.com.on.fiap.core.domain.Produto;
import br.com.on.fiap.datapool.ProdutoDataPool;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PedidoValidaProdutoUseCaseImplTest {

    @Mock
    private ProdutoGateway produtoGateway;

    @InjectMocks
    private PedidoValidaProdutoUseCaseImpl pedidoValidaProdutoUseCase;

    @Test
    @DisplayName(
            "Dado uma lista de produtos solicitados, quando todos existirem, então os produtos devem ser validados e retornados com a quantidade")
    void dadoProdutosSolicitados_quandoTodosExistirem_entaoDevemSerValidadosComQuantidade() {
        when(produtoGateway.buscaProdutoPorIdsLista(any())).thenReturn(ProdutoDataPool.criarProdutosComIdsDinamicos(1));

        Map<Produto, Long> resultado = pedidoValidaProdutoUseCase.validarProdutos(
                Collections.singletonList(ProdutoQuantidadeEntrada.create(1L, 2L)));

        assertNotNull(resultado);
        verify(produtoGateway).buscaProdutoPorIdsLista(any());
    }

    @Test
    @DisplayName(
            "Dado uma lista de produtos solicitados, quando algum produto não existir, então deve lançar uma exceção")
    void dadoProdutosSolicitados_quandoAlgumNaoExistir_entaoDeveLancarExcecao() {
        when(produtoGateway.buscaProdutoPorIdsLista(Arrays.asList(1L, 3L)))
                .thenReturn(ProdutoDataPool.criarProdutosComIdsDinamicos(2));

        assertThrows(
                ProdutoNaoEncontradoExcecao.class,
                () -> pedidoValidaProdutoUseCase.validarProdutos(
                        List.of(ProdutoQuantidadeEntrada.create(1L, 2L), ProdutoQuantidadeEntrada.create(3L, 1L))));
    }
}
