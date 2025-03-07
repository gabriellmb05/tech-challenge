package br.com.on.fiap.core.application.usecase.pedido.impl;

import br.com.on.fiap.core.application.dto.entrada.produto.ProdutoQuantidadeEntrada;
import br.com.on.fiap.core.application.exception.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.core.application.exception.message.MessageError;
import br.com.on.fiap.core.application.gateway.ProdutoGateway;
import br.com.on.fiap.core.application.usecase.pedido.PedidoValidaProdutoUseCase;
import br.com.on.fiap.core.domain.Produto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PedidoValidaProdutoUseCaseImpl implements PedidoValidaProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    public PedidoValidaProdutoUseCaseImpl(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Override
    public Map<Produto, Long> validarProdutos(List<ProdutoQuantidadeEntrada> produtosSolicitados) {
        List<Long> idsProdutos = produtosSolicitados.stream()
                .map(ProdutoQuantidadeEntrada::getIdProduto)
                .toList();

        List<Produto> produtosExistentes = produtoGateway.buscaProdutoPorIdsLista(idsProdutos);

        if (produtosExistentes.size() != idsProdutos.size()) {
            throw new ProdutoNaoEncontradoExcecao(
                    MessageError.MSG_ERRO_PRODUTO_NAO_ENCONTRADO.getMensagem(), idsProdutos);
        }
        return produtosSolicitados.stream()
                .collect(Collectors.toMap(
                        solicitacao -> produtosExistentes.stream()
                                .filter(produto -> produto.getId().equals(solicitacao.getIdProduto()))
                                .findFirst()
                                .orElseThrow(() -> new ProdutoNaoEncontradoExcecao(
                                        MessageError.MSG_ERRO_PRODUTO_NAO_ENCONTRADO.getMensagem(),
                                        solicitacao.getIdProduto())),
                        ProdutoQuantidadeEntrada::getQuantidade));
    }
}
