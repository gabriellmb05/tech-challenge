package br.com.on.fiap.adapter.output.db.datasource;

import br.com.on.fiap.adapter.input.dto.resposta.PaginaRespostaInfo;
import br.com.on.fiap.adapter.output.db.component.PageableComponent;
import br.com.on.fiap.adapter.output.db.entity.PedidoEntity;
import br.com.on.fiap.adapter.output.db.mapper.PedidoMapper;
import br.com.on.fiap.adapter.output.db.repository.PedidoProdutoRepository;
import br.com.on.fiap.adapter.output.db.repository.PedidoRepository;
import br.com.on.fiap.adapter.output.db.specification.PedidoFluxoNovoSpecification;
import br.com.on.fiap.adapter.output.db.specification.PedidoSpecification;
import br.com.on.fiap.core.adapter.datasource.PedidoDataSource;
import br.com.on.fiap.core.application.dto.filtro.PedidoFiltroEntrada;
import br.com.on.fiap.core.application.dto.resposta.PaginaResposta;
import br.com.on.fiap.core.application.dto.resposta.PaginacaoResposta;
import br.com.on.fiap.core.domain.Pedido;
import br.com.on.fiap.core.domain.SituacaoPedido;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PedidoDataSourceImpl implements PedidoDataSource {

    private final PedidoRepository pedidoRepository;
    private final PedidoProdutoRepository pedidoProdutoRepository;
    private final PageableComponent pageableComponent;
    private final PedidoMapper pedidoMapper;

    public PedidoDataSourceImpl(
            PedidoRepository pedidoRepository,
            PedidoProdutoRepository pedidoProdutoRepository,
            PageableComponent pageableComponent,
            PedidoMapper pedidoMapper) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoProdutoRepository = pedidoProdutoRepository;
        this.pageableComponent = pageableComponent;
        this.pedidoMapper = pedidoMapper;
    }

    @Override
    @Transactional
    public Optional<Pedido> atualizarPedido(String protocolo) {
        return pedidoRepository.findByNmProtocolo(protocolo).map(pedido -> {
            SituacaoPedido situacaoProxima =
                    SituacaoPedido.deCodigo(pedido.getStPedido().getOrdem() + 1);
            if (situacaoProxima != null) {
                pedido.setStPedido(situacaoProxima);
                pedido.setDhAtualizacaoPedido(LocalDateTime.now());
                pedidoRepository.save(pedido);
            }
            return pedido.toDomain(null);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public PaginaResposta<Pedido> listarComFiltros(PedidoFiltroEntrada filtro, PaginacaoResposta paginacaoResposta) {
        Pageable pageable = pageableComponent.criarPageable(paginacaoResposta);
        Page<Pedido> pagePedido = pedidoRepository
                .findAll(PedidoSpecification.filtroPorDataInicioEDataFim(filtro), pageable)
                .map(pedidoMapper::toDomain);
        return PaginaRespostaInfo.create(pagePedido);
    }

    @Override
    @Transactional(readOnly = true)
    public PaginaResposta<Pedido> listarComFiltros(PaginacaoResposta paginacaoResposta) {
        Pageable pageable = pageableComponent.criarPageable(paginacaoResposta);
        Page<Pedido> pagePedido = pedidoRepository
                .findAll(PedidoFluxoNovoSpecification.filtrar(), pageable)
                .map(pedidoMapper::toDomain);
        return PaginaRespostaInfo.create(pagePedido);
    }

    @Override
    public Optional<Pedido> detalhaPedido(String protocolo) {
        return pedidoRepository.findByNmProtocolo(protocolo).map(pedidoMapper::toDomain);
    }

    @Override
    @Transactional
    public Pedido salvaPedido(Pedido pedido) {
        PedidoEntity pedidoEntity = pedidoMapper.toEntity(pedido);
        pedidoRepository.save(pedidoEntity);
        atualizarRelacaoPedidoProduto(pedidoEntity);
        pedidoProdutoRepository.saveAll(pedidoEntity.getRelPedPro());
        return pedidoMapper.toDomain(pedidoEntity);
    }

    private void atualizarRelacaoPedidoProduto(PedidoEntity pedidoEntity) {
        pedidoEntity.getRelPedPro().forEach(item -> {
            item.getId().setPedId(pedidoEntity.getPedId());
            item.setPedId(pedidoEntity);
        });
    }
}
