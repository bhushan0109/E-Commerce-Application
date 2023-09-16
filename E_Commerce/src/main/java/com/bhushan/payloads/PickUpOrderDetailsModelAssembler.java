/**
 * 
 */
package com.bhushan.payloads;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.bhushan.controllers.CustomerController;
import com.bhushan.controllers.OrderController;
import com.bhushan.exceptions.DuplicateResourceException;
import com.bhushan.exceptions.ResourceNotAllowedException;
import com.bhushan.exceptions.ResourceNotFoundException;
import com.bhushan.model.PickUpOrderRequest;
import com.bhushan.modelResponseDto.PickUpOrderDetailsResponseDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author bhushan patil
 *
 */
@Component
public class PickUpOrderDetailsModelAssembler
		extends RepresentationModelAssemblerSupport<PickUpOrderRequest, PickUpOrderDetailsResponseDto> {

	@Autowired
	private ModelMapper modelMapper;

	public PickUpOrderDetailsModelAssembler() {
		super(OrderController.class, PickUpOrderDetailsResponseDto.class);
	}

	@Override
	public PickUpOrderDetailsResponseDto toModel(PickUpOrderRequest entity) {

		PickUpOrderDetailsResponseDto pickUpOrderDetailsResponseDto = this.modelMapper.map(entity,
				PickUpOrderDetailsResponseDto.class);

		try {

			pickUpOrderDetailsResponseDto.add(linkTo(methodOn(CustomerController.class)
					.getCustomerHandler(pickUpOrderDetailsResponseDto.getCustomer().getContact())).withRel("customer"));

			pickUpOrderDetailsResponseDto.add(linkTo(
					methodOn(OrderController.class).getOrderById(pickUpOrderDetailsResponseDto.getOrder().getOrderId()))
					.withRel("order"));

			pickUpOrderDetailsResponseDto.add(linkTo(methodOn(OrderController.class).revokeOrderPickUpStatus(null,
					pickUpOrderDetailsResponseDto.getOrder().getOrderId())).withRel("revoke pickup status"));

		} catch (ResourceNotFoundException | DuplicateResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ResourceNotAllowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pickUpOrderDetailsResponseDto;
	}

}
