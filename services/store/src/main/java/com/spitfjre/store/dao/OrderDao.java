package com.spitfjre.store.dao;

import com.spitfjre.store.entity.OrderDbo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<OrderDbo, Long> {}
