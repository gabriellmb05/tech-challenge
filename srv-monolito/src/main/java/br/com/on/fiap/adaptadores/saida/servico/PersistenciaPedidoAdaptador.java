package br.com.on.fiap.adaptadores.saida.servico;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.PedidoEntidade;
import br.com.on.fiap.adaptadores.saida.persistencia.mapeador.PedidoSaidaMapeador;
import br.com.on.fiap.adaptadores.saida.persistencia.repositorio.PedidoRepositorio;
import br.com.on.fiap.hexagono.dominio.Pedido;
import br.com.on.fiap.hexagono.portas.saida.pedido.PersistePedidoPortaSaida;
import org.springframework.stereotype.Service;

@Service
public class PersistenciaPedidoAdaptador implements PersistePedidoPortaSaida {

    private final PedidoRepositorio pedidoRepositorio;
    private final PedidoSaidaMapeador pedidoSaidaMapeador;

    public PersistenciaPedidoAdaptador(PedidoRepositorio pedidoRepositorio, PedidoSaidaMapeador pedidoSaidaMapeador) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.pedidoSaidaMapeador = pedidoSaidaMapeador;
    }

    @Override
    public Pedido salvaPedido(Pedido pedido) {
        PedidoEntidade pedidoEntidade = pedidoSaidaMapeador.paraEntidade(pedido);
        pedidoEntidade.setSituacao(1L);
        return pedidoSaidaMapeador.paraPedido(pedidoRepositorio.save(pedidoEntidade));
    }
}
