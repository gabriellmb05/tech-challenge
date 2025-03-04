package br.com.on.datapool;

import br.com.on.fiap.adapter.input.dto.response.PedidoRespostaDTO;
import br.com.on.fiap.core.domain.entity.SituacaoPedido;
import java.time.LocalDateTime;
import java.util.List;

public class DataPoolPedidoRespostaDTO {

    private static PedidoRespostaDTO construirPedidoRespostaDTO(
            Long id, SituacaoPedido situacao, String protocolo, LocalDateTime dataHora) {
        return PedidoRespostaDTO.builder()
                .id(id)
                .situacao(situacao)
                .protocolo(protocolo)
                .dataHora(dataHora)
                .build();
    }

    public static PedidoRespostaDTO gerarPedido() {
        return construirPedidoRespostaDTO(1L, SituacaoPedido.REALIZADO, "2025012010424756339", LocalDateTime.now());
    }

    public static List<PedidoRespostaDTO> gerarListaPedidoRespostaDTO() {
        return List.of(
                construirPedidoRespostaDTO(1L, SituacaoPedido.REALIZADO, "2025012010424756339", LocalDateTime.now()),
                construirPedidoRespostaDTO(2L, SituacaoPedido.REALIZADO, "2025012010424756340", LocalDateTime.now()));
    }
}
