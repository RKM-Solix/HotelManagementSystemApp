package com.solix.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemQuantity {

	@Enumerated(EnumType.STRING)
	@Column(name = "food_item")
	private FoodMenu foodItem;
	
	
	@Column(name = "quantity")
	private int quantity;
	
	
}
