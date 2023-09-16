/**
 * 
 */
package com.bhushan.services;

import com.bhushan.exceptions.ResourceNotFoundException;
import com.bhushan.modelRequestDto.AddressRequestDto;
import com.bhushan.modelRequestDto.AddressUpdateRequestDto;
import com.bhushan.modelResponseDto.AddressResponseDto;
import com.bhushan.modelResponseDto.CustomerResponseDto;

/**
 * @author bhushan patil
 *
 */
public interface AddressServices {

	AddressResponseDto getAddressDetails(String customerContact) throws ResourceNotFoundException;


	CustomerResponseDto addAddressDetails(String customerContact, AddressRequestDto addressRequestDto)
			throws ResourceNotFoundException;

	CustomerResponseDto udpateAddressDetails(String customerContact, AddressUpdateRequestDto addressUpdateRequestDto)
			throws ResourceNotFoundException;

}
