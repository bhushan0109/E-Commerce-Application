/**
 * 
 */
package com.bhushan.modelResponseDto;

import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

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
public class CategoryResponseDto extends RepresentationModel<CategoryResponseDto> {

	private Integer categoryId;

	private String categoryName;

	private String categoryDescription;

	private Boolean active;

	private LocalDateTime categoryAddedDateTime;

	private LocalDateTime categoryUpdatedDateTime;
}
