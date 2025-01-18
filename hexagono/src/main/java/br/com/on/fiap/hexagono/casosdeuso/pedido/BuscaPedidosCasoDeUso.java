package br.com.on.fiap.hexagono.casosdeuso.pedido;

import br.com.on.fiap.hexagono.dominio.Pedido;
import br.com.on.fiap.hexagono.dominio.PedidoFiltro;
import br.com.on.fiap.hexagono.portas.entrada.pedido.BuscaPedidosPortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.pedido.BuscaPedidosPortaSaida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class BuscaPedidosCasoDeUso implements BuscaPedidosPortaEntrada {

    private final BuscaPedidosPortaSaida listarPedidoPortaSaida;

    public BuscaPedidosCasoDeUso(BuscaPedidosPortaSaida listarPedidoPortaSaida) {
        this.listarPedidoPortaSaida = listarPedidoPortaSaida;
    }

    @Override
    public Page<Pedido> buscarPedidosComFiltro(PedidoFiltro filtro, Pageable pageable) {
        return listarPedidoPortaSaida.listarComFiltros(filtro, pageable);
    }
}
