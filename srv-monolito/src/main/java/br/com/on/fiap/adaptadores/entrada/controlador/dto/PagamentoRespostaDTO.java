package br.com.on.fiap.adaptadores.entrada.controlador.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class PagamentoRespostaDTO {

	private String id;

	@JsonProperty("device_id")
	private String deviceId;
	private Integer amount;
	private String description;
	private PagamentoDTO pagamentoDTO;

	@JsonProperty("additional_info")
	private InfoAdicionaisDTO infoAdicionaisDTO;
}
