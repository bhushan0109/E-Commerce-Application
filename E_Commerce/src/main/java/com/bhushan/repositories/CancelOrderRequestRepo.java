/**
 * 
 */
package com.bhushan.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhushan.model.CancelOrderRequest;
import com.bhushan.model.Order;

/**
 * @author bhushan patil
 *
 */
@Repository
public interface CancelOrderRequestRepo extends JpaRepository<CancelOrderRequest, Integer> {
	
	Optional<CancelOrderRequest> findByOrder(Order order);
	
	
}
