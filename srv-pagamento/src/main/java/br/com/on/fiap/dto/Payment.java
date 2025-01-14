package br.com.on.fiap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payment {

	private int installments;
	private String type;

	@JsonProperty("installments_cost")
	private String installmentsCost;
}
