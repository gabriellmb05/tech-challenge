package br.com.on.fiap.adaptadores.entrada.controlador.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class InfoAdicionaisDTO {

	@JsonProperty("external_reference")
	private String externalReference;

	@JsonProperty("print_on_terminal")
	private boolean printOnTerminal;
}
