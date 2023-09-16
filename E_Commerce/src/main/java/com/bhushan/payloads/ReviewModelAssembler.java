/**
 * 
 */
package com.bhushan.payloads;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.bhushan.controllers.ReviewController;
import com.bhushan.exceptions.ResourceNotAllowedException;
import com.bhushan.exceptions.ResourceNotFoundException;
import com.bhushan.model.Review;
import com.bhushan.modelResponseDto.ReviewResponseDto;

/**
 * @author bhushan patil
 *
 */
@Component
public class ReviewModelAssembler extends RepresentationModelAssemblerSupport<Review, ReviewResponseDto> {

	@Autowired
	private ModelMapper modelMapper;

	public ReviewModelAssembler() {

		super(ReviewController.class, ReviewResponseDto.class);

	}

	@Override
	public ReviewResponseDto toModel(Review entity) {

		ReviewResponseDto reviewResponseDto = this.modelMapper.map(entity, ReviewResponseDto.class);

		try {
			try {
				reviewResponseDto
						.add(linkTo(methodOn(ReviewController.class).getReviewById(reviewResponseDto.getReviewId()))
								.withSelfRel());
			} catch (ResourceNotAllowedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}

		return reviewResponseDto;
	}

}
