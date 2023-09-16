/**
 * 
 */
package com.bhushan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhushan.model.Role;

/**
 * @author bhushan patil
 *
 */
@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

}
