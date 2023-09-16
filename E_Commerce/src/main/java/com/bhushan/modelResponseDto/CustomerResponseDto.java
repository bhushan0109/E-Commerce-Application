/**
 * 
 */
package com.bhushan.modelResponseDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.bhushan.model.Image;
import com.fasterxml.jackson.annotation.JsonFormat;

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
public class CustomerResponseDto extends RepresentationModel<CustomerResponseDto> {

	private Integer userId;

	private String firstName;

	private String lastName;

	private String contact;

	private String email;

	private LocalDate dateOfBirth;

	private Image image;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime accountCreatedDate;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime accountUpdatedDate;

	private Set<RoleResponseDto> roles;

	private AddressResponseDto address;

}
