package com.example.SafarNow.Transformer;

import com.example.SafarNow.Dto.Request.BookingRequest;
import com.example.SafarNow.Dto.Response.BookingResponse;
import com.example.SafarNow.Enum.TripStatus;
import com.example.SafarNow.Model.Booking;
import com.example.SafarNow.Model.Customer;
import com.example.SafarNow.Model.Driver;

public class BookingTransformer {

    public static Booking bookingRequestToBooking(BookingRequest bookingRequest, double perKmRate){

        return Booking.builder()
                .pickUp(bookingRequest.getPickUp())
                .destination(bookingRequest.getDestination())
                .tripDistanceInKm(bookingRequest.getTripDistanceInKm())
                .tripStatus(TripStatus.IN_PROGESS).
                billAmount(bookingRequest.getTripDistanceInKm() * perKmRate).build();
    }

    public static BookingResponse bookingToBookingResponse(Booking booking, Customer savedCustomer, Driver driver){

        return BookingResponse.builder()
                .pickUp(booking.getPickUp())
                .destination(booking.getDestination())
                .tripDistanceInKm(booking.getTripDistanceInKm())
                .tripStatus(booking.getTripStatus())
                .billAmount(booking.getTripDistanceInKm() * driver.getCab().getPerKmRate())
                .bookedAt(booking.getBookedAt())
                .lastUpdateAt(booking.getLastUpdateAt())
                .customerResponse(CustomerTransformer.customerToCustomerResponse(savedCustomer))
                .cabResponse(CabTransformer.cabToCabResponse(driver.getCab(), driver)).build();
    }

}
