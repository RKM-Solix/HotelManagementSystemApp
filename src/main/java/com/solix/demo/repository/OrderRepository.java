package com.solix.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solix.demo.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
