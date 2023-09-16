/**
 * 
 */
package com.bhushan.modelResponseDto;

import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import com.bhushan.model.Image;

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
public class FeedbackResponseDto extends RepresentationModel<FeedbackResponseDto> {

	private Integer feedbackId;

	private String customerFeedback;

	private Boolean deliveredOrderFeedback;

	private Boolean cancelOrderFeedback;

	private Boolean replacementOrderFeedback;

	private Double rating;

	private Image image;

	private LocalDateTime feebackTimeStamp;

	private CustomerDetailsResponseDto customer;

	private OrderDetailsResponseDto order;

}
