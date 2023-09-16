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
import com.bhushan.exceptions.ResourceNotAllowedException;
import com.bhushan.exceptions.ResourceNotFoundException;
import com.bhushan.model.Review;
import com.bhushan.modelRequestDto.ReviewRequestDto;
import com.bhushan.modelResponseDto.ReviewResponseDto;
import com.bhushan.payloads.ApiResponse;

/**
 * @author bhushan patil
 *
 */

public interface ReviewServices {

	List<ReviewResponseDto> getReviewsByProduct(Integer productId) throws ResourceNotFoundException;

	List<ReviewResponseDto> getReviewsByCustomer(String contact) throws ResourceNotFoundException;

	ReviewResponseDto getReviewById(Integer reviewId) throws ResourceNotFoundException;

	ReviewResponseDto addReview(String contact, Integer productId, ReviewRequestDto reviewRequestDto)
			throws ResourceNotFoundException;

	ReviewResponseDto addReviewWithImages(String contact, Integer productId, ReviewRequestDto reviewRequestDto,
			MultipartFile[] images)
			throws ResourceNotFoundException, ResourceNotAllowedException, IOException, FileTypeNotValidException;

	Page<Review> getAllReviewsByProductOrderedByDate(Integer productId, Integer page, Integer size)
			throws ResourceNotFoundException;

	Page<Review> getAllReviewsByProductOrderedByRating(Integer productId, Integer page, Integer size,
			String sortDirection) throws ResourceNotFoundException;

	Page<Review> getAllReviewsSortedByRating(Integer page, Integer size, String sortDirection);

	void serveReviewImage(String imageName, HttpServletResponse response)
			throws ResourceNotFoundException, IOException, FileTypeNotValidException;

	ApiResponse deleteReview(Integer reviewId) throws ResourceNotFoundException, IOException;

	Page<Review> getAllReviews(Integer page, Integer size);
}
