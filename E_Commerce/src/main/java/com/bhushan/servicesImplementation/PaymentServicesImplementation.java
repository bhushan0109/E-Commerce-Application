/**
 * 
 */
package com.bhushan.servicesImplementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhushan.exceptions.DuplicateResourceException;
import com.bhushan.exceptions.ResourceNotFoundException;
import com.bhushan.model.Payment;
import com.bhushan.modelRequestDto.PaymentRequestDto;
import com.bhushan.modelResponseDto.PaymentResponseDto;
import com.bhushan.payloads.ApiResponse;
import com.bhushan.repositories.PaymentRepo;
import com.bhushan.services.PaymentServices;

/**
 * @author bhushan patil
 *
 */
@Service
public class PaymentServicesImplementation implements PaymentServices {

	@Autowired
	private PaymentRepo paymentRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PaymentResponseDto addPaymentMethod(PaymentRequestDto paymentRequestDto) throws DuplicateResourceException {

		Optional<Payment> findById = this.paymentRepo.findById(paymentRequestDto.getPaymentId());

		if (findById.isEmpty()) {

			Payment payment = this.toPayment(paymentRequestDto);

			Payment savedPayment = this.paymentRepo.save(payment);

			return this.toPaymentResponseDto(savedPayment);
		} else {
			throw new DuplicateResourceException("Payment", "Payment Id", paymentRequestDto.getPaymentId());
		}

	}

	@Override
	public PaymentResponseDto getPaymentMethod(Integer paymentId) throws ResourceNotFoundException {

		Payment payment = this.paymentRepo.findById(paymentId)
				.orElseThrow(() -> new ResourceNotFoundException("Payment", "Payment Id", paymentId));

		return this.toPaymentResponseDto(payment);

	}

	@Override
	public List<PaymentResponseDto> getAllPaymentMethods() throws ResourceNotFoundException {

		List<Payment> payments = this.paymentRepo.findAll();

		List<PaymentResponseDto> list = payments.stream().map(p -> this.toPaymentResponseDto(p))
				.collect(Collectors.toList());

		return list;

	}

	@Override
	public ApiResponse deletePaymentMethod(Integer paymentId) throws ResourceNotFoundException {

		Payment payment = this.paymentRepo.findById(paymentId)
				.orElseThrow(() -> new ResourceNotFoundException("Payment", "Payment Id", paymentId));

		this.paymentRepo.delete(payment);

		return new ApiResponse(LocalDateTime.now(), "Payment Method Deleted Successfully !", true,payment);
	}

	@Override
	public PaymentResponseDto revokePaymentMethod(Integer paymentId) throws ResourceNotFoundException {

		Payment payment = this.paymentRepo.findById(paymentId)
				.orElseThrow(() -> new ResourceNotFoundException("Payment", "Payment Id", paymentId));

		if (payment.getAllowed()) {

			payment.setAllowed(false);

		} else {

			payment.setAllowed(true);
		}

		Payment savedPayment = this.paymentRepo.save(payment);

		return this.toPaymentResponseDto(savedPayment);
	}

	// Model Mapper Methods

	private PaymentResponseDto toPaymentResponseDto(Payment payment) {

		return this.modelMapper.map(payment, PaymentResponseDto.class);

	}

	private Payment toPayment(PaymentRequestDto paymentRequestDto) {

		return this.modelMapper.map(paymentRequestDto, Payment.class);

	}
}
