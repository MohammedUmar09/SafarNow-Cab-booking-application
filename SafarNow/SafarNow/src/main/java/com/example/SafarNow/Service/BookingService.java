package com.example.SafarNow.Service;

import com.example.SafarNow.Dto.Request.BookingRequest;
import com.example.SafarNow.Dto.Response.BookingResponse;
import com.example.SafarNow.Exception.CabUnavailableException;
import com.example.SafarNow.Exception.CustomerNotFoundException;
import com.example.SafarNow.Model.Booking;
import com.example.SafarNow.Model.Cab;
import com.example.SafarNow.Model.Customer;
import com.example.SafarNow.Model.Driver;
import com.example.SafarNow.Repository.BookingRepository;
import com.example.SafarNow.Repository.CabRepository;
import com.example.SafarNow.Repository.CustomerRepository;
import com.example.SafarNow.Repository.DriverRepository;
import com.example.SafarNow.Transformer.BookingTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    CabRepository cabRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public BookingResponse bookCab(BookingRequest bookingRequest, int customerId) {

        //checking customer valid or not
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Invalid customer Id!!");
        }

        Customer customer = optionalCustomer.get();

        //book cab
        Cab availableCab = cabRepository.getAvailableCab();
        if(availableCab == null){
            throw new CabUnavailableException("Cabs are Unavailable...");
        }

        //RequestDto-->BookingEntity
        Booking booking = BookingTransformer.bookingRequestToBooking(bookingRequest, availableCab.getPerKmRate());

        //saving booking into db since this creates a P.K for it
        Booking savedBooking = bookingRepository.save(booking);


        //things to do after booking a cab
        availableCab.setAvailable(false);
        customer.getBookings().add(savedBooking);
        Driver driver = driverRepository.getDriverbyCabId(availableCab.getCabId());
        driver.getBookings().add(savedBooking);

        //save customer(parent), driver(parent) of booking(child)
        Customer savedCustomer = customerRepository.save(customer);
        Driver savedDriver = driverRepository.save(driver);

        sendEmail(savedCustomer, savedBooking);

        //-->bookingResponse
        return BookingTransformer.bookingToBookingResponse(savedBooking, savedCustomer, driver);
    }

    private void sendEmail(Customer customer,Booking booking){

        String text = """
        Hi %s,

        Your cab booking has been successfully confirmed! 🚕✨

        Booking ID: %s
        Pickup: %s
        Destination: %s
        Fare: ₹%s

        Thank you for choosing SafarNow.
        Have a safe and comfortable journey!

        Regards,
        Muhammad Umar
        SafarNow Team
        """.formatted(
                customer.getName(),
                booking.getBookingId(),
                booking.getPickUp(),
                booking.getDestination(),
                booking.getBillAmount()
        );

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();//inBuild cls from mail dependency
        simpleMailMessage.setFrom("zaynm1832@gmail.com");
        simpleMailMessage.setTo(customer.getEmailId());
        simpleMailMessage.setSubject("Cab Booked!!");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);
    }
}
