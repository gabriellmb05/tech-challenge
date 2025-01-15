package br.com.on.fiap.adaptadores.entrada.controlador.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class PagamentoDTO {

	private Integer installments;
	private String type;

	@JsonProperty("installments_cost")
	private String installmentsCost;
}
