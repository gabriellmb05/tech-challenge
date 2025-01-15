package br.com.on.fiap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class PaymentRequest {

	private Integer amount;
	private String description;
	private Payment payment;

	@JsonProperty("additional_info")
	private AdditionalInfo additionalInfo;
}
