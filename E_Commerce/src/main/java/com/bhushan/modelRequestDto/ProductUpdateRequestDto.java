/**
 * 
 */
package com.bhushan.modelRequestDto;

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
public class ProductUpdateRequestDto {

	private String productName;

	private String productDescription;

	private String productTechnicalDetails;

	private String brand;

	private String type;

	private Double marketPrice;

	private Integer discountPercentage;

	private Integer stockQuantity;

	private Double rating;

	private Integer manufacturingYear;

	private Integer manufacturingMonth;

}
