package br.com.on.fiap.adaptadores.entrada.controlador.dto.filtro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoFiltroDTO {

	private LocalDate dataInicio;
	private LocalDate dataFim;
	private Long situacao;
	private String cpfCliente;
}
