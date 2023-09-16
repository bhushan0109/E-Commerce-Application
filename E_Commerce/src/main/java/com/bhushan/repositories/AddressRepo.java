/**
 * 
 */
package com.bhushan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhushan.model.Address;

/**
 * @author bhushan patil
 *
 */
@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {

}
