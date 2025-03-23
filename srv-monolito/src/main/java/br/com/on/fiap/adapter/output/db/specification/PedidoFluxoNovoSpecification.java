package br.com.on.fiap.adapter.output.db.specification;

import br.com.on.fiap.adapter.output.db.entity.PedidoEntity;
import jakarta.persistence.criteria.*;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class PedidoFluxoNovoSpecification {

    public static final String ST_PEDIDO = "stPedido";

    private PedidoFluxoNovoSpecification() {}

    public static Specification<PedidoEntity> filtrar() {
        return (Root<PedidoEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Predicate predicate = builder.conjunction();

            predicate = excluirPedidosFinalizados(root, builder, predicate);
            adicionarOrdenacao(query, builder, root);

            return predicate;
        };
    }

    private static Predicate excluirPedidosFinalizados(
            Root<PedidoEntity> root, CriteriaBuilder builder, Predicate predicate) {
        return builder.and(predicate, builder.notEqual(root.get(ST_PEDIDO), 5));
    }

    private static void adicionarOrdenacao(CriteriaQuery<?> query, CriteriaBuilder builder, Root<PedidoEntity> root) {
        List<Order> ordenacoes = List.of(
                builder.desc(builder.selectCase()
                        .when(builder.equal(root.get(ST_PEDIDO), 4), 4)
                        .when(builder.equal(root.get(ST_PEDIDO), 3), 3)
                        .when(builder.equal(root.get(ST_PEDIDO), 2), 2)
                        .when(builder.equal(root.get(ST_PEDIDO), 1), 1)
                        .otherwise(4)),
                builder.asc(root.get("dhAtualizacaoPedido")));
        query.orderBy(ordenacoes);
    }
}
