package br.com.on.fiap.hexagono.casodeuso.pedido;

import br.com.on.fiap.hexagono.casodeuso.pedido.entrada.AtualizaPedidoCasoDeUso;
import br.com.on.fiap.hexagono.casodeuso.pedido.saida.AtualizaPedidoPortaSaida;
import br.com.on.fiap.hexagono.entidades.Pedido;
import br.com.on.fiap.hexagono.excecao.PedidoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;

public class AtualizaPedidoCasoDeUsoImpl implements AtualizaPedidoCasoDeUso {

    private final AtualizaPedidoPortaSaida atualizaPedidoPortaSaida;

    public AtualizaPedidoCasoDeUsoImpl(AtualizaPedidoPortaSaida atualizaPedidoPortaSaida) {
        this.atualizaPedidoPortaSaida = atualizaPedidoPortaSaida;
    }

    @Override
    public Pedido atualizarPedido(String protocolo) {
        return atualizaPedidoPortaSaida
                .atualizarPedido(protocolo)
                .orElseThrow(() -> new PedidoNaoEncontradoExcecao(
                        MessageError.MSG_ERRO_PEDIDO_NAO_ENCONTRADO_PARA_PROTOCOLO.getMensagem(), protocolo));
    }
}
