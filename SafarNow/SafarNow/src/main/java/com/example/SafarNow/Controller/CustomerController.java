package com.example.SafarNow.Controller;

import com.example.SafarNow.Dto.Request.CustomerRequest;
import com.example.SafarNow.Dto.Response.CustomerResponse;
import com.example.SafarNow.Enum.Gender;
import com.example.SafarNow.Model.Customer;
import com.example.SafarNow.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;//bean

    @PostMapping("/add-customer")
    public CustomerResponse addCustomer(@RequestBody CustomerRequest customerRequest){
        return customerService.addCustomer(customerRequest);
    }

    @GetMapping("/get/customer-id/{id}")
    public CustomerResponse getCustomer(@PathVariable("id") int customerId){
        return customerService.getCustomer(customerId);
    }

    //get customer by gender
    @GetMapping("/get/customerByGender/{gender}")
    public List<CustomerResponse> getCustomerByGender(@PathVariable("gender") Gender gender){
        return customerService.getCustomerByGender(gender);
    }

    //get customer by gender and age
    @GetMapping("/get/customerByGenderAndAge")
    public List<CustomerResponse> getCustomerByGenderAndAge(@RequestParam Gender gender,
                                                            @RequestParam int age){
        return customerService.getByGenderAndAge(gender, age);
    }

    //get customer with gender's age>input
    @GetMapping("/get/customerAgeGreaterThan")
    public List<CustomerResponse> getAgeGreaterThan(@RequestParam Gender gender, @RequestParam int age){
        return customerService.getAgeGreaterThan(gender, age);
    }
}