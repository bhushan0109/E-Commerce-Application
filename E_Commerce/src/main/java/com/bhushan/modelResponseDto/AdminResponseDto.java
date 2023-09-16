package com.bhushan.modelResponseDto;

import java.util.HashSet;
import java.util.Set;

import com.bhushan.model.Role;

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
public class AdminResponseDto{

	private Integer userId;

	private String firstName;

	private String lastName;
	
	private String contact;

	private String email;
	
	private Set<Role> roles = new HashSet<>();
	
}
