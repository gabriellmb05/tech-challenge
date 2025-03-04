package br.com.on.fiap.hexagono.casodeuso.pedido;

import br.com.on.fiap.hexagono.adaptadores.gateways.PedidoGateway;
import br.com.on.fiap.hexagono.casodeuso.pedido.entrada.DetalhaPedidoCasoDeUso;
import br.com.on.fiap.hexagono.entidades.Pedido;
import br.com.on.fiap.hexagono.excecao.PedidoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;

public class DetalhaPedidoCasoDeUsoImpl implements DetalhaPedidoCasoDeUso {

    private final PedidoGateway pedidoGateway;

    public DetalhaPedidoCasoDeUsoImpl(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public Pedido detalhaPedido(String protocolo) {
        return pedidoGateway
                .detalhaPedido(protocolo)
                .orElseThrow(() -> new PedidoNaoEncontradoExcecao(
                        MessageError.MSG_ERRO_PEDIDO_NAO_ENCONTRADO_PARA_PROTOCOLO.getMensagem(), protocolo));
    }
}
