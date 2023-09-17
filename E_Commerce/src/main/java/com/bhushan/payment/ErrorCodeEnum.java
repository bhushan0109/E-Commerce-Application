package com.bhushan.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ErrorCodeEnum {

	EXCEPTION_OCCURRED(1002, "Exception Occurred in  Service"),
	
	CARD_INVALID(2001, "Invalid Card Details"),
	ORDER_TOKEN_INVALID(2002, "Invalid Order Token"),
	INVALID_MERCHANT(2003,"Merchant details not available"),
	BANK_ACCOUNT_INVALID(2004,"Bank details are invalid"),
	USER_INVALID(2005,"Invalid User"),
	ORDER_INVALID(2006,"Invalid ORDER"),
	TXN_INVALID(2007,"Invalid Transaction Details"),
	NO_CARDS(2008,"No active cards found"),
	PAYEE_INVALID(2009,"Invalid Payee Id or Payee Details Not Available"),
	PAYEE_ALREADY_EXISTS(2010,"User already has payee with same account details"),
	INVALID_SETTLEMENT(2011,"INVALID SETTLEMENT MODE"),
	INVALID_CARD_NETWORK(2012, "We are only accepting MasterCard or Visa Credit Card for now "),
	INVALID_CARD_COUNTRY(2013,"Only Indian Cards are accepted for now"),
	INVALID_USER_PAYEE_MAP(2014,"User payee mapping not found"),
	UPI_AMOUNT_LIMIT(2015,"Txn amount for UPI exceeded"),
	USER_PAYEE_NAME_ISSUE(2016,"You can only transfer to bank accounts which are not registered in your or your company's name or related parties"),
	USER_KYC_PENDING(2017,"Please Complete your KYC to proceed further"),
	INVALID_LAST_INDEX(2018,"LastIndex provided is invalid"),
	CARD_ALREADY_EXISTS(2019,"Card number already exists..."),
	ISSUE_IN_CARD_DELETION(2020,"Issue in card deletion"),
	TECHNICAL_ISSUE(2021,"We are facing a technical issue at the moment. Please try again in some time"),
	USER_CARD_NAME_ISSUE(2022,"You can only add cards which are registered in your or your company's name or related parties."),
	INVALID_PAYMENT_DETAILS(2023,"Invalid Payment Details"),
	INVALID_IFSC(2024,"Invalid IFSC code"),
	INVALID_CARD_TYPE(2025, "Please use MasterCard or Visa Credit Card only"),
	RESTRICT_PREPAID(2026, "We are not accepting Prepaid Credit Cards for now"),
	BANKAC_USER_IFSC_UNIQUE(2027, "Please wait, your request is being processed"),
	INVALID_CATEGORY(2028,"Invalid Category"),
	INVALID_PAYEE_CREATION(2029,"Unable to create payee, Contact BharatNXT"),
	
	ACCOUNT_NOT_VALIDATED(3001,"Account validation failed at CF server"),
	UPI_IS_NOT_ENABLED(4001,"UPI Payments coming soon...");
	
	@Getter
	private Integer id;
	
	@Getter
	private String value;
	
	public static ErrorCodeEnum findByValue(String value) {
		for(ErrorCodeEnum order : ErrorCodeEnum.values()) {
			if(order.getValue().equalsIgnoreCase(value)) {
				return order;
			}
		}
		return null;
	}
	
	public static ErrorCodeEnum findById(Integer value) {
		
		for(ErrorCodeEnum order : ErrorCodeEnum.values()) {
			System.out.println("ErrorCodeEnum.values() :" +ErrorCodeEnum.values().toString());
			System.out.println("order.getId() :" +order.getId() + " value : " +value);
			int val = order.getId();
			if(val == value) {
				System.out.print("order : " +order.toString());
				return order;
			}
		}
		return null;
	}
}



