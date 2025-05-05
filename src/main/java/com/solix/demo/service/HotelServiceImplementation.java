package com.solix.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solix.demo.entity.Bill;
import com.solix.demo.entity.Booking;
import com.solix.demo.entity.Customer;
import com.solix.demo.entity.FoodItemQuantity;
import com.solix.demo.entity.HotelAdmin;
import com.solix.demo.entity.Order;
import com.solix.demo.entity.Rooms;
import com.solix.demo.repository.BillRepository;
import com.solix.demo.repository.BookingRepository;
import com.solix.demo.repository.CustomerRepository;
import com.solix.demo.repository.HotelAdminRepository;
import com.solix.demo.repository.OrderRepository;

@Service
public class HotelServiceImplementation implements HotelService{

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	HotelAdminRepository hotelAdminRepository;
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	BillRepository billRepository;
	
	@Override
	public Booking createBookingAndBill(Long customerId, Long hotelAdminId, Rooms roomType, int numberOfDays,
			int numberOfRooms) {
		
		Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found with ID : " + customerId));
		HotelAdmin hotelAdmin = hotelAdminRepository.findById(hotelAdminId).orElseThrow(() -> new RuntimeException("Hotel Admin not found with ID : " + hotelAdminId));
		
		Booking booking = new Booking(roomType, numberOfDays, numberOfRooms);
		booking.setCustomer(customer);
		booking.setHotelAdmin(hotelAdmin);
		
		booking = bookingRepository.save(booking);
		
		// Calculate total amount for the booking
		double totalAmount = roomType.getPrice() * numberOfRooms + numberOfDays;
		
		Bill bill = new Bill();
		bill.setTotalAmount(totalAmount);
		bill.setPaymentStatus("Pending");
		bill.setBooking(booking);
		bill = billRepository.save(bill);
		
		booking.setBill(bill);
	    bookingRepository.save(booking);
		
		return booking;
	}

	
	@Override
	public Order createOrderAndBill(Long customerId, List<FoodItemQuantity> foodItems, Long bookingId) {
		Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found with ID : " + customerId));
		Booking booking = null;
		
		if(bookingId != null) {
			booking = bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking not found with ID : " + bookingId));
		}
		
		Order order = new Order();
        order.setFoodItems(foodItems);
        order.setCustomer(customer);
        order.setBooking(booking); // add booking if ID is given.
        order = orderRepository.save(order);
        
        // Calculate total amount for the order...
        double totalAmount = foodItems.stream()
        		             .mapToDouble(item -> item.getFoodItem().getPrice() * item.getQuantity())
        		             .sum();
        
        Bill bill = new Bill();
        bill.setTotalAmount(totalAmount);
        bill.setPaymentStatus("Pending");
        bill.setOrder(order);
        bill = billRepository.save(bill);
        
        order.setBill(bill);
        orderRepository.save(order);
        
        // update the booking...
        if(booking != null) {
        	booking.addOrder(order);
        	bookingRepository.save(booking);
        }
        
		return order;
	}

	
	@Override
	public Bill getBillByBookingId(Long bookingId) {
		
		Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking not found with ID : " + bookingId));
		
		return billRepository.findByBooking(booking).orElse(null);
	}

	
	@Override
	public Bill getBillByOrderId(Long orderId) {
		
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found with ID : " + orderId));
		
		return billRepository.findByOrder(order).orElse(null);
	}

	@Override
	public List<Bill> getAllBills() {
		
		return billRepository.findAll();
	}

	//update bill payment status...
	@Override
	public Bill updateBillPaymentStatus(Long billId, String paymentStatus) {
		
		Bill bill = billRepository.findById(billId).orElseThrow(() -> new RuntimeException("Bill not found with id: " + billId));
		
		bill.setPaymentStatus(paymentStatus);
		return billRepository.save(bill);
	}

	@Override
	public List<Order> getOrdersByBookingId(Long bookingId) {
		Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking not found with ID : " + bookingId));
		
		return booking.getOrders();
	}

}
