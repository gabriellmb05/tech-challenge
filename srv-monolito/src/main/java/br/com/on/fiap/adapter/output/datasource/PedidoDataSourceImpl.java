package br.com.on.fiap.adapter.output.datasource;

import br.com.on.fiap.adapter.input.dto.response.PaginaInfo;
import br.com.on.fiap.adapter.output.persistence.component.PageableComponent;
import br.com.on.fiap.adapter.output.persistence.entity.PedidoEntity;
import br.com.on.fiap.adapter.output.persistence.entity.PedidoProdutoEntity;
import br.com.on.fiap.adapter.output.persistence.entity.ProdutoEntity;
import br.com.on.fiap.adapter.output.persistence.entity.rel.RelPedId;
import br.com.on.fiap.adapter.output.persistence.repository.PedidoProdutoRepository;
import br.com.on.fiap.adapter.output.persistence.repository.PedidoRepository;
import br.com.on.fiap.adapter.output.persistence.specification.PedidoSpecification;
import br.com.on.fiap.core.adapter.datasource.PedidoDataSource;
import br.com.on.fiap.core.application.dto.entrada.PedidoFiltroEntrada;
import br.com.on.fiap.core.application.dto.resposta.Pagina;
import br.com.on.fiap.core.application.dto.resposta.Paginacao;
import br.com.on.fiap.core.domain.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PedidoDataSourceImpl implements PedidoDataSource {

    private final PedidoRepository pedidoRepository;
    private final PedidoProdutoRepository pedidoProdutoRepository;
    private final PageableComponent pageableComponent;

    public PedidoDataSourceImpl(
            PedidoRepository pedidoRepository,
            PedidoProdutoRepository pedidoProdutoRepository,
            PageableComponent pageableComponent) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoProdutoRepository = pedidoProdutoRepository;
        this.pageableComponent = pageableComponent;
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
        return Optional.ofNullable(pedido.toDomain(null));
    }

    @Override
    @Transactional(readOnly = true)
    public Pagina<Pedido> listarComFiltros(PedidoFiltroEntrada filtro, Paginacao paginacao) {
        Pageable pageable = pageableComponent.criarPageable(paginacao);
        Page<Pedido> pagePedido = pedidoRepository
                .findAll(PedidoSpecification.filtroPorDataInicioEDataFim(filtro), pageable)
                .map(pedidoEntity -> pedidoEntity.toDomain(null));
        return PaginaInfo.create(pagePedido);
    }

    @Override
    public Optional<Pedido> detalhaPedido(String protocolo) {
        Optional<PedidoEntity> pedidoOp = pedidoRepository.findByNmProtocolo(protocolo);
        return pedidoOp.map(pedidoEntity -> pedidoEntity.toDomain(null));
    }

    @Override
    @Transactional
    public Pedido salvaPedido(Pedido pedido) {
        List<PedidoProdutoEntity> pedidoProdutoEntities = relacaoPedidoProduto(pedido.getRelPedidoProdutos());
        PedidoEntity pedidoEntity = PedidoEntity.create(pedido, pedidoProdutoEntities);
        pedidoRepository.save(pedidoEntity);

        pedidoEntity.getRelPedPro().forEach(item -> {
            item.getId().setPedId(pedidoEntity.getPedId());
            item.setPedId(pedidoEntity);
        });
        pedidoProdutoRepository.saveAll(pedidoEntity.getRelPedPro());
        return returnObj(pedidoEntity);
    }

    private List<PedidoProdutoEntity> relacaoPedidoProduto(List<PedidoProduto> pedidoProdutos) {

        Map<Produto, Long> collect = pedidoProdutos.stream()
                .collect(Collectors.toMap(PedidoProduto::getProduto, PedidoProduto::getQuantidade));

        return collect.entrySet().stream()
                .map(produtoQuantidade -> {
                    ProdutoEntity produto = ProdutoEntity.fromDomain(produtoQuantidade.getKey());
                    RelPedId relId = RelPedId.create(null, produto.getProId());
                    return PedidoProdutoEntity.create(relId, null, produto, produtoQuantidade.getValue());
                })
                .toList();
    }

    private Pedido returnObj(PedidoEntity pedidoEntity) {
        Map<ProdutoEntity, Long> collect = pedidoEntity.getRelPedPro().stream()
                .collect(Collectors.toMap(PedidoProdutoEntity::getProId, PedidoProdutoEntity::getQtPedido));

        List<PedidoProduto> pedidoProdutos = collect.entrySet().stream()
                .map(produtoQuantidade -> {
                    Produto produto = produtoQuantidade.getKey().toDomain();
                    return new PedidoProduto(produto, pedidoEntity.toDomain(null), produtoQuantidade.getValue());
                })
                .toList();

        return pedidoEntity.toDomain(pedidoProdutos);
    }
}
