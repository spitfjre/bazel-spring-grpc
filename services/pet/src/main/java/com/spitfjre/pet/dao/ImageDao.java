package com.spitfjre.pet.dao;

import com.spitfjre.pet.entity.ImageDbo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageDao extends JpaRepository<ImageDbo, Long> {}
