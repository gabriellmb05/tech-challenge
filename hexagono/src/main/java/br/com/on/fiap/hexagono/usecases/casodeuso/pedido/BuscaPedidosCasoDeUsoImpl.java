package br.com.on.fiap.hexagono.usecases.casodeuso.pedido;

import br.com.on.fiap.hexagono.entities.entidades.Pedido;
import br.com.on.fiap.hexagono.entities.entidades.PedidoFiltro;
import br.com.on.fiap.hexagono.usecases.interfaces.entrada.pedido.BuscaPedidosCasoDeUso;
import br.com.on.fiap.hexagono.usecases.interfaces.saida.pedido.BuscaPedidosPortaSaida;
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
