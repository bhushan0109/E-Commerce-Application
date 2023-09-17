/**
 * 
 */
package com.bhushan.controllers;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bhushan.JWT.security.request.JwtAuthRequest;
import com.bhushan.JWT.security.response.JwtAuthResponse;
import com.bhushan.JWT.security.utils.JWTUtils;
import com.bhushan.exceptions.DuplicateResourceException;
import com.bhushan.exceptions.ResourceNotFoundException;
import com.bhushan.model.Customer;
import com.bhushan.modelRequestDto.AdminRequestDto;
import com.bhushan.modelRequestDto.CustomerRequestDto;
import com.bhushan.modelResponseDto.AdminResponseDto;
import com.bhushan.modelResponseDto.CustomerDetailsResponseDto;
import com.bhushan.modelResponseDto.CustomerResponseDto;
import com.bhushan.payloads.ApiResponse;
import com.bhushan.payment.CreateOrderResponse;
import com.bhushan.payment.OrderSplitCF;
import com.bhushan.payment.PaymentService;
import com.bhushan.payment.UserDTO;
import com.bhushan.repositories.CustomerRepo;
import com.bhushan.services.AdminServices;
import com.bhushan.services.CustomerServices;


/**
 * @author bhushan patil
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/bestbuy/auth")
public class AuthController {

	@Autowired
	private JWTUtils jwtUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomerServices customerServices;

	@Autowired
	private AdminServices adminServices;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private PaymentService paymentService;

	// For Customer & Admin Username is Contact Number
	@PostMapping("/customers/signin")
	public ResponseEntity<?> loginCustomerHandler(@RequestBody JwtAuthRequest loginRequest) {

		Authentication authentication = (authenticationManager).authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

		JwtAuthResponse response = new JwtAuthResponse();

		response.setToken(jwtCookie.getValue());

		response.setResponse(this.modelMapper.map((Customer) userDetails, CustomerResponseDto.class));

		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(response);

	}

	@PostMapping("/admins/signin")
	public ResponseEntity<?> loginAdminHandler(@RequestBody JwtAuthRequest loginRequest) {

		Authentication authentication = (authenticationManager).authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

		JwtAuthResponse response = new JwtAuthResponse();

		response.setToken(jwtCookie.getValue());

		response.setResponse(this.modelMapper.map((Customer) userDetails, AdminResponseDto.class));

		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(response);

	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@PostMapping("/signout")
	public ResponseEntity<?> logoutHandler() {

		ResponseCookie cookie = jwtUtils.getCleanJwtCookie();

		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
				.body(new ApiResponse(LocalDateTime.now(), "You've been signed out!", true));

	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/active")
	public ResponseEntity<ApiResponse> getActiveUserName(HttpServletRequest request) {

		String jwt = jwtUtils.getJwtFromCookies(request);

		if (jwt != null && jwtUtils.validateJwtToken(jwt)) {

			String username = jwtUtils.getUserNameFromJwtToken(jwt);

			return new ResponseEntity<ApiResponse>(
					new ApiResponse(LocalDateTime.now(), "Valid J.W.T. Token", true, username), HttpStatus.OK);

		} else {
			return new ResponseEntity<ApiResponse>(new ApiResponse(LocalDateTime.now(), false, "Invalid J.W.T. Token"),
					HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@PostMapping("/customers/signup")
	public ResponseEntity<?> registerUserHandler(@Valid @RequestBody CustomerRequestDto customerRequestDto)
			throws ResourceNotFoundException {

		if (Boolean.TRUE.equals(this.customerRepo.existsByContact(customerRequestDto.getContact()))) {

			return ResponseEntity.badRequest()
					.body(new DuplicateResourceException("Customer", "Contact", customerRequestDto.getContact()));

		} else {

			if (Boolean.TRUE.equals(this.customerRepo.existsByEmail(customerRequestDto.getEmail()))) {

				return ResponseEntity.badRequest()
						.body(new DuplicateResourceException("Customer", "Email Id", customerRequestDto.getEmail()));

			} else {

				CustomerDetailsResponseDto customerDetailsResponseDto = this.customerServices
						.registerCustomer(customerRequestDto);

				return new ResponseEntity<CustomerDetailsResponseDto>(customerDetailsResponseDto, HttpStatus.CREATED);

			}

		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/admins/signup")
	public ResponseEntity<?> registerAdminHandler(@Valid @RequestBody AdminRequestDto adminRequestDto)
			throws ResourceNotFoundException {

		if (Boolean.TRUE.equals(this.customerRepo.existsByContact(adminRequestDto.getContact()))) {

			return ResponseEntity.badRequest().body(new DuplicateResourceException("Another Admin / Customer",
					"Contact", adminRequestDto.getContact()));

		} else {

			if (Boolean.TRUE.equals(this.customerRepo.existsByEmail(adminRequestDto.getEmail()))) {

				return new ResponseEntity<>(new DuplicateResourceException("Another Admin / Customer", "Email Id",
						adminRequestDto.getEmail()), HttpStatus.BAD_REQUEST);

			} else {

				AdminResponseDto adminResponseDto = this.adminServices.registerAdmin(adminRequestDto);

				return new ResponseEntity<AdminResponseDto>(adminResponseDto, HttpStatus.CREATED);

			}

		}

	}
//
//	@PostMapping("/create/order")
//	public ResponseEntity<?> createOrderCF(@RequestParam Double amount, @RequestBody List<OrderSplitCF> order_splits,
//			@RequestParam Long order_mode, HttpServletRequest httpRequest,@RequestParam Long merchant_id) throws Exception {
//
//		UserDTO userData = new  UserDTO();
//		userData.setId(1L);
//		CreateOrderResponse createOrderResponse = paymentService.createOrderApiCall(userData, amount, order_splits,
//				order_mode, httpRequest, merchant_id);
//		return new ResponseEntity<CreateOrderResponse>(createOrderResponse, HttpStatus.CREATED);
//
//	}
//	
//	@PostMapping("/v1/paymentWebhookCF")
//	public ResponseEntity<?> paymentWebhookCF(@RequestBody PaymentWebhookCF request, HttpServletRequest httpRequest) {
//		try {
//					paymentService.paymentWebhookCF(request, httpRequest));
//		} catch (Exception e) {
//		return new ResponseEntity<CreateOrderResponse>(createOrderResponse, HttpStatus.CREATED);
//
//	}

}
