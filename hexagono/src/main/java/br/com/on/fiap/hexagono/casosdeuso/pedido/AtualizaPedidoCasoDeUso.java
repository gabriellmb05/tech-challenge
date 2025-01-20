package br.com.on.fiap.hexagono.casosdeuso.pedido;

import br.com.on.fiap.hexagono.dominio.Pedido;
import br.com.on.fiap.hexagono.dominio.SituacaoPedido;
import br.com.on.fiap.hexagono.excecao.PedidoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
import br.com.on.fiap.hexagono.portas.entrada.pedido.AtualizaPedidoPortaEntrada;
import br.com.on.fiap.hexagono.portas.entrada.pedido.DetalhaPedidoPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.pedido.AtualizaPedidoPortaSaida;
import br.com.on.fiap.hexagono.portas.saida.pedido.PersistePedidoPagamentoPortaSaida;
import br.com.on.fiap.hexagono.portas.saida.pedido.PersistePedidoPortaSaida;

import java.util.Optional;

public class AtualizaPedidoCasoDeUso implements AtualizaPedidoPortaEntrada {

    private final AtualizaPedidoPortaSaida atualizaPedidoPortaSaida;
    private final DetalhaPedidoPortaEntrada detalhaPedidoPortaEntrada;

    public AtualizaPedidoCasoDeUso(AtualizaPedidoPortaSaida atualizaPedidoPortaSaida, DetalhaPedidoPortaEntrada detalhaPedidoPortaEntrada) {
        this.atualizaPedidoPortaSaida = atualizaPedidoPortaSaida;
        this.detalhaPedidoPortaEntrada = detalhaPedidoPortaEntrada;
    }

    @Override
    public Pedido atualizarPedido(String protocolo) {
        Pedido pedido = detalhaPedidoPortaEntrada.detalhaPedido(protocolo);
        SituacaoPedido situacaoAtual = pedido.getSituacao();
        SituacaoPedido proximaSituacao = SituacaoPedido.deCodigo(situacaoAtual.getOrdem() + 1);
        if (proximaSituacao != null) {
            pedido.setSituacao(proximaSituacao);
            atualizaPedidoPortaSaida.atualizaPedido(pedido);
        }
        return pedido;
    }
}
