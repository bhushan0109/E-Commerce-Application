/**
 * 
 */
package com.bhushan.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhushan.model.Order;
import com.bhushan.model.RefundOrderRequest;
import com.bhushan.model.ReplaceOrderRequest;
import com.bhushan.model.ReturnOrderRequest;

/**
 * @author bhushan patil
 *
 */
@Repository
public interface ReturnOrderRequestRepo extends JpaRepository<ReturnOrderRequest, Integer> {

	Optional<ReturnOrderRequest> findByOrder(Order order);
	
	Optional<ReturnOrderRequest> findByReplaceOrderRequest(ReplaceOrderRequest replaceOrderRequest);
	
	Optional<ReturnOrderRequest> findByRefundOrderRequest(RefundOrderRequest refundOrderRequest);
}
