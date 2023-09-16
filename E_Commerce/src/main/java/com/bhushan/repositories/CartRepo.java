/**
 * 
 */
package com.bhushan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhushan.model.Cart;

/**
 * @author bhushan patil
 *
 */
@Repository
public interface CartRepo extends JpaRepository<Cart, Integer>{
	
	
	
}
