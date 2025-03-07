package br.com.on.fiap.adapter.output.db.specification;

import br.com.on.fiap.adapter.output.db.entity.ClienteEntity;
import br.com.on.fiap.adapter.output.db.entity.PedidoEntity;
import br.com.on.fiap.core.application.dto.filtro.PedidoFiltroEntrada;
import jakarta.persistence.criteria.*;
import java.time.LocalDateTime;
import org.springframework.data.jpa.domain.Specification;

public class PedidoSpecification {

    private PedidoSpecification() {}

    public static Specification<PedidoEntity> filtroPorDataInicioEDataFim(PedidoFiltroEntrada filtro) {
        return (Root<PedidoEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Predicate predicate = builder.conjunction();

            predicate = adicionarFiltroDataInicio(filtro, root, builder, predicate);
            predicate = adicionarFiltroDataFim(filtro, root, builder, predicate);
            predicate = adicionarFiltroSituacao(filtro, root, builder, predicate);
            predicate = adicionarFiltroCpfCliente(filtro, root, builder, predicate);
            return predicate;
        };
    }

    private static Predicate adicionarFiltroDataInicio(
            PedidoFiltroEntrada filtro, Root<PedidoEntity> root, CriteriaBuilder builder, Predicate predicate) {
        if (filtro.getDataInicio() != null) {
            LocalDateTime dataInicio = filtro.getDataInicio().atStartOfDay();
            return builder.and(predicate, builder.greaterThanOrEqualTo(root.get("dhPedido"), dataInicio));
        }
        return predicate;
    }

    private static Predicate adicionarFiltroDataFim(
            PedidoFiltroEntrada filtro, Root<PedidoEntity> root, CriteriaBuilder builder, Predicate predicate) {
        if (filtro.getDataFim() != null) {
            LocalDateTime dataFim = filtro.getDataFim().plusDays(1).atStartOfDay();
            return builder.and(predicate, builder.lessThan(root.get("dhPedido"), dataFim));
        }
        return predicate;
    }

    private static Predicate adicionarFiltroSituacao(
            PedidoFiltroEntrada filtro, Root<PedidoEntity> root, CriteriaBuilder builder, Predicate predicate) {
        if (filtro.getSituacao() != null) {
            return builder.and(predicate, builder.equal(root.get("stPedido"), filtro.getSituacao()));
        }
        return predicate;
    }

    private static Predicate adicionarFiltroCpfCliente(
            PedidoFiltroEntrada filtro, Root<PedidoEntity> root, CriteriaBuilder builder, Predicate predicate) {
        if (filtro.getCpfCliente() != null && !filtro.getCpfCliente().isEmpty()) {
            Join<PedidoEntity, ClienteEntity> clienteJoin = root.join("cliId", JoinType.INNER);
            return builder.and(predicate, builder.equal(clienteJoin.get("nmCpf"), filtro.getCpfCliente()));
        }
        return predicate;
    }
}
