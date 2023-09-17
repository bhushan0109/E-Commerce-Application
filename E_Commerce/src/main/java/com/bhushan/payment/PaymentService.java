package com.bhushan.payment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@Service
public class PaymentService {

	private static Logger logger = LoggerFactory.getLogger(PaymentService.class);
	@Value("${cashfree.AppID}")
	private String cashfreeAppID;

	@Value("${cashfree.Secret}")
	private String cashfreeSecret;

	@Value("${cashfree.version}")
	private String appVersion;

	@Value("${cashfree.createOrderUrl}")
	private String createOrderUrl;

	@Value("${base.url}")
	private String baseUrl;

	@Autowired
	private ApiCallHistoryRepository apiCallHistoryRepository;

	public CreateOrderResponse createOrderApiCall(UserDTO userData, Double amount, List<OrderSplitCF> order_splits,
			Long order_mode, HttpServletRequest httpRequest, Long merchant_id) throws Exception {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = createHttpHeader();
		CreateOrderRequest createOrderRequest = new CreateOrderRequest();

		com.bhushan.payment.CreateOrderRequest.CustomerDetails customerDetails = createOrderRequest.new CustomerDetails();
		customerDetails.setCustomer_email("bhupatil0001@gmail.com");
		customerDetails.setCustomer_id(Long.valueOf(userData.getId()).toString());
		customerDetails.setCustomer_name("bhushan");
		customerDetails.setCustomer_phone("8975891291");
		com.bhushan.payment.CreateOrderRequest.OrderMeta orderMeta = createOrderRequest.new OrderMeta();
		orderMeta.setNotify_url(baseUrl + PaymentConstants.CashfreeConstants.NOTIFY_URL);
//		orderMeta.setReturn_url(order_mode.equals(Long.valueOf(1))
//				? baseUrl + "/addcard.html?order_id={order_id}&order_token={order_token}"
//				: baseUrl + "/payment.html?order_id={order_id}&order_token={order_token}");
//		orderMeta.setPayment_methods(PaymentConstants.CashfreeConstants.PAYMENT_METHODS); // issue at CF

		Long lastId = System.currentTimeMillis();
		createOrderRequest.setOrder_id("CF" + merchant_id + "_" + userData.getId() + "_" + lastId);
		createOrderRequest.setCustomer_details(customerDetails);
		createOrderRequest.setOrder_meta(orderMeta);
		// createOrderRequest.setOrder_amount(doublePrecision(amount, 2));
		createOrderRequest.setOrder_amount(10.23);
		createOrderRequest.setOrder_currency(PaymentConstants.CashfreeConstants.CURRENCY);
//		createOrderRequest.setOrder_splits(order_splits);

		HttpEntity<CreateOrderRequest> entity = new HttpEntity<CreateOrderRequest>(createOrderRequest, headers);
		logger.info("CreateOrderRequest to Cashfree ==> {}", new Gson().toJson(entity));
		try {

			ResponseEntity<CreateOrderResponse> responseEntity = restTemplate.exchange(createOrderUrl, HttpMethod.POST,
					entity, CreateOrderResponse.class);
//			ResponseEntity<Object> responseEntity = restTemplate.exchange(createOrderUrl, HttpMethod.POST,
//					entity, Object.class);

			logger.info("CreateOrderResponse from Cashfree ==> {}", new Gson().toJson(responseEntity.getBody()));

			logger.info("CreateOrderResponse from Cashfree ==> {}", new Gson().toJson(responseEntity.getBody()));

			CreateOrderResponse createOrderResponse = new CreateOrderResponse();

			ApiCallHistory apiCallHistory = new ApiCallHistory(ApiMstEnum.CREATE_ORDER_CF.getId(),
					new Gson().toJson(createOrderRequest), new Gson().toJson(responseEntity.getBody()), createOrderUrl,
					userData.getId(), CommonUtil.getIpAddress(httpRequest));

			apiCallHistoryRepository.save(apiCallHistory);

			HttpStatus statusCode = responseEntity.getStatusCode();
			if (statusCode == HttpStatus.OK || statusCode == HttpStatus.CREATED || statusCode == HttpStatus.ACCEPTED) {
				createOrderResponse = responseEntity.getBody();
			}
			return createOrderResponse;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	private HttpHeaders createHttpHeader() {

		HttpHeaders headers = new HttpHeaders();
		headers.add("x-client-id", cashfreeAppID);
		headers.add("x-client-secret", cashfreeSecret);
		headers.add("x-api-version", appVersion);
		headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.add("accept", MediaType.APPLICATION_JSON_VALUE);
		// headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

	public Double doublePrecision(Double value, int precision) {
		BigDecimal bd = new BigDecimal(value).setScale(precision, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public String paymentWebhookCF(PaymentWebhookCF request, HttpServletRequest httpRequest) throws Exception {

		ApiCallHistory apiCallHistory = new ApiCallHistory(ApiMstEnum.PAYMENT_WEBHOOK_CF.getId(),
				new Gson().toJson(request), null, "PaymentWebhookCF", null, CommonUtil.getIpAddress(httpRequest));
		apiCallHistoryRepository.save(apiCallHistory);
		// update payment status
		return "Success";
	}

	public VarifyOrderResponse varifyPaymentCF(String order_id, HttpServletRequest httpRequest) throws Exception {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = createHttpHeader();// merchant later stages
			HttpEntity entity = new HttpEntity(headers);
			ResponseEntity<VarifyOrderResponse> responseEntity = restTemplate.exchange(createOrderUrl + "/" + order_id,
					HttpMethod.GET, entity, VarifyOrderResponse.class);

			logger.info("CreateOrderResponse from Cashfree ==> {}", new Gson().toJson(responseEntity.getBody()));
			VarifyOrderResponse varifyOrderResponse = new VarifyOrderResponse();
			HttpStatus statusCode = responseEntity.getStatusCode();
			if (statusCode == HttpStatus.OK || statusCode == HttpStatus.CREATED || statusCode == HttpStatus.ACCEPTED) {
				varifyOrderResponse = responseEntity.getBody();
			}
			return varifyOrderResponse;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
