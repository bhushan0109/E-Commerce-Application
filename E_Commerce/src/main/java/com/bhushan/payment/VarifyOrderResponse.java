package com.bhushan.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VarifyOrderResponse {

	private float cf_order_id;
	private String order_id;
	private String entity;
	private String order_currency;
	private float order_amount;
	private String order_status;
	private String order_token;
	private String order_note;
	private String order_expiry_time;
	private String payment_link;
}
