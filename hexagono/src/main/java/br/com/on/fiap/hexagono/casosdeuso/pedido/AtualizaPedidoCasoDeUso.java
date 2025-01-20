package br.com.on.fiap.hexagono.casosdeuso.pedido;

import br.com.on.fiap.hexagono.dominio.Pedido;
import br.com.on.fiap.hexagono.excecao.PedidoNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
import br.com.on.fiap.hexagono.portas.entrada.pedido.AtualizaPedidoPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.pedido.AtualizaPedidoPortaSaida;

public class AtualizaPedidoCasoDeUso implements AtualizaPedidoPortaEntrada {

    private final AtualizaPedidoPortaSaida atualizaPedidoPortaSaida;

    public AtualizaPedidoCasoDeUso(AtualizaPedidoPortaSaida atualizaPedidoPortaSaida) {
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
