package com.solix.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Customers")
@Data
@NoArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String email;
	
	
	// Bi-directional relationship with Booking
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", orphanRemoval = true)
	private List<Booking> bookings = new ArrayList<>();
	
	
	 // Bi-directional relationship with Order
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();
	
	
	public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }
	
	// Add and Remove Booking methods to maintain bi-directional relationship
    public void addBooking(Booking booking) {
        this.bookings.add(booking);
        booking.setCustomer(this);
    }

    public void removeBooking(Booking booking) {
        this.bookings.remove(booking);
        booking.setCustomer(null);
    }
    
    
    // Add and Remove Order methods
    public void addOrder(Order order) {
        this.orders.add(order);
        order.setCustomer(this);
    }

    public void removeOrder(Order order) {
        this.orders.remove(order);
        order.setCustomer(null);
    }

	
}
