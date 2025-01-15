package br.com.on.fiap.service;

import br.com.on.fiap.dto.AdditionalInfo;
import br.com.on.fiap.dto.Payment;
import br.com.on.fiap.dto.PaymentRequest;
import br.com.on.fiap.dto.PaymentResponse;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

	public PaymentResponse createPaymentResponse(PaymentRequest request) {
		Payment payment = Payment.builder().installments(request.getPayment().getInstallments())
				.type(request.getPayment().getType()).installmentsCost(request.getPayment().getInstallmentsCost())
				.build();

		AdditionalInfo additionalInfo = AdditionalInfo.builder()
				.externalReference(request.getAdditionalInfo().getExternalReference()).printOnTerminal(true).build();
		return PaymentResponse.builder().id(UUID.randomUUID().toString()).deviceId("PAX_A910__SMARTPOS1234345545")
				.amount(request.getAmount()).description(request.getDescription()).payment(payment)
				.additionalInfo(additionalInfo).build();
	}
}
