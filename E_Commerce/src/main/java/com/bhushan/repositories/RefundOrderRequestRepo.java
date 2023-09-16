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
import com.bhushan.model.RefundOrderRequest;

/**
 * @author bhushan patil
 *
 */

@Repository
public interface RefundOrderRequestRepo extends JpaRepository<RefundOrderRequest, Integer> {

	Page<RefundOrderRequest> findByApproved(Boolean approved, Pageable pageable);

	Optional<RefundOrderRequest> findByOrder(Order order);
	
	Page<RefundOrderRequest> findByCustomer(Customer customer,Pageable pageable);
}
