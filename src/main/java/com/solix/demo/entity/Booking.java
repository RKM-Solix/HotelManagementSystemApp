package com.solix.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Bookings")
@Data
@NoArgsConstructor
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING) // Store the enum as a string in the database
	@Column(name = "room_type", nullable = false)
	private Rooms roomType;
	
	
	@Column(name = "number_of_days", nullable = false) // Added number of days
    private int numberOfDays;
	
	
	@Column(name = "number_of_rooms", nullable = false)
	private int numberOfRooms;
	
	
	// Many-to-One relationship with Customer, where "customer_id" is primary key in Customer..
	// A customer can book many rooms, so many-to-one...
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	
	
	// Many-to-One relationship with HotelAdmin, where "hotel_admin_id" is primary key in HotelAdmin...
	// A hotel admin can manage multiple bookings, so many-to-one...
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hotel_admin_id", nullable = false)
	private HotelAdmin hotelAdmin;
	
	
	// One-to-One relationship with Bill
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "bill_id")
    private Bill bill;

    // One booking may consists of many orders...
    // One-to-Many relationship with Order
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();
	
	public Booking(Rooms roomType, int numberOfDays, int numberOfRooms) {
		this.roomType = roomType;
		this.numberOfDays = numberOfDays;
		this.numberOfRooms = numberOfRooms;
	}
	
	// Add and Remove Order methods to maintain bi-directional relationship
    public void addOrder(Order order) {
        this.orders.add(order);
        order.setBooking(this);
    }

    public void removeOrder(Order order) {
        this.orders.remove(order);
        order.setBooking(null);
    }
    
}
