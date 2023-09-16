/**
 * 
 */
package com.bhushan.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhushan.model.Customer;
import com.bhushan.model.Order;
import com.bhushan.model.ReplaceOrderRequest;

/**
 * @author bhushan patil
 *
 */
@Repository
public interface ReplaceOrderRequestRepo extends JpaRepository<ReplaceOrderRequest, Integer> {

	Page<ReplaceOrderRequest> findByApproved(Boolean approved, Pageable pageable);
	
	Optional<ReplaceOrderRequest> findByOrder(Order order);
	
	Page<ReplaceOrderRequest> findByCustomer(Customer customer, Pageable pageable);
}
