/**
 * 
 */
package com.bhushan.modelResponseDto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bhushan patil
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefundOrderResponseDto {

	private Integer refundOrderRequestId;

	private LocalDateTime refundTimestamp;
	
	private LocalDateTime refundUpdatedTimeStamp;

	private Double orderTotalAmount;

	private Boolean approved;

	private String status;
	
	private PaymentResponseDto payment;

}
