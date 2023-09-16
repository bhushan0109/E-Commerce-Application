/**
 * 
 */
package com.bhushan.servicesImplementation;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhushan.exceptions.ResourceNotFoundException;
import com.bhushan.model.Address;
import com.bhushan.model.Customer;
import com.bhushan.modelRequestDto.AddressRequestDto;
import com.bhushan.modelRequestDto.AddressUpdateRequestDto;
import com.bhushan.modelResponseDto.AddressResponseDto;
import com.bhushan.modelResponseDto.CustomerResponseDto;
import com.bhushan.repositories.CustomerRepo;
import com.bhushan.services.AddressServices;

/**
 * @author bhushan patil
 *
 */
@Service
public class AddressServicesImplementation implements AddressServices {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public CustomerResponseDto udpateAddressDetails(String customerContact,
			AddressUpdateRequestDto addressUpdateRequestDto) throws ResourceNotFoundException {

		Customer customer = this.customerRepo.findByContact(customerContact)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "Contact Number", customerContact));
		
		Address address = customer.getAddress();

		if (addressUpdateRequestDto.getAddressLine1() != null) {

			address.setAddressLine1(addressUpdateRequestDto.getAddressLine1());
		}

		if (addressUpdateRequestDto.getAddressLine2() != null) {

			address.setAddressLine2(addressUpdateRequestDto.getAddressLine2());
		}

		if (addressUpdateRequestDto.getBuildingName() != null) {

			address.setBuildingName(addressUpdateRequestDto.getBuildingName());
		}
		if (addressUpdateRequestDto.getCity() != null) {

			address.setCity(addressUpdateRequestDto.getCity());
		}
		if (addressUpdateRequestDto.getCountry() != null) {

			address.setCountry(addressUpdateRequestDto.getCountry());
		}
		if (addressUpdateRequestDto.getLandMark() != null) {

			address.setLandMark(addressUpdateRequestDto.getLandMark());
		}
		if (addressUpdateRequestDto.getPincode() != null) {

			address.setPincode(addressUpdateRequestDto.getPincode());
		}
		if (addressUpdateRequestDto.getState() != null) {

			address.setState(addressUpdateRequestDto.getState());
		}

		customer.setAddress(address);

		Customer savedCustomer = this.customerRepo.save(customer);

		return this.toCustomerResponseDto(savedCustomer);
	}

	@Override
	public AddressResponseDto getAddressDetails(String customerContact) throws ResourceNotFoundException {

		Customer customer = this.customerRepo.findByContact(customerContact)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "Contact Number", customerContact));

		return this.toAddressResponseDto(customer.getAddress());

	}

	@Override
	public CustomerResponseDto addAddressDetails(String customerContact, AddressRequestDto addressRequestDto)
			throws ResourceNotFoundException {

		Customer customer = this.customerRepo.findByContact(customerContact)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "Contact Number", customerContact));
		
		Address address = this.toAddress(addressRequestDto);

		customer.setAddress(address);

		this.customerRepo.save(customer);

		return this.toCustomerResponseDto(customer);
	}

	private Address toAddress(AddressRequestDto addressRequestDto) {

		return this.modelMapper.map(addressRequestDto, Address.class);

	}

	private AddressResponseDto toAddressResponseDto(Address address) {

		return this.modelMapper.map(address, AddressResponseDto.class);

	}

	// Necessary for mapping Nested Objects

	private CustomerResponseDto toCustomerResponseDto(Customer customer) {

		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

		return this.modelMapper.map(customer, CustomerResponseDto.class);

	}

}
