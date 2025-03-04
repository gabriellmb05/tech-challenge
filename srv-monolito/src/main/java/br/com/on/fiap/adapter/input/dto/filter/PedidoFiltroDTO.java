package br.com.on.fiap.adapter.input.dto.filter;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoFiltroDTO {

    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Long situacao;
    private String cpfCliente;
}
