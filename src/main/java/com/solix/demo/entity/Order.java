package com.solix.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
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


@Entity
@Table(name = "food_order")
@Data
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	// JPA will create a "order_food_items" table with consists of foodmenu and quantity, and order_id...
	// "FoodItemQuantity" is an entity class, and it's an embedded class of Order, consists of "order_id" as it's foreign key...
	@ElementCollection
	@CollectionTable(name = "order_food_items", joinColumns = @JoinColumn(name = "order_id"))
	private List<FoodItemQuantity> foodItems = new ArrayList<>();
	
	
	// Many-to-One relationship with Customer
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	
	
	// Many-to-One relationship with Booking (optional)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;
    
    
	// One-to-One relationship with Bill
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "bill_id")
	private Bill bill;
	
}
