package br.com.on.fiap.adapter.input.dto.filtro;

import br.com.on.fiap.core.application.dto.filtro.PedidoFiltroEntrada;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoFiltroRequest implements PedidoFiltroEntrada {

    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Long situacao;
    private String cpfCliente;
}
