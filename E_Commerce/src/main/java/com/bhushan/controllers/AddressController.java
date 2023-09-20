/**
 * 
 */
package com.bhushan.controllers;

import javax.validation.Valid;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhushan.exceptions.ResourceNotAllowedException;
import com.bhushan.exceptions.ResourceNotFoundException;
import com.bhushan.modelRequestDto.AddressRequestDto;
import com.bhushan.modelRequestDto.AddressUpdateRequestDto;
import com.bhushan.modelRequestDto.LoginForm;
import com.bhushan.modelResponseDto.AddressResponseDto;
import com.bhushan.modelResponseDto.CustomerResponseDto;
import com.bhushan.services.AddressServices;

/**
 * @author bhushan patil
 *
 */
@RestController
@RequestMapping("/bestbuy/address")
public class AddressController {

	@Autowired
	private AddressServices addressServices;
	
	  @PostMapping("/customers/v1/")
	    public String processLogin(@Valid @RequestBody LoginForm form) {
	        return "Success";

	    }

	@GetMapping("/customers/{contact}")
	public ResponseEntity<AddressResponseDto> getAddressDetailsHandler(@PathVariable("contact") String customerContact)
			throws ResourceNotFoundException {

		AddressResponseDto addressDetails = this.addressServices.getAddressDetails(customerContact);

		// Self Link
		addressDetails
				.add(linkTo(methodOn(AddressController.class).getAddressDetailsHandler(customerContact)).withSelfRel());

		return new ResponseEntity<AddressResponseDto>(addressDetails, HttpStatus.OK);

	}

	@PostMapping("/customers/{contact}")
	public ResponseEntity<CustomerResponseDto> addAddressDetailsHandler(@PathVariable("contact") String customerContact,
			@Valid @RequestBody AddressRequestDto addressRequestDto) throws ResourceNotFoundException, ResourceNotAllowedException {

		CustomerResponseDto customerResponseDto = this.addressServices.addAddressDetails(customerContact,
				addressRequestDto);

		// Self Link
		customerResponseDto
				.add(linkTo(methodOn(AddressController.class).getAddressDetailsHandler(customerContact)).withSelfRel());

		// Customer Link
		customerResponseDto
				.add(linkTo(methodOn(CustomerController.class).getCustomerHandler(customerResponseDto.getContact()))
						.withRel("customer"));

		return new ResponseEntity<CustomerResponseDto>(customerResponseDto, HttpStatus.CREATED);
	}

	@PutMapping("/customers/{contact}")
	public ResponseEntity<CustomerResponseDto> udpateAddressDetailsHandler(@PathVariable("contact") String customerContact,
			@RequestBody AddressUpdateRequestDto addressUpdateRequestDto) throws ResourceNotFoundException, ResourceNotAllowedException {

		CustomerResponseDto customerResponseDto = this.addressServices.udpateAddressDetails(customerContact,
				addressUpdateRequestDto);

		// Self Link
		customerResponseDto
				.add(linkTo(methodOn(AddressController.class).getAddressDetailsHandler(customerContact)).withSelfRel());

		// Customer Link
		customerResponseDto
				.add(linkTo(methodOn(CustomerController.class).getCustomerHandler(customerResponseDto.getContact()))
						.withRel("customer"));

		return new ResponseEntity<CustomerResponseDto>(customerResponseDto, HttpStatus.OK);

	}

}
