package br.com.on.fiap.core.domain.model;

import java.time.LocalDateTime;

public interface PedidoResposta {

    Long getId();

    SituacaoPedido getSituacao();

    String getProtocolo();

    LocalDateTime getDataHora();

    static PedidoResposta create(Pedido pedido) {
        return new PedidoResposta() {
            @Override
            public Long getId() {
                return pedido.getId();
            }

            @Override
            public SituacaoPedido getSituacao() {
                return pedido.getSituacao();
            }

            @Override
            public String getProtocolo() {
                return pedido.getProtocolo();
            }

            @Override
            public LocalDateTime getDataHora() {
                return pedido.getDataHora();
            }
        };
    }
}
