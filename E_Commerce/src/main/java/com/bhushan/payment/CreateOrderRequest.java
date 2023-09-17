package com.bhushan.payment;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

	private String order_id;
	private double order_amount;
	private String order_currency;
	private CustomerDetails customer_details;
	private OrderMeta order_meta;
	private List<OrderSplitCF> order_splits;

	public CreateOrderRequest(CreateOrderRequest request) {
		this.order_id = request.order_id;
		this.order_amount = request.order_amount;
		this.order_currency = request.order_currency;
		this.customer_details = request.customer_details;
		this.order_meta = request.order_meta;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public class CustomerDetails {
		private String customer_id;
		private String customer_email;
		private String customer_phone;
		private String customer_name;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public class OrderMeta {
		private String payment_methods;
		private String return_url;
		private String notify_url;
	}

}
