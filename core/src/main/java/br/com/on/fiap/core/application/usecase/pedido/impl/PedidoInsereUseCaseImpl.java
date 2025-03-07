package br.com.on.fiap.core.application.usecase.pedido.impl;

import br.com.on.fiap.core.application.dto.entrada.PedidoEntrada;
import br.com.on.fiap.core.application.usecase.cliente.ClienteBuscaPorIdUseCase;
import br.com.on.fiap.core.application.usecase.pagamento.PagamentoCriaUseCase;
import br.com.on.fiap.core.application.usecase.pedido.*;
import br.com.on.fiap.core.domain.*;
import java.util.Map;

public class PedidoInsereUseCaseImpl implements PedidoInsereUseCase {

    private final ClienteBuscaPorIdUseCase clienteBuscaPorIdUseCase;
    private final PedidoValidaProdutoUseCase pedidoValidaProdutoUseCase;
    private final PedidoCriaUseCase pedidoCriaUseCase;
    private final PagamentoCriaUseCase pagamentoCriaUseCase;
    private final PedidoProdutoCriaRelacionamentoUseCase pedidoProdutoCriaRelacionamentoUseCase;
    private final PedidoSalvaUseCase pedidoSalvaUseCase;

    public PedidoInsereUseCaseImpl(
            ClienteBuscaPorIdUseCase clienteBuscaPorIdUseCase,
            PedidoValidaProdutoUseCase pedidoValidaProdutoUseCase,
            PedidoCriaUseCase pedidoCriaUseCase,
            PagamentoCriaUseCase pagamentoCriaUseCase,
            PedidoProdutoCriaRelacionamentoUseCase pedidoProdutoCriaRelacionamentoUseCase,
            PedidoSalvaUseCase pedidoSalvaUseCase) {
        this.clienteBuscaPorIdUseCase = clienteBuscaPorIdUseCase;
        this.pedidoValidaProdutoUseCase = pedidoValidaProdutoUseCase;
        this.pedidoCriaUseCase = pedidoCriaUseCase;
        this.pagamentoCriaUseCase = pagamentoCriaUseCase;
        this.pedidoProdutoCriaRelacionamentoUseCase = pedidoProdutoCriaRelacionamentoUseCase;
        this.pedidoSalvaUseCase = pedidoSalvaUseCase;
    }

    @Override
    public Pedido inserePedido(PedidoEntrada pedidoEntrada) {
        Cliente cliente = clienteBuscaPorIdUseCase.buscar(pedidoEntrada.getCliente());

        Map<Produto, Long> produtosValidados = pedidoValidaProdutoUseCase.validarProdutos(pedidoEntrada.getProdutos());

        Pagamento pagamento = pagamentoCriaUseCase.criarPagamento(
                pedidoEntrada.getPagamento(), SituacaoPagamento.PENDENTE, produtosValidados);
        Pedido pedidoSolicitante = pedidoCriaUseCase.criaPedido(pedidoEntrada, cliente, pagamento);
        pedidoProdutoCriaRelacionamentoUseCase.criaRelacionamentoProdutoPedido(pedidoSolicitante, produtosValidados);
        return pedidoSalvaUseCase.salvarPedido(pedidoSolicitante);
    }
}
