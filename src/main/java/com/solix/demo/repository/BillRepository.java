package com.solix.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solix.demo.entity.Bill;
import com.solix.demo.entity.Booking;
import com.solix.demo.entity.Order;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

	Optional<Bill> findByBooking(Booking booking);

    Optional<Bill> findByOrder(Order order);
}
