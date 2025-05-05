package com.solix.demo.service;

import java.util.List;

import com.solix.demo.entity.Bill;
import com.solix.demo.entity.Booking;
import com.solix.demo.entity.FoodItemQuantity;
import com.solix.demo.entity.Order;
import com.solix.demo.entity.Rooms;

public interface HotelService {

	// Method to create a booking and associated bill
    public Booking createBookingAndBill(Long customerId, 
    		                            Long hotelAdminId, 
    		                            Rooms roomType,  
    		                            int numberOfDays, 
    		                            int numberOfRooms);
    
    
    // Method to create an order and associated bill
    public Order createOrderAndBill(Long customerId, 
    		                        List<FoodItemQuantity> foodItems,
    		                        Long bookingId);
    
    
    // Method to get bill by booking id
    public Bill getBillByBookingId(Long bookingId);
    
    
    // Method to get bill by order id
    public Bill getBillByOrderId(Long orderId);
    
    //method to get all bills
    public List<Bill> getAllBills();
    
    //update bill
    public Bill updateBillPaymentStatus(Long billId, String paymentStatus);
    
    // Method to get orders by booking id
    public List<Order> getOrdersByBookingId(Long bookingId);
    
}
