package br.com.on.fiap.hexagono.casosdeuso.produto;

import br.com.on.fiap.hexagono.dominio.Pedido;
import br.com.on.fiap.hexagono.dominio.Produto;
import br.com.on.fiap.hexagono.dominio.RelPedidoProduto;
import br.com.on.fiap.hexagono.excecao.ProdutoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
import br.com.on.fiap.hexagono.portas.entrada.produto.ValidaProdutosDoPedidoPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.produto.PersisteProdutoPortaSaida;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidaProdutosDoPedidoCasoDeUso implements ValidaProdutosDoPedidoPortaEntrada {

    private final PersisteProdutoPortaSaida persisteProdutoPortaSaida;

    public ValidaProdutosDoPedidoCasoDeUso(PersisteProdutoPortaSaida persisteProdutoPortaSaida) {
        this.persisteProdutoPortaSaida = persisteProdutoPortaSaida;
    }

    @Override
    public Pedido validarProdutosDoPedido(Pedido pedido) {
        List<Long> idsProdutos = extrairIdsProdutos(pedido.getRelPedidoProdutos());
        List<Produto> produtosExistentes = buscarProdutosExistentes(idsProdutos);
        List<Produto> produtosValidados = validarSeTodosProdutosExistem(idsProdutos, produtosExistentes);
        atualizarRelacaoPedidoProduto(pedido, produtosValidados);
        return pedido;
    }

    private void atualizarRelacaoPedidoProduto(Pedido pedido, List<Produto> produtosValidados) {
        pedido.getRelPedidoProdutos().forEach(relPedidoProduto -> produtosValidados.stream()
                .filter(produto ->
                        produto.getId().equals(relPedidoProduto.getProduto().getId()))
                .findFirst()
                .ifPresentOrElse(relPedidoProduto::setProduto, () -> {}));
    }

    private List<Long> extrairIdsProdutos(List<RelPedidoProduto> produtos) {
        return produtos.stream()
                .map(RelPedidoProduto::getProduto)
                .map(Produto::getId)
                .toList();
    }

    private List<Produto> buscarProdutosExistentes(List<Long> idsProdutos) {
        return persisteProdutoPortaSaida.buscaProdutoPorIdsLista(idsProdutos);
    }

    private List<Produto> validarSeTodosProdutosExistem(List<Long> idsProdutos, List<Produto> produtosExistentes) {
        Set<Long> idsProdutosExistentes =
                produtosExistentes.stream().map(Produto::getId).collect(Collectors.toSet());

        List<Long> idsFaltantes = idsProdutos.stream()
                .filter(id -> !idsProdutosExistentes.contains(id))
                .toList();

        if (!idsFaltantes.isEmpty()) {
            throw new ProdutoNaoEncontradoExcecao(
                    MessageError.MSG_PRODUTOS_NAO_ENCONTRADOS.getMensagem(),
                    idsFaltantes.stream().toList());
        }
        return produtosExistentes;
    }
}
