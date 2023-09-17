package com.bhushan.payment;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderResponse {

	private float cf_order_id;
    private String order_id;
    private String entity;
    private String order_currency;
    private double order_amount;
    private String order_expiry_time;
    private CustomerDetails customer_details;
    private OrderMeta order_meta;
    private Settlements settlements;
    private Payments payments;
    private Refunds refunds;
    private String order_status;
    private String order_token;
    private String order_note;
    private String payment_link;
    private String created_at;
    private String payment_session_id;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class CustomerDetails{
        private String customer_id;
        private String customer_name;
        private String customer_email;
        private String customer_phone;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class OrderMeta{
        private String return_url;
        private String notify_url;
        private String payment_methods;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Settlements{
        private String url;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Payments{
        private String url;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Refunds{
        private String url;
    }
}
