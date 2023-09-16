package com.bhushan.modelResponseDto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.bhushan.model.Image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto extends RepresentationModel<ReviewResponseDto> {

	private Integer reviewId;

	private String customerReview;

	private Double customerRating;

	private List<Image> images;
	
	private LocalDateTime reviewTimeStamp;

	private CustomerDetailsResponseDto customer;

}
