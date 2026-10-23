package com.example.SafarNow.Transformer;

import com.example.SafarNow.Dto.Request.CustomerRequest;
import com.example.SafarNow.Dto.Response.CustomerResponse;
import com.example.SafarNow.Model.Customer;

public class CustomerTransformer {

    public static Customer customerRequestToCustomer(CustomerRequest customerRequest){

        Customer customer = Customer.builder()
                .name(customerRequest.getName())
                .age(customerRequest.getAge())
                .emailId(customerRequest.getEmailId())
                .gender(customerRequest.getGender()).build();

//        customer.setName(customerRequest.getName());
//        customer.setAge(customerRequest.getAge());
//        customer.setEmailId(customerRequest.getEmailId());
//        customer.setGender(customerRequest.getGender());

        return customer;
    }

    public static CustomerResponse customerToCustomerResponse(Customer savedCustomer){

        CustomerResponse customerResponse = CustomerResponse.builder()
                .name(savedCustomer.getName())
                .age(savedCustomer.getAge())
                .emailId(savedCustomer.getEmailId()).build();

//        customerResponse.setName(savedCustomer.getName());
//        customerResponse.setAge(savedCustomer.getAge());
//        customerResponse.setEmailId(savedCustomer.getEmailId());
//
        return customerResponse;
    }
}


