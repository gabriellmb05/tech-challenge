package br.com.on.fiap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentRequest {

	private int amount;
	private String description;
	private Payment payment;

	@JsonProperty("additional_info")
	private AdditionalInfo additionalInfo;
}
