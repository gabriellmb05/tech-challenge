package br.com.on.fiap.hexagono.casodeuso.pedido;

import br.com.on.fiap.hexagono.adaptadores.gateways.PedidoGateway;
import br.com.on.fiap.hexagono.casodeuso.pedido.entrada.AtualizaPedidoCasoDeUso;
import br.com.on.fiap.hexagono.entidades.Pedido;
import br.com.on.fiap.hexagono.excecao.PedidoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;

public class AtualizaPedidoCasoDeUsoImpl implements AtualizaPedidoCasoDeUso {

    private final PedidoGateway pedidoGateway;

    public AtualizaPedidoCasoDeUsoImpl(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public Pedido atualizarPedido(String protocolo) {
        return pedidoGateway
                .atualizarPedido(protocolo)
                .orElseThrow(() -> new PedidoNaoEncontradoExcecao(
                        MessageError.MSG_ERRO_PEDIDO_NAO_ENCONTRADO_PARA_PROTOCOLO.getMensagem(), protocolo));
    }
}
