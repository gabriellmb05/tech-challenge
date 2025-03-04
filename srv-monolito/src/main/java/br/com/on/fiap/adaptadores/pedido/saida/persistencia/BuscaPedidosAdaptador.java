package br.com.on.fiap.adaptadores.pedido.saida.persistencia;

import br.com.on.fiap.adaptadores.pedido.saida.persistencia.especificacao.PedidoEspecificacao;
import br.com.on.fiap.adaptadores.pedido.saida.persistencia.mapeador.PedidoProdutoSaidaMapeador;
import br.com.on.fiap.adaptadores.pedido.saida.persistencia.repositorio.PedidoRepositorio;
import br.com.on.fiap.hexagono.casodeuso.pedido.saida.BuscaPedidosPortaSaida;
import br.com.on.fiap.hexagono.entidades.Pedido;
import br.com.on.fiap.hexagono.entidades.PedidoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BuscaPedidosAdaptador implements BuscaPedidosPortaSaida {

    private final PedidoRepositorio pedidoRepositorio;
    private final PedidoProdutoSaidaMapeador pedidoProdutoSaidaMapeador;

    public BuscaPedidosAdaptador(
            PedidoRepositorio pedidoRepositorio, PedidoProdutoSaidaMapeador pedidoProdutoSaidaMapeador) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.pedidoProdutoSaidaMapeador = pedidoProdutoSaidaMapeador;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Pedido> listarComFiltros(PedidoFiltro filtro, Pageable page) {
        return pedidoRepositorio
                .findAll(PedidoEspecificacao.filtroPorDataInicioEDataFim(filtro), page)
                .map(pedidoProdutoSaidaMapeador::paraPedido);
    }
}
