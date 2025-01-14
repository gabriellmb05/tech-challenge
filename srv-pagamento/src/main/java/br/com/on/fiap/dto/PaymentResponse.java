package br.com.on.fiap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponse {

	private String id;

	@JsonProperty("device_id")
	private String deviceId;
	private int amount;
	private String description;
	private Payment payment;

	@JsonProperty("additional_info")
	private AdditionalInfo additionalInfo;
}
