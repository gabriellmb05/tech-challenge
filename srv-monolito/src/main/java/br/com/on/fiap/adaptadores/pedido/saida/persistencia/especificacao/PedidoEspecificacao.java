package br.com.on.fiap.adaptadores.pedido.saida.persistencia.especificacao;

import br.com.on.fiap.entidades.ClienteEntidade;
import br.com.on.fiap.entidades.PedidoEntidade;
import br.com.on.fiap.hexagono.entidades.PedidoFiltro;
import jakarta.persistence.criteria.*;
import java.time.LocalDateTime;
import org.springframework.data.jpa.domain.Specification;

public class PedidoEspecificacao {

    private PedidoEspecificacao() {}

    public static Specification<PedidoEntidade> filtroPorDataInicioEDataFim(PedidoFiltro filtro) {
        return (Root<PedidoEntidade> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Predicate predicate = builder.conjunction();

            predicate = adicionarFiltroDataInicio(filtro, root, builder, predicate);
            predicate = adicionarFiltroDataFim(filtro, root, builder, predicate);
            predicate = adicionarFiltroSituacao(filtro, root, builder, predicate);
            predicate = adicionarFiltroCpfCliente(filtro, root, builder, predicate);
            return predicate;
        };
    }

    private static Predicate adicionarFiltroDataInicio(
            PedidoFiltro filtro, Root<PedidoEntidade> root, CriteriaBuilder builder, Predicate predicate) {
        if (filtro.getDataInicio() != null) {
            LocalDateTime dataInicio = filtro.getDataInicio().atStartOfDay();
            return builder.and(predicate, builder.greaterThanOrEqualTo(root.get("dhPedido"), dataInicio));
        }
        return predicate;
    }

    private static Predicate adicionarFiltroDataFim(
            PedidoFiltro filtro, Root<PedidoEntidade> root, CriteriaBuilder builder, Predicate predicate) {
        if (filtro.getDataFim() != null) {
            LocalDateTime dataFim = filtro.getDataFim().plusDays(1).atStartOfDay();
            return builder.and(predicate, builder.lessThan(root.get("dhPedido"), dataFim));
        }
        return predicate;
    }

    private static Predicate adicionarFiltroSituacao(
            PedidoFiltro filtro, Root<PedidoEntidade> root, CriteriaBuilder builder, Predicate predicate) {
        if (filtro.getSituacao() != null) {
            return builder.and(predicate, builder.equal(root.get("stPedido"), filtro.getSituacao()));
        }
        return predicate;
    }

    private static Predicate adicionarFiltroCpfCliente(
            PedidoFiltro filtro, Root<PedidoEntidade> root, CriteriaBuilder builder, Predicate predicate) {
        if (filtro.getCpfCliente() != null && !filtro.getCpfCliente().isEmpty()) {
            Join<PedidoEntidade, ClienteEntidade> clienteJoin = root.join("cliId", JoinType.INNER);
            return builder.and(predicate, builder.equal(clienteJoin.get("nmCpf"), filtro.getCpfCliente()));
        }
        return predicate;
    }
}
