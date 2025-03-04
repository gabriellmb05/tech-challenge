package br.com.on.fiap.adapter.output.datasource;

import br.com.on.fiap.adapter.output.persistence.specification.PedidoSpecification;
import br.com.on.fiap.adapter.output.persistence.mapper.PedidoProdutoSaidaMapeador;
import br.com.on.fiap.adapter.output.persistence.repository.PedidoProdutoRepository;
import br.com.on.fiap.adapter.output.persistence.repository.PedidoRepository;
import br.com.on.fiap.adapter.output.persistence.entity.PedidoEntity;
import br.com.on.fiap.adapter.output.persistence.entity.PedidoProdutoEntity;
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

    private final PedidoRepository pedidoRepository;
    private final PedidoProdutoRepository pedidoProdutoRepository;
    private final PedidoProdutoSaidaMapeador pedidoProdutoSaidaMapeador;

    public PedidoDataSourceImpl(
            PedidoRepository pedidoRepository,
            PedidoProdutoRepository pedidoProdutoRepository,
            PedidoProdutoSaidaMapeador pedidoProdutoSaidaMapeador) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoProdutoRepository = pedidoProdutoRepository;
        this.pedidoProdutoSaidaMapeador = pedidoProdutoSaidaMapeador;
    }

    @Override
    @Transactional
    public Optional<Pedido> atualizarPedido(String protocolo) {
        Optional<PedidoEntity> pedidoOptional = pedidoRepository.findByNmProtocolo(protocolo);

        if (pedidoOptional.isEmpty()) {
            return Optional.empty();
        }

        PedidoEntity pedido = pedidoOptional.get();

        SituacaoPedido situacaoAtual = pedido.getStPedido();
        SituacaoPedido situacaoProxima = SituacaoPedido.deCodigo(situacaoAtual.getOrdem() + 1);

        if (situacaoProxima != null) {
            pedido.setStPedido(situacaoProxima);
            pedido = pedidoRepository.save(pedido);
        }
        return Optional.ofNullable(pedidoProdutoSaidaMapeador.paraPedido(pedido));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Pedido> listarComFiltros(PedidoFiltro filtro, Pageable page) {
        return pedidoRepository
                .findAll(PedidoSpecification.filtroPorDataInicioEDataFim(filtro), page)
                .map(pedidoProdutoSaidaMapeador::paraPedido);
    }

    @Override
    public Optional<Pedido> detalhaPedido(String protocolo) {
        Optional<PedidoEntity> pedido = pedidoRepository.findByNmProtocolo(protocolo);
        return pedido.map(pedidoProdutoSaidaMapeador::paraPedidoComPagamento);
    }

    @Override
    public void salvaPedidoPagamento(Pedido pedido) {
        PedidoEntity pedidoComPagamento = pedidoProdutoSaidaMapeador.paraEntidadeComPagamento(pedido);
        pedidoRepository.save(pedidoComPagamento);
    }

    @Override
    @Transactional
    public Pedido salvaPedido(Pedido pedido) {
        PedidoEntity pedidoEntity = pedidoProdutoSaidaMapeador.paraEntidade(pedido);
        PedidoEntity pedidoSalvo = pedidoRepository.save(pedidoEntity);
        return pedidoProdutoSaidaMapeador.paraPedido(pedidoSalvo);
    }

    @Override
    public void vincularPedido(List<RelPedidoProduto> relPedidoProdutos) {
        List<PedidoProdutoEntity> pedidoProdutos = pedidoProdutoSaidaMapeador.paraListaEntidade(relPedidoProdutos);
        pedidoProdutoRepository.saveAll(pedidoProdutos);
    }
}
