package com.bhushan.modelRequestDto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateRequestDto {

	private String firstName;

	private String lastName;

	@Size(max = 12, message = "Invalid Contact,Minimum Length Should be 10")
	private String contact;

	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE, message = "{Customer.email.invalid}")
	private String email;

	@Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){6,12}$", message = "Password must contain 1 number (0-9),1 Uppercase letter,1 Lowercase letter,1 non-alpha Numeric Number & should be Min 6 , Max 12 characters with no space")
	private String password;

	@Past(message = "Date of Birth Should be in Past")
	private LocalDate dateOfBirth;

}
