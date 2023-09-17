package com.bhushan.payment;

import java.util.ArrayList;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PaymentWebhookCF {
	public class Application {
		Data DataObject;
		private String event_time;
		private String type;

		// Getter Methods

		public Data getData() {
			return DataObject;
		}

		public String getEvent_time() {
			return event_time;
		}

		public String getType() {
			return type;
		}

		// Setter Methods

		public void setData(Data dataObject) {
			this.DataObject = dataObject;
		}

		public void setEvent_time(String event_time) {
			this.event_time = event_time;
		}

		public void setType(String type) {
			this.type = type;
		}
	}

	public class Data {
		Order OrderObject;
		Payment PaymentObject;
		Customer_details Customer_detailsObject;
		Payment_gateway_details Payment_gateway_detailsObject;
		ArrayList<Object> payment_offers = new ArrayList<Object>();

		// Getter Methods

		public Order getOrder() {
			return OrderObject;
		}

		public Payment getPayment() {
			return PaymentObject;
		}

		public Customer_details getCustomer_details() {
			return Customer_detailsObject;
		}

		public Payment_gateway_details getPayment_gateway_details() {
			return Payment_gateway_detailsObject;
		}

		// Setter Methods

		public void setOrder(Order orderObject) {
			this.OrderObject = orderObject;
		}

		public void setPayment(Payment paymentObject) {
			this.PaymentObject = paymentObject;
		}

		public void setCustomer_details(Customer_details customer_detailsObject) {
			this.Customer_detailsObject = customer_detailsObject;
		}

		public void setPayment_gateway_details(Payment_gateway_details payment_gateway_detailsObject) {
			this.Payment_gateway_detailsObject = payment_gateway_detailsObject;
		}
	}

	public class Payment_gateway_details {
		private String gateway_name;
		private String gateway_order_id;
		private String gateway_payment_id;
		private String gateway_status_code = null;

		// Getter Methods

		public String getGateway_name() {
			return gateway_name;
		}

		public String getGateway_order_id() {
			return gateway_order_id;
		}

		public String getGateway_payment_id() {
			return gateway_payment_id;
		}

		public String getGateway_status_code() {
			return gateway_status_code;
		}

		// Setter Methods

		public void setGateway_name(String gateway_name) {
			this.gateway_name = gateway_name;
		}

		public void setGateway_order_id(String gateway_order_id) {
			this.gateway_order_id = gateway_order_id;
		}

		public void setGateway_payment_id(String gateway_payment_id) {
			this.gateway_payment_id = gateway_payment_id;
		}

		public void setGateway_status_code(String gateway_status_code) {
			this.gateway_status_code = gateway_status_code;
		}
	}

	public class Customer_details {
		private String customer_name = null;
		private String customer_id;
		private String customer_email;
		private String customer_phone;

		// Getter Methods

		public String getCustomer_name() {
			return customer_name;
		}

		public String getCustomer_id() {
			return customer_id;
		}

		public String getCustomer_email() {
			return customer_email;
		}

		public String getCustomer_phone() {
			return customer_phone;
		}

		// Setter Methods

		public void setCustomer_name(String customer_name) {
			this.customer_name = customer_name;
		}

		public void setCustomer_id(String customer_id) {
			this.customer_id = customer_id;
		}

		public void setCustomer_email(String customer_email) {
			this.customer_email = customer_email;
		}

		public void setCustomer_phone(String customer_phone) {
			this.customer_phone = customer_phone;
		}
	}

	public class Payment {
		private float cf_payment_id;
		private String payment_status;
		private float payment_amount;
		private String payment_currency;
		private String payment_message;
		private String payment_time;
		private String bank_reference;
		private String auth_id = null;
		Payment_method Payment_methodObject;
		private String payment_group;

		// Getter Methods

		public float getCf_payment_id() {
			return cf_payment_id;
		}

		public String getPayment_status() {
			return payment_status;
		}

		public float getPayment_amount() {
			return payment_amount;
		}

		public String getPayment_currency() {
			return payment_currency;
		}

		public String getPayment_message() {
			return payment_message;
		}

		public String getPayment_time() {
			return payment_time;
		}

		public String getBank_reference() {
			return bank_reference;
		}

		public String getAuth_id() {
			return auth_id;
		}

		public Payment_method getPayment_method() {
			return Payment_methodObject;
		}

		public String getPayment_group() {
			return payment_group;
		}

		// Setter Methods

		public void setCf_payment_id(float cf_payment_id) {
			this.cf_payment_id = cf_payment_id;
		}

		public void setPayment_status(String payment_status) {
			this.payment_status = payment_status;
		}

		public void setPayment_amount(float payment_amount) {
			this.payment_amount = payment_amount;
		}

		public void setPayment_currency(String payment_currency) {
			this.payment_currency = payment_currency;
		}

		public void setPayment_message(String payment_message) {
			this.payment_message = payment_message;
		}

		public void setPayment_time(String payment_time) {
			this.payment_time = payment_time;
		}

		public void setBank_reference(String bank_reference) {
			this.bank_reference = bank_reference;
		}

		public void setAuth_id(String auth_id) {
			this.auth_id = auth_id;
		}

		public void setPayment_method(Payment_method payment_methodObject) {
			this.Payment_methodObject = payment_methodObject;
		}

		public void setPayment_group(String payment_group) {
			this.payment_group = payment_group;
		}
	}

	public class Payment_method {
		Upi UpiObject;

		// Getter Methods

		public Upi getUpi() {
			return UpiObject;
		}

		// Setter Methods

		public void setUpi(Upi upiObject) {
			this.UpiObject = upiObject;
		}
	}

	public class Upi {
		private String channel = null;
		private String upi_id;

		// Getter Methods

		public String getChannel() {
			return channel;
		}

		public String getUpi_id() {
			return upi_id;
		}

		// Setter Methods

		public void setChannel(String channel) {
			this.channel = channel;
		}

		public void setUpi_id(String upi_id) {
			this.upi_id = upi_id;
		}
	}

	public class Order {
		private String order_id;
		private float order_amount;
		private String order_currency;
		private String order_tags = null;

		// Getter Methods

		public String getOrder_id() {
			return order_id;
		}

		public float getOrder_amount() {
			return order_amount;
		}

		public String getOrder_currency() {
			return order_currency;
		}

		public String getOrder_tags() {
			return order_tags;
		}

		// Setter Methods

		public void setOrder_id(String order_id) {
			this.order_id = order_id;
		}

		public void setOrder_amount(float order_amount) {
			this.order_amount = order_amount;
		}

		public void setOrder_currency(String order_currency) {
			this.order_currency = order_currency;
		}

		public void setOrder_tags(String order_tags) {
			this.order_tags = order_tags;
		}
	}

//
//	public Datum data;
//	public Date event_time;
//	public String type;
//
//	@Data
//	@AllArgsConstructor
//	@NoArgsConstructor
//	public class Datum {
//		public Order order;
//		public Payment payment;
//		public CustomerDetails customer_details;
//
//		@Data
//		@AllArgsConstructor
//		@NoArgsConstructor
//		public class Order {
//			public String order_id;
//			public int order_amount;
//			public String order_currency;
////            public Object order_tags;
//		}
//
//		@Data
//		@AllArgsConstructor
//		@NoArgsConstructor
//		public class CustomerDetails {
//			public String customer_name;
//			public String customer_id;
//			public String customer_email;
//			public String customer_phone;
//		}
//
//		@Data
//		@AllArgsConstructor
//		@NoArgsConstructor
//		public class Payment {
//			public float cf_payment_id;
//			public String payment_status;
//			public double payment_amount;
//			public String payment_currency;
//			public String payment_message;
//			public Date payment_time;
//			public String bank_reference;
//			public String auth_id;
//			public PaymentMethod payment_method;
//			public String payment_group;
//
//			@Data
//			@AllArgsConstructor
//			@NoArgsConstructor
//			public class PaymentMethod {
//				public Card card;
//
//				@Data
//				@AllArgsConstructor
//				@NoArgsConstructor
//				public class Card {
//					public String channel;
//					public String card_number;
//					public String card_network;
//					public String card_type;
//					public String card_country;
//					public String card_bank_name;
//				}
//			}
//		}
//	}
}
