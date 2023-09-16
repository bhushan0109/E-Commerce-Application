/**
 * 
 */
package com.bhushan.payloads;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.bhushan.controllers.CustomerController;
import com.bhushan.exceptions.ResourceNotAllowedException;
import com.bhushan.exceptions.ResourceNotFoundException;
import com.bhushan.model.Customer;
import com.bhushan.modelResponseDto.CustomerResponseDto;

/**
 * @author bhushan patil
 *
 */
@Component
public class CustomerResponseModelAssembler extends RepresentationModelAssemblerSupport<Customer, CustomerResponseDto> {

	public CustomerResponseModelAssembler() {
		super(CustomerController.class, CustomerResponseDto.class);
	}

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CustomerResponseDto toModel(Customer entity) {

		CustomerResponseDto customerResponseDto = this.modelMapper.map(entity, CustomerResponseDto.class);

		try {
			try {
				customerResponseDto.add(
						linkTo(methodOn(CustomerController.class).getCustomerHandler(customerResponseDto.getContact()))
								.withRel("customer"));
			} catch (ResourceNotAllowedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}

		return customerResponseDto;
	}

}
