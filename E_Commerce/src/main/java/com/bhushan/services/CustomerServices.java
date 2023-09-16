/**
 * 
 */
package com.bhushan.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.bhushan.exceptions.FileTypeNotValidException;
import com.bhushan.exceptions.ResourceNotFoundException;
import com.bhushan.model.Customer;
import com.bhushan.modelRequestDto.CustomerRequestDto;
import com.bhushan.modelRequestDto.CustomerUpdateRequestDto;
import com.bhushan.modelResponseDto.CustomerDetailsResponseDto;
import com.bhushan.modelResponseDto.CustomerResponseDto;
import com.bhushan.payloads.ApiResponse;

/**
 * @author bhushan patil
 *
 */

public interface CustomerServices {

	CustomerDetailsResponseDto registerCustomer(CustomerRequestDto customerRequestDto) throws ResourceNotFoundException;

	CustomerDetailsResponseDto updateCustomerDetails(String contact, CustomerUpdateRequestDto customerUpdateRequestDto)
			throws ResourceNotFoundException;

	ApiResponse deleteCustomerAccount(String contact) throws ResourceNotFoundException;

	CustomerResponseDto getCustomer(String contact) throws ResourceNotFoundException;

	CustomerDetailsResponseDto updateCustomerImage(String contact, MultipartFile image)
			throws ResourceNotFoundException, IOException, FileTypeNotValidException;

	void serveCustomerImage(String imageName, HttpServletResponse response)
			throws ResourceNotFoundException, IOException, FileTypeNotValidException;

	ApiResponse deleteCustomerImage(String fileName) throws IOException;

	List<CustomerResponseDto> searchByfirstName(String firstName);

	Page<Customer> getAllCustomerDetails(Integer page, Integer size, String sortDirection, String sortBy);

	List<CustomerResponseDto> searchBylastName(String lastName);

	List<CustomerResponseDto> searchByemailId(String email);

	List<CustomerResponseDto> searchByFirstAndLastName(String firstName, String lastName);

}
