/**
 * 
 */
package com.bhushan.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhushan.model.Order;
import com.bhushan.model.PickUpOrderRequest;

/**
 * @author bhushan patil
 *
 */
@Repository
public interface PickUpOrderRequestRepo extends JpaRepository<PickUpOrderRequest, Integer> {

	Optional<PickUpOrderRequest> findByOrder(Order order);

	Page<PickUpOrderRequest> findByIsReturnOrderPickedUp(Boolean isReturnOrderPickedUp, Pageable pageable);

}
