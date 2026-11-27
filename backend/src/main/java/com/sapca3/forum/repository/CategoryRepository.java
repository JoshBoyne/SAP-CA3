package com.sapca3.forum.repository;

import com.sapca3.forum.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long>{
    Optional<Category> findByName(String name);
}