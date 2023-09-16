/**
 * 
 */
package com.bhushan.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bhushan.exceptions.ResourceNotFoundException;
import com.bhushan.model.Category;
import com.bhushan.modelRequestDto.CategoryRequestDto;
import com.bhushan.modelRequestDto.CategoryUpdateRequestDto;
import com.bhushan.modelResponseDto.CategoryResponseDto;
import com.bhushan.payloads.ApiResponse;

/**
 * @author bhushan patil
 *
 */
public interface CategoryServices {

	List<CategoryResponseDto> getAllCategories();


	CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto);


	ApiResponse deleteCategoryById(Integer categoryId) throws ResourceNotFoundException;

	CategoryResponseDto getCategory(Integer categoryId) throws ResourceNotFoundException;

	Page<Category> getAllCategoriesByPage(Integer page, Integer size);

	Page<Category> getSortedByAnyCategoryDetailsByPage(Integer page, Integer size, String sortBy, String sortDirection);

	List<CategoryResponseDto> searchCategoriesByKeyword(String keyword);


	CategoryResponseDto updateCategory(Integer categoryId, CategoryUpdateRequestDto categoryRequestDto)
			throws ResourceNotFoundException;



}
