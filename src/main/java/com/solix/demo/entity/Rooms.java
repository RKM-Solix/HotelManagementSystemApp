package com.solix.demo.entity;


public enum Rooms {

	SINGLE("Single Room", 25000, 5),
    TWIN("Twin Room", 30000, 10),
    QUEEN("Queen Room", 35000, 8),
    KING("King Room", 40000, 7);

    private String roomName;
    private int price; 
    private int availableRooms;
    
	private Rooms(String roomName, int price, int availableRooms) {
		this.roomName = roomName;
		this.price = price;
		this.availableRooms = availableRooms;
	}

	public String getRoomName() {
		return roomName;
	}


	public int getPrice() {
		return price;
	}


	public int getAvailableRooms() {
		return availableRooms;
	}
    
}
