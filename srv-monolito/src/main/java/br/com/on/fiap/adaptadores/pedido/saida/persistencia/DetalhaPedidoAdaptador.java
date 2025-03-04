package br.com.on.fiap.adaptadores.pedido.saida.persistencia;

import br.com.on.fiap.adaptadores.pedido.saida.persistencia.mapeador.PedidoProdutoSaidaMapeador;
import br.com.on.fiap.adaptadores.pedido.saida.persistencia.repositorio.PedidoRepositorio;
import br.com.on.fiap.entidades.PedidoEntidade;
import br.com.on.fiap.hexagono.casodeuso.pedido.saida.DetalhaPedidoPortaSaida;
import br.com.on.fiap.hexagono.entidades.Pedido;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class DetalhaPedidoAdaptador implements DetalhaPedidoPortaSaida {

    private final PedidoRepositorio pedidoRepositorio;
    private final PedidoProdutoSaidaMapeador pedidoProdutoSaidaMapeador;

    public DetalhaPedidoAdaptador(
            PedidoRepositorio pedidoRepositorio, PedidoProdutoSaidaMapeador pedidoProdutoSaidaMapeador) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.pedidoProdutoSaidaMapeador = pedidoProdutoSaidaMapeador;
    }

    @Override
    public Optional<Pedido> detalhaPedido(String protocolo) {
        Optional<PedidoEntidade> pedido = pedidoRepositorio.findByNmProtocolo(protocolo);
        return pedido.map(pedidoProdutoSaidaMapeador::paraPedidoComPagamento);
    }
}
