package com.bhushan.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiCallHistoryRepository extends JpaRepository<ApiCallHistory, Long> {

}
