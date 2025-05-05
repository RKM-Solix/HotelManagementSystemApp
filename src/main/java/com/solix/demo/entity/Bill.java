package com.solix.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


// Bill = Booking(RoomType with no. of rooms) + Order(foodItems)... Amount
@Entity
@Table(name = "bill")
@Data
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "total_amount", nullable = false)
	private double totalAmount;
	
	@Column(name = "payment_status", nullable = false)
    private String paymentStatus;  // e.g., "Pending", "Paid", "Cancelled"
	
	
	// One-to-One relationship with Booking
    @OneToOne(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    private Booking booking;
    
    
    // One-to-One relationship with Order
    @OneToOne(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    private Order order;
    
    
    // Many-to-One relationship with HotelAdmin (optional, for who issued the bill)
    // Many bills can be issued by the same hotel administrator.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_admin_id")
    private HotelAdmin hotelAdmin;
    
    
}
