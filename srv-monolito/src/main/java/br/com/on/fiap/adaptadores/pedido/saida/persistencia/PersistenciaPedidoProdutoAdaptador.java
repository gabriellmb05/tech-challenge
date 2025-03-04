package br.com.on.fiap.adaptadores.pedido.saida.persistencia;

import br.com.on.fiap.adaptadores.pedido.saida.persistencia.mapeador.PedidoProdutoSaidaMapeador;
import br.com.on.fiap.adaptadores.pedido.saida.persistencia.repositorio.PedidoProdutoRepositorio;
import br.com.on.fiap.entidades.PedidoProdutoEntidade;
import br.com.on.fiap.hexagono.casodeuso.pedido.saida.PersistePedidoProdutoPortaSaida;
import br.com.on.fiap.hexagono.entidades.RelPedidoProduto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PersistenciaPedidoProdutoAdaptador implements PersistePedidoProdutoPortaSaida {

    private final PedidoProdutoRepositorio pedidoProdutoRepositorio;
    private final PedidoProdutoSaidaMapeador pedidoProdutoSaidaMapeador;

    public PersistenciaPedidoProdutoAdaptador(
            PedidoProdutoRepositorio pedidoProdutoRepositorio, PedidoProdutoSaidaMapeador pedidoProdutoSaidaMapeador) {
        this.pedidoProdutoRepositorio = pedidoProdutoRepositorio;
        this.pedidoProdutoSaidaMapeador = pedidoProdutoSaidaMapeador;
    }

    @Override
    public void vincularPedido(List<RelPedidoProduto> relPedidoProdutos) {
        List<PedidoProdutoEntidade> pedidoProdutos = pedidoProdutoSaidaMapeador.paraListaEntidade(relPedidoProdutos);
        pedidoProdutoRepositorio.saveAll(pedidoProdutos);
    }
}
