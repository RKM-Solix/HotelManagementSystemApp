package com.solix.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solix.demo.entity.HotelAdmin;

@Repository
public interface HotelAdminRepository extends JpaRepository<HotelAdmin, Long> {

}
