/**
 * 
 */
package com.bhushan.services;


import com.bhushan.exceptions.ResourceNotAllowedException;
import com.bhushan.exceptions.ResourceNotFoundException;
import com.bhushan.modelResponseDto.CartResponseDto;
import com.bhushan.modelResponseDto.OrderResponseDto;
import com.bhushan.payloads.ApiResponse;

/**
 * @author bhushan patil
 *
 */

public interface CartServices {
	

	ApiResponse addProducttoCart(String contact, Integer productId, Integer productQuantity)
			throws ResourceNotFoundException, ResourceNotAllowedException;

	ApiResponse updateCartProductQuantity(String contact, Integer productId, Integer quantity) throws ResourceNotFoundException, ResourceNotAllowedException;

	CartResponseDto emptyCart(String contact) throws ResourceNotFoundException;

	CartResponseDto deleteProductfromCart(String contact, Integer productId) throws ResourceNotFoundException;
	
	CartResponseDto getCart(String contact) throws ResourceNotFoundException;


	OrderResponseDto buyCart(String contact, Integer paymentId) throws ResourceNotFoundException, ResourceNotAllowedException;
	

}
