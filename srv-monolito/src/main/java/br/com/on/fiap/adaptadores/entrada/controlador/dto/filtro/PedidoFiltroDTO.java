package br.com.on.fiap.adaptadores.entrada.controlador.dto.filtro;

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
