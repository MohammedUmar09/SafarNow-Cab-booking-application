package com.example.SafarNow.Controller;

import com.example.SafarNow.Dto.Request.BookingRequest;
import com.example.SafarNow.Dto.Response.BookingResponse;
import com.example.SafarNow.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;


    @PostMapping("/book/cab/{customerId}")
    public BookingResponse bookCab(@RequestBody BookingRequest bookingRequest,
                                   @PathVariable("customerId") int customerId){
        return bookingService.bookCab(bookingRequest, customerId);
    }
}
