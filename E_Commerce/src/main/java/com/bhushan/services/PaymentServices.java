package com.bhushan.services;

import java.util.List;

import com.bhushan.exceptions.DuplicateResourceException;
import com.bhushan.exceptions.ResourceNotFoundException;
import com.bhushan.modelRequestDto.PaymentRequestDto;
import com.bhushan.modelResponseDto.PaymentResponseDto;
import com.bhushan.payloads.ApiResponse;

/**
 * @author bhushan patil
 *
 */

public interface PaymentServices {
	
	
	PaymentResponseDto addPaymentMethod(PaymentRequestDto paymentRequestDto) throws DuplicateResourceException;

	PaymentResponseDto getPaymentMethod(Integer paymentId) throws ResourceNotFoundException;

	ApiResponse deletePaymentMethod(Integer paymentId) throws ResourceNotFoundException;

	PaymentResponseDto revokePaymentMethod(Integer paymentId) throws ResourceNotFoundException;

	List<PaymentResponseDto> getAllPaymentMethods() throws ResourceNotFoundException;

}
