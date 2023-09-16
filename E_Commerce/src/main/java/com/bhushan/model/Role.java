/**
 * 
 */
package com.bhushan.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bhushan patil
 *
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Role {

	@Id
	private Integer roleId;

	private String roleName;

}
