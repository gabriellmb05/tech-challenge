package br.com.on.fiap.controller;

import br.com.on.fiap.dto.PaymentRequest;
import br.com.on.fiap.dto.PaymentResponse;
import br.com.on.fiap.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	private final Logger log = LoggerFactory.getLogger(PaymentController.class);

	private final PaymentService paymentService;

	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@PostMapping
	public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest) {
		log.info("Request: {}", paymentRequest);
		PaymentResponse response = paymentService.createPaymentResponse(paymentRequest);
		log.info("Response: {}", response);
		return ResponseEntity.ok(response);
	}
}
