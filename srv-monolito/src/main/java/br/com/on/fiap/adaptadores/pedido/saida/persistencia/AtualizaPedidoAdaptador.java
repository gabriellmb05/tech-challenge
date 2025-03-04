package br.com.on.fiap.adaptadores.pedido.saida.persistencia;

import br.com.on.fiap.adaptadores.pedido.saida.persistencia.mapeador.PedidoProdutoSaidaMapeador;
import br.com.on.fiap.adaptadores.pedido.saida.persistencia.repositorio.PedidoRepositorio;
import br.com.on.fiap.entidades.PedidoEntidade;
import br.com.on.fiap.hexagono.casodeuso.pedido.saida.AtualizaPedidoPortaSaida;
import br.com.on.fiap.hexagono.entidades.Pedido;
import br.com.on.fiap.hexagono.entidades.SituacaoPedido;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AtualizaPedidoAdaptador implements AtualizaPedidoPortaSaida {

    private final PedidoRepositorio pedidoRepositorio;
    private final PedidoProdutoSaidaMapeador pedidoProdutoSaidaMapeador;

    public AtualizaPedidoAdaptador(
            PedidoRepositorio pedidoRepositorio, PedidoProdutoSaidaMapeador pedidoProdutoSaidaMapeador) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.pedidoProdutoSaidaMapeador = pedidoProdutoSaidaMapeador;
    }

    @Override
    @Transactional
    public Optional<Pedido> atualizarPedido(String protocolo) {
        Optional<PedidoEntidade> pedidoOptional = pedidoRepositorio.findByNmProtocolo(protocolo);

        if (pedidoOptional.isEmpty()) {
            return Optional.empty();
        }

        PedidoEntidade pedido = pedidoOptional.get();

        SituacaoPedido situacaoAtual = pedido.getStPedido();
        SituacaoPedido situacaoProxima = SituacaoPedido.deCodigo(situacaoAtual.getOrdem() + 1);

        if (situacaoProxima != null) {
            pedido.setStPedido(situacaoProxima);
            pedido = pedidoRepositorio.save(pedido);
        }
        return Optional.ofNullable(pedidoProdutoSaidaMapeador.paraPedido(pedido));
    }
}
