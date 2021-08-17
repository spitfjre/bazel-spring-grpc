package com.spitfjre.pet.dao;

import com.spitfjre.pet.entity.CategoryDbo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<CategoryDbo, Long> {}
