package com.bhushan.payment;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/pg/")
public class PaymentControllerCf {

	@Autowired
	private PaymentService paymentService;

	@PostMapping("v1/create/order")
	public ResponseEntity<?> createOrderCF(@RequestParam Double amount, @RequestBody List<OrderSplitCF> order_splits,
			@RequestParam Long order_mode, HttpServletRequest httpRequest, @RequestParam Long merchant_id)
			throws Exception {

		UserDTO userData = new UserDTO();
		userData.setId(1L);
		CreateOrderResponse createOrderResponse = paymentService.createOrderApiCall(userData, amount, order_splits,
				order_mode, httpRequest, merchant_id);
		return new ResponseEntity<CreateOrderResponse>(createOrderResponse, HttpStatus.CREATED);

	}

	@PostMapping("/v1/paymentWebhookCF")
	public ResponseEntity<?> paymentWebhookCF(@RequestBody PaymentWebhookCF request, HttpServletRequest httpRequest) {
		try {
			paymentService.paymentWebhookCF(request, httpRequest);
		} catch (Exception e) {
			return new ResponseEntity<Object>("", HttpStatus.CREATED);

		}
		return null;

	}
}
