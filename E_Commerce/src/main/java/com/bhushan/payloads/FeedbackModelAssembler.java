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

import com.bhushan.controllers.FeedbackController;
import com.bhushan.exceptions.ResourceNotAllowedException;
import com.bhushan.exceptions.ResourceNotFoundException;
import com.bhushan.model.Feedback;
import com.bhushan.modelResponseDto.FeedbackResponseDto;

/**
 * @author bhushan patil
 *
 */
@Component
public class FeedbackModelAssembler extends RepresentationModelAssemblerSupport<Feedback, FeedbackResponseDto> {

	@Autowired
	private ModelMapper modelMapper;

	public FeedbackModelAssembler() {

		super(FeedbackController.class, FeedbackResponseDto.class);

	}

	@Override
	public FeedbackResponseDto toModel(Feedback entity) {

		FeedbackResponseDto feedbackResponseDto = this.modelMapper.map(entity, FeedbackResponseDto.class);

		try {
			try {
				feedbackResponseDto
						.add(linkTo(methodOn(FeedbackController.class).getFeedbackById(feedbackResponseDto.getFeedbackId()))
								.withSelfRel());
			} catch (ResourceNotAllowedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}

		return feedbackResponseDto;
	}

}
