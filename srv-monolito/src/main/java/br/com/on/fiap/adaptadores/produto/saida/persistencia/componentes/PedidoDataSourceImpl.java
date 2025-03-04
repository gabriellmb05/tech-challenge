package br.com.on.fiap.adaptadores.produto.saida.persistencia.componentes;

import br.com.on.fiap.adaptadores.pedido.saida.persistencia.especificacao.PedidoEspecificacao;
import br.com.on.fiap.adaptadores.pedido.saida.persistencia.mapeador.PedidoProdutoSaidaMapeador;
import br.com.on.fiap.adaptadores.pedido.saida.persistencia.repositorio.PedidoProdutoRepositorio;
import br.com.on.fiap.adaptadores.pedido.saida.persistencia.repositorio.PedidoRepositorio;
import br.com.on.fiap.entidade.PedidoEntidade;
import br.com.on.fiap.entidade.PedidoProdutoEntidade;
import br.com.on.fiap.hexagono.adapter.datasource.PedidoDataSource;
import br.com.on.fiap.hexagono.domain.entity.Pedido;
import br.com.on.fiap.hexagono.domain.entity.PedidoFiltro;
import br.com.on.fiap.hexagono.domain.entity.RelPedidoProduto;
import br.com.on.fiap.hexagono.domain.entity.SituacaoPedido;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PedidoDataSourceImpl implements PedidoDataSource {

    private final PedidoRepositorio pedidoRepositorio;
    private final PedidoProdutoRepositorio pedidoProdutoRepositorio;
    private final PedidoProdutoSaidaMapeador pedidoProdutoSaidaMapeador;

    public PedidoDataSourceImpl(
            PedidoRepositorio pedidoRepositorio,
            PedidoProdutoRepositorio pedidoProdutoRepositorio,
            PedidoProdutoSaidaMapeador pedidoProdutoSaidaMapeador) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.pedidoProdutoRepositorio = pedidoProdutoRepositorio;
        this.pedidoProdutoSaidaMapeador = pedidoProdutoSaidaMapeador;
    }

    @Override
    @Transactional
    public Optional<Pedido> atualizarPedido(String protocolo) {
        Optional<PedidoEntidade> pedidoOptional = pedidoRepositorio.findByNmProtocolo(protocolo);

        if (pedidoOptional.isEmpty()) {
            return Optional.empty();
        }

        PedidoEntidade pedido = pedidoOptional.get();

        SituacaoPedido situacaoAtual = pedido.getStPedido();
        SituacaoPedido situacaoProxima = SituacaoPedido.deCodigo(situacaoAtual.getOrdem() + 1);

        if (situacaoProxima != null) {
            pedido.setStPedido(situacaoProxima);
            pedido = pedidoRepositorio.save(pedido);
        }
        return Optional.ofNullable(pedidoProdutoSaidaMapeador.paraPedido(pedido));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Pedido> listarComFiltros(PedidoFiltro filtro, Pageable page) {
        return pedidoRepositorio
                .findAll(PedidoEspecificacao.filtroPorDataInicioEDataFim(filtro), page)
                .map(pedidoProdutoSaidaMapeador::paraPedido);
    }

    @Override
    public Optional<Pedido> detalhaPedido(String protocolo) {
        Optional<PedidoEntidade> pedido = pedidoRepositorio.findByNmProtocolo(protocolo);
        return pedido.map(pedidoProdutoSaidaMapeador::paraPedidoComPagamento);
    }

    @Override
    public void salvaPedidoPagamento(Pedido pedido) {
        PedidoEntidade pedidoComPagamento = pedidoProdutoSaidaMapeador.paraEntidadeComPagamento(pedido);
        pedidoRepositorio.save(pedidoComPagamento);
    }

    @Override
    @Transactional
    public Pedido salvaPedido(Pedido pedido) {
        PedidoEntidade pedidoEntidade = pedidoProdutoSaidaMapeador.paraEntidade(pedido);
        PedidoEntidade pedidoSalvo = pedidoRepositorio.save(pedidoEntidade);
        return pedidoProdutoSaidaMapeador.paraPedido(pedidoSalvo);
    }

    @Override
    public void vincularPedido(List<RelPedidoProduto> relPedidoProdutos) {
        List<PedidoProdutoEntidade> pedidoProdutos = pedidoProdutoSaidaMapeador.paraListaEntidade(relPedidoProdutos);
        pedidoProdutoRepositorio.saveAll(pedidoProdutos);
    }
}
