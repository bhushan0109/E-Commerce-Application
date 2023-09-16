/**
 * 
 */
package com.bhushan.services;

import java.util.List;

import com.bhushan.exceptions.ResourceNotFoundException;
import com.bhushan.modelRequestDto.AdminRequestDto;
import com.bhushan.modelRequestDto.AdminUpdateRequestDto;
import com.bhushan.modelResponseDto.AdminResponseDto;
import com.bhushan.payloads.ApiResponse;

/**
 * @author bhushan patil
 *
 */
public interface AdminServices {

	AdminResponseDto registerAdmin(AdminRequestDto adminRequestDto) throws ResourceNotFoundException;

	AdminResponseDto updateAdminDetails(AdminUpdateRequestDto userdto, Integer adminId)
			throws ResourceNotFoundException;

	AdminResponseDto getAdminDetailsById(Integer adminId) throws ResourceNotFoundException;

	List<AdminResponseDto> getAllAdmins();

	ApiResponse deleteAdminById(Integer adminId) throws ResourceNotFoundException;
	
	
}
