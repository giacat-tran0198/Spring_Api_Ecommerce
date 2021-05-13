package com.crm.gestionstock.repository;

import com.crm.gestionstock.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Integer, Category> {
}
