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
import com.bhushan.model.Product;
import com.bhushan.modelRequestDto.ProductRequestDto;
import com.bhushan.modelRequestDto.ProductUpdateRequestDto;
import com.bhushan.modelResponseDto.ProductResponseDto;
import com.bhushan.payloads.ApiResponse;

/**
 * @author bhushan patil
 *
 */
public interface ProductServices {

	// Change availability automatically
	// NewlyAddedByCategory

	List<ProductResponseDto> getAllProducts();

	ProductResponseDto addProduct(Integer categoryId, ProductRequestDto productRequestDto, MultipartFile[] images)
			throws ResourceNotFoundException, ResourceNotAllowedException, IOException, FileTypeNotValidException;

	ApiResponse deleteProductById(Integer productId) throws ResourceNotFoundException;

	ProductResponseDto getProductById(Integer productId) throws ResourceNotFoundException;

	Page<Product> getProductsByPage(Integer page, Integer size);

	Page<Product> getSortedProductsByRating(Integer page, Integer size, String sortDirection);

	Page<Product> getSortedProductsBySalePrice(Integer page, Integer size, String sortDirection);

	Page<Product> getSortedProductsByManufacturingYear(Integer page, Integer size, String sortDirection);

	Page<Product> getSortedProductsByManufacturingMonth(Integer page, Integer size, String sortDirection);

	Page<Product> getSortedByAnyProductDetailsByPage(Integer page, Integer size, String sortDirection, String sortBy);

	Page<Product> getSortedProductsByAddedDate(Integer page, Integer size, String sortDirection);

	List<ProductResponseDto> searchProductsByProductNameKeyword(String keyword);

	ProductResponseDto updateProductCategory(Integer productId, Integer categoryId)
			throws ResourceNotFoundException, ResourceNotAllowedException;

	List<ProductResponseDto> searchByCategory(Integer categoryId) throws ResourceNotFoundException;

	List<ProductResponseDto> searchByTypeKeyword(String keyword) throws ResourceNotFoundException;

	ProductResponseDto updateProductDetails(Integer productId, ProductUpdateRequestDto productRequestDto)
			throws ResourceNotFoundException;

	ProductResponseDto revokeProductOnDiscountSale(Integer productId) throws ResourceNotFoundException;

	ProductResponseDto revokeAvailability(Integer productId) throws ResourceNotFoundException;

	ProductResponseDto revokeBuyersChoice(Integer productId) throws ResourceNotFoundException;

	ProductResponseDto updateProductRating(Integer productId, Double rating) throws ResourceNotFoundException;

	ProductResponseDto updateProductStockQuantity(Integer productId, Integer quantity) throws ResourceNotFoundException;

	Page<Product> findByCategorySortByRating(Integer categoryId, Integer page, Integer size, String sortDirection)
			throws ResourceNotFoundException;

	Page<Product> findByCategorySortByProductAddedDate(Integer categoryId, Integer page, Integer size,
			String sortDirection) throws ResourceNotFoundException;

	ProductResponseDto updateProductImage(MultipartFile[] image, Integer productId)
			throws ResourceNotFoundException, IOException, FileTypeNotValidException;

	void serveProductImage(String imageName, HttpServletResponse response)
			throws ResourceNotFoundException, IOException, FileTypeNotValidException;

	ApiResponse serveImageByProductId(Integer productId) throws IOException, ResourceNotFoundException;

	ApiResponse deleteProductImage(String fileName) throws IOException;

	Page<Product> getProductsOnDiscountSale(Integer page, Integer size, String sortBy, String sortDirection);

	Page<Product> getProductsOnBuyersChoice(Integer page, Integer size, String sortBy, String sortDirection);

	Page<Product> getProductsByDiscountPercentage(Integer discountPercentage, Integer page, Integer size, String sortBy,
			String sortDirection);

	// highest sold product
	ApiResponse highestSoldProduct();

	// Least sold product
	ApiResponse leastSoldProduct();

	// highest sold product ordered by rating in given duration
	Page<Product> highestSoldProductSortedByRating(Double rating, Integer page, Integer size, String sortDirection);

	// highest sold product categorised under selling price
	Page<Product> findBySalePriceOrderByTotalSales(Double salePrice, Integer page, Integer size, String sortDirection);
}
