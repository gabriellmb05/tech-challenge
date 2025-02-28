package br.com.on.fiap.adaptadores.saida.servico;

import br.com.on.fiap.adaptadores.saida.persistencia.especificacao.PedidoEspecificacao;
import br.com.on.fiap.adaptadores.saida.persistencia.mapeador.PedidoProdutoSaidaMapeador;
import br.com.on.fiap.adaptadores.saida.persistencia.repositorio.PedidoRepositorio;
import br.com.on.fiap.hexagono.entities.entidades.Pedido;
import br.com.on.fiap.hexagono.entities.entidades.PedidoFiltro;
import br.com.on.fiap.hexagono.usecases.interfaces.saida.pedido.BuscaPedidosPortaSaida;
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
