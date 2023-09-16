/**
 * 
 */
package com.bhushan.modelResponseDto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.bhushan.model.CartProductDetails;

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
public class CartResponseDto extends RepresentationModel<CartResponseDto>{
	
	private Integer cartId;

	private Integer cartQuantity;

	private Double cartTotalAmount;

	private List<CartProductDetails> listOfProducts = new ArrayList<>();
	
}
