/**
 * 
 */
package com.bhushan.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhushan.model.Customer;
import com.bhushan.model.Feedback;
import com.bhushan.model.Order;

/**
 * @author bhushan patil
 *
 */
@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {

	Page<Feedback> findByCustomer(Customer customer, Pageable pageable);

	Page<Feedback> findByOrder(Order order, Pageable pageable);

}
