package com.bhushan.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ApiMstEnum {

//	PAYMENT_INITIATE,
//	PAYMENT_DECRYPT_RESPONSE,
//	PAYMENT_INQUIRY;

	CREATE_ORDER_CF(1), ORDER_PAY_CF(2);

	@Getter
	private Integer id;
	
	public static ErrorCodeEnum findByValue(String value) {
		for (ErrorCodeEnum order : ErrorCodeEnum.values()) {
			if (order.getValue().equalsIgnoreCase(value)) {
				return order;
			}
		}
		return null;
	}
}

