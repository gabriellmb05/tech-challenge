package br.com.on.fiap.adapter.output.db.specification;

import br.com.on.fiap.adapter.output.db.entity.PedidoEntity;
import jakarta.persistence.criteria.*;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class PedidoFluxoNovoSpecification {

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
        return builder.and(predicate, builder.notEqual(root.get("stPedido"), 5));
    }

    private static void adicionarOrdenacao(CriteriaQuery<?> query, CriteriaBuilder builder, Root<PedidoEntity> root) {
        List<Order> ordenacoes = List.of(
                builder.desc(builder.selectCase()
                        .when(builder.equal(root.get("stPedido"), "Pronto"), 1)
                        .when(builder.equal(root.get("stPedido"), "Em Preparação"), 2)
                        .when(builder.equal(root.get("stPedido"), "Recebido"), 3)
                        .otherwise(4)),
                builder.desc(root.get("dhPedido")));
        query.orderBy(ordenacoes);
    }

    //    REALIZADO(1, 1),
    //    APROVADO(2, 2),
    //    EM_PREPARACAO(3, 3),
    //    PRONTO(4, 4),
    //    FINALIZADO(5, 5);
}
