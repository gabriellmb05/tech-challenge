package br.com.on.fiap.adaptadores.saida.servico;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.PedidoEntidade;
import br.com.on.fiap.adaptadores.saida.persistencia.mapeador.PedidoProdutoSaidaMapeador;
import br.com.on.fiap.adaptadores.saida.persistencia.repositorio.PedidoRepositorio;
import br.com.on.fiap.hexagono.entities.entidades.Pedido;
import br.com.on.fiap.hexagono.usecases.interfaces.saida.pedido.DetalhaPedidoPortaSaida;
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
