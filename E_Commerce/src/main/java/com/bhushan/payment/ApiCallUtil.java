//package com.bhushan.payment;
//
//import java.io.File;
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;
//
//@Component
//public class ApiCallUtil {
//	private static final Logger LOGGER = LoggerFactory.getLogger(ApiCallUtil.class);
//
//	@Autowired
//	RestTemplate getRestTemplate;
//
//	public ResponseEntity<String> netCoreApiCallingIntBulk(String username, String password, String feedID, String smsUrl,
//			File reqTextFile) {
//
////		ResponseEntity<String> netCoreResponse = null;
//		LOGGER.info("netCoreApiCallingIntBulk in");
//		LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
//		params.add("username", username);
//		params.add("password", password);
//		params.add("feedid", feedID);
//		params.add("upload", new FileSystemResource(reqTextFile));
//		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(smsUrl);
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//		HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, headers);
//		LOGGER.info("netcore api call url ==>" + builder.build().encode().toUri());
//		netCoreResponse = getRestTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, requestEntity,
//				String.class);
//		LOGGER.info("netCoreResponse " + netCoreResponse);
//		return netCoreResponse;
//
//	}
//
//	public ResponseEntity<String> merceApiCallingForInstant(UriComponentsBuilder builder) {
//		ResponseEntity<String> merceResponse = null;
//		LOGGER.info("merceApiCallingForInstant in");
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.add(HttpHeaders.AUTHORIZATION, "urAccessToken");
//		@SuppressWarnings({ "rawtypes", "unchecked" })
//
//		HttpEntity httpEntity = new HttpEntity(httpHeaders);
//		LOGGER.info("###########################################################################################");
//		LOGGER.info("instant merece api call url=> "+builder.build().toUri());
//		LOGGER.info("###########################################################################################");
//		merceResponse = getRestTemplate.exchange(builder.build().toUri(), HttpMethod.POST, httpEntity, String.class);
//
//		LOGGER.info("merceResponse " + merceResponse);
//		return merceResponse;
//	}
//	
//	
//	public ResponseEntity<String> merceApiCallingForBulk(String client, String app, String checksum,String campaign,String batch,String template, String smsUrl,
//			File reqZipFile) {
//		ResponseEntity<String> merceResponseBulk = null;
//		LOGGER.info("merceApiCallingForBulk in");
//		LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
//		params.add("client", client);
//		params.add("app", app);
//		params.add("checksum", checksum);
//		params.add("campaign", campaign);
//		params.add("batch", batch);
//		params.add("template",template);
//		params.add("file", new FileSystemResource(reqZipFile));
//		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(smsUrl);
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//		HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, headers);
//		LOGGER.info("bulk merece api call url ==>" + builder.build().encode().toUri());
//		merceResponseBulk = getRestTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, requestEntity,
//				String.class);
//		LOGGER.info("netCoreResponse " + merceResponseBulk);
//		return merceResponseBulk;
//	}
//}
