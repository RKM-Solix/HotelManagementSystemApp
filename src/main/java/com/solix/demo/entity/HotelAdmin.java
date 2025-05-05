package com.solix.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hotel_admin")
@Data
@NoArgsConstructor
public class HotelAdmin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	@Column(name = "hotel_name", nullable = false)
	private String hotelname;

	public HotelAdmin(String username, String hotelname) {
		this.username = username;
		this.hotelname = hotelname;
	}

	
	
	
	
}
