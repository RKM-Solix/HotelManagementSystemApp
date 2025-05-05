package com.solix.demo.entity;

public enum FoodMenu {

	// Tiffins
    IDLI("Idli (2 pcs)", 60),
    MASALA_DOSA("Masala Dosa", 120),
    POORI("Poori (2 pcs)", 110),
    UPMA("Vegetable Upma", 90),
    VADA("Medu Vada (2 pcs)", 100),

    // Meals
    VEG_MEALS("Vegetarian Meals", 180),
    SAMBAR_RICE("Sambar Rice", 120),
    CURD_RICE("Curd Rice", 100),

    // Biryani 
    VEG_BIRYANI("Vegetable Biryani", 250),

    // Snacks
    MIRCHI_BAJJI("Mirchi Bajji (2 pcs)", 60),
    ONION_BAJJI("Onion Bajji", 70),

    // Beverages
    TEA("Tea", 30),
    COFFEE("Coffee", 40),
    LASSI("Sweet Lassi", 80);
	
	
	private String itemName;
    private int price;
    
    
	private FoodMenu(String itemName, int price) {
		this.itemName = itemName;
		this.price = price;
	}

	public String getItemName() {
		return itemName;
	}

	
	public int getPrice() {
		return price;
	}

	
	

}
