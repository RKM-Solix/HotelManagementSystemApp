package com.solix.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solix.demo.entity.Bill;
import com.solix.demo.entity.Booking;
import com.solix.demo.entity.FoodItemQuantity;
import com.solix.demo.entity.Order;
import com.solix.demo.entity.Rooms;
import com.solix.demo.service.HotelServiceImplementation;

@RestController
@RequestMapping("/api")
public class HotelRESTController {

	@Autowired
	HotelServiceImplementation hotelService;
	
	// New endpoint to create a booking and bill....
	@PostMapping("/bookings/customers/{customerId}/hotelAdmins/{hotelAdminId}")
	public Booking createBookingAndBill(
			@PathVariable Long customerId,
			@PathVariable Long hotelAdminId,
			@RequestParam Rooms roomType,
			@RequestParam int numberOfDays,
			@RequestParam int numberOfRooms) {
		return hotelService.createBookingAndBill(customerId, hotelAdminId, roomType, numberOfDays, numberOfRooms);
	}
	
	
	// New endpoint to create an order and bill, with optional bookingId
	@PostMapping("/orders/customers/{customerId}")
	public Order createOrderAndBill(
			@PathVariable Long customerId,
			@RequestBody List<FoodItemQuantity> foodItems,
			@RequestParam(required = false) Long bookingId) {
		return hotelService.createOrderAndBill(customerId, foodItems, bookingId);
	}
	
	// New endpoint to get bill by booking id...
	@GetMapping("/bills/bookings/{bookingId}")
	public Bill getBillByBookingId(@PathVariable Long bookingId) {
		return hotelService.getBillByBookingId(bookingId);
	}
	
	
	// New endpoint to get bill by order id...
	@GetMapping("/bills/orders/{orderId}")
	public Bill getBillByOrderId(@PathVariable Long orderId) {
		return hotelService.getBillByOrderId(orderId);
	}
	
	@GetMapping("/bills")
	public List<Bill> getAllBills() {
		return hotelService.getAllBills();
	}
	
	//update bill payment status
    @PutMapping("/bills/{billId}")
	public Bill updateBillPaymentStatus(@PathVariable Long billId, @RequestParam String paymentStatus) {
    	return hotelService.updateBillPaymentStatus(billId, paymentStatus);
    }
    
    
    // New endpoint to get orders by bookingId
    @GetMapping("/bookings/{bookingId}/orders")
    public List<Order> getOrdersByBookingId(@PathVariable Long bookingId) {
        return hotelService.getOrdersByBookingId(bookingId);
    }
	
	
}
