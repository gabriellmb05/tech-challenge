package br.com.on.fiap.hexagono.usecases.casodeuso.pedido;

import br.com.on.fiap.hexagono.entities.entidades.Pedido;
import br.com.on.fiap.hexagono.usecases.excecao.PedidoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.usecases.excecao.message.MessageError;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.pedido.AtualizaPedidoCasoDeUso;
import br.com.on.fiap.hexagono.usecases.interfaces.saida.pedido.AtualizaPedidoPortaSaida;

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
