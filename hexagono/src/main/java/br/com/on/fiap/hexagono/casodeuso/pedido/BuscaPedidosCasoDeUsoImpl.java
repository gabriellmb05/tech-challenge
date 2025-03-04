package br.com.on.fiap.hexagono.casodeuso.pedido;

import br.com.on.fiap.hexagono.casodeuso.pedido.entrada.BuscaPedidosCasoDeUso;
import br.com.on.fiap.hexagono.casodeuso.pedido.saida.BuscaPedidosPortaSaida;
import br.com.on.fiap.hexagono.entidades.Pedido;
import br.com.on.fiap.hexagono.entidades.PedidoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class BuscaPedidosCasoDeUsoImpl implements BuscaPedidosCasoDeUso {

    private final BuscaPedidosPortaSaida listarPedidoPortaSaida;

    public BuscaPedidosCasoDeUsoImpl(BuscaPedidosPortaSaida listarPedidoPortaSaida) {
        this.listarPedidoPortaSaida = listarPedidoPortaSaida;
    }

    @Override
    public Page<Pedido> buscarPedidosComFiltro(PedidoFiltro filtro, Pageable pageable) {
        return listarPedidoPortaSaida.listarComFiltros(filtro, pageable);
    }
}
