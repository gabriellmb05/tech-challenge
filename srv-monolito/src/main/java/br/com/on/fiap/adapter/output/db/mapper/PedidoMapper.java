package br.com.on.fiap.adapter.output.db.mapper;

import br.com.on.fiap.adapter.output.db.entity.PedidoEntity;
import br.com.on.fiap.adapter.output.db.entity.PedidoProdutoEntity;
import br.com.on.fiap.adapter.output.db.entity.ProdutoEntity;
import br.com.on.fiap.adapter.output.db.entity.rel.RelPedId;
import br.com.on.fiap.core.domain.Pedido;
import br.com.on.fiap.core.domain.PedidoProduto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PedidoMapper {

    public Pedido toDomain(PedidoEntity pedidoEntity) {
        Map<ProdutoEntity, Long> produtoQuantidadeMap = pedidoEntity.getRelPedPro().stream()
                .collect(Collectors.toMap(PedidoProdutoEntity::getProId, PedidoProdutoEntity::getQtPedido));

        List<PedidoProduto> pedidoProdutos = produtoQuantidadeMap.entrySet().stream()
                .map(entry ->
                        new PedidoProduto(entry.getKey().toDomain(), pedidoEntity.toDomain(null), entry.getValue()))
                .collect(Collectors.toList());

        return pedidoEntity.toDomain(pedidoProdutos);
    }

    public PedidoEntity toEntity(Pedido pedido) {
        List<PedidoProdutoEntity> pedidoProdutoEntities = pedido.getRelPedidoProdutos().stream()
                .collect(Collectors.toMap(PedidoProduto::getProduto, PedidoProduto::getQuantidade))
                .entrySet()
                .stream()
                .map(entry -> {
                    ProdutoEntity produtoEntity = ProdutoEntity.fromDomain(entry.getKey());
                    return PedidoProdutoEntity.create(
                            new RelPedId(null, produtoEntity.getProId()), null, produtoEntity, entry.getValue());
                })
                .collect(Collectors.toList());

        return PedidoEntity.create(pedido, pedidoProdutoEntities);
    }
}
