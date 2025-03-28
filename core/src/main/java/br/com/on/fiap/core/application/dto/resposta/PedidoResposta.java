package br.com.on.fiap.core.application.dto.resposta;

import br.com.on.fiap.core.domain.Pedido;
import br.com.on.fiap.core.domain.SituacaoPedido;
import java.time.LocalDateTime;

public interface PedidoResposta {

    Long getId();

    SituacaoPedido getSituacao();

    String getProtocolo();

    LocalDateTime getDataHora();

    LocalDateTime getDataHoraAtualizacao();

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

            @Override
            public LocalDateTime getDataHoraAtualizacao() {
                return pedido.getDataHoraAtualizacao();
            }
        };
    }
}
