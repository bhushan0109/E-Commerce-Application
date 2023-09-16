package com.bhushan.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhushan.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

	List<Category> findByCategoryNameContaining(String keyword);

	Boolean existsByCategoryNameIgnoreCase(String categoryName);
}
