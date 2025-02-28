package br.com.on.fiap.hexagono.usecases.casodeuso.pedido;

import br.com.on.fiap.hexagono.entities.entidades.Pedido;
import br.com.on.fiap.hexagono.usecases.excecao.PedidoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.usecases.excecao.message.MessageError;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.pedido.DetalhaPedidoCasoDeUso;
import br.com.on.fiap.hexagono.usecases.interfaces.saida.pedido.DetalhaPedidoPortaSaida;

public class DetalhaPedidoCasoDeUsoImpl implements DetalhaPedidoCasoDeUso {

    private final DetalhaPedidoPortaSaida detalhaPedidoPortaSaida;

    public DetalhaPedidoCasoDeUsoImpl(DetalhaPedidoPortaSaida detalhaPedidoPortaSaida) {
        this.detalhaPedidoPortaSaida = detalhaPedidoPortaSaida;
    }

    @Override
    public Pedido detalhaPedido(String protocolo) {
        return detalhaPedidoPortaSaida
                .detalhaPedido(protocolo)
                .orElseThrow(() -> new PedidoNaoEncontradoExcecao(
                        MessageError.MSG_ERRO_PEDIDO_NAO_ENCONTRADO_PARA_PROTOCOLO.getMensagem(), protocolo));
    }
}
