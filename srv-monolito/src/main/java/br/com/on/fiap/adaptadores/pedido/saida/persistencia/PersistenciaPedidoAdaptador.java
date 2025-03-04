package br.com.on.fiap.adaptadores.pedido.saida.persistencia;

import br.com.on.fiap.adaptadores.pedido.saida.persistencia.mapeador.PedidoProdutoSaidaMapeador;
import br.com.on.fiap.adaptadores.pedido.saida.persistencia.repositorio.PedidoRepositorio;
import br.com.on.fiap.entidades.PedidoEntidade;
import br.com.on.fiap.hexagono.casodeuso.pedido.saida.PersistePedidoPagamentoPortaSaida;
import br.com.on.fiap.hexagono.casodeuso.pedido.saida.PersistePedidoPortaSaida;
import br.com.on.fiap.hexagono.entidades.Pedido;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersistenciaPedidoAdaptador implements PersistePedidoPortaSaida, PersistePedidoPagamentoPortaSaida {

    private final PedidoRepositorio pedidoRepositorio;
    private final PedidoProdutoSaidaMapeador pedidoProdutoSaidaMapeador;

    public PersistenciaPedidoAdaptador(
            PedidoRepositorio pedidoRepositorio, PedidoProdutoSaidaMapeador pedidoProdutoSaidaMapeador) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.pedidoProdutoSaidaMapeador = pedidoProdutoSaidaMapeador;
    }

    @Override
    @Transactional
    public Pedido salvaPedido(Pedido pedido) {
        PedidoEntidade pedidoEntidade = pedidoProdutoSaidaMapeador.paraEntidade(pedido);
        PedidoEntidade pedidoSalvo = pedidoRepositorio.save(pedidoEntidade);
        return pedidoProdutoSaidaMapeador.paraPedido(pedidoSalvo);
    }

    @Override
    public void salvaPedidoPagamento(Pedido pedido) {
        PedidoEntidade pedidoComPagamento = pedidoProdutoSaidaMapeador.paraEntidadeComPagamento(pedido);
        pedidoRepositorio.save(pedidoComPagamento);
    }
}
