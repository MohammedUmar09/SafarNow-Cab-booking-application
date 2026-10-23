package com.example.SafarNow.Service;

import com.example.SafarNow.Dto.Request.CustomerRequest;
import com.example.SafarNow.Dto.Response.CustomerResponse;
import com.example.SafarNow.Enum.Gender;
import com.example.SafarNow.Exception.CustomerNotFoundException;
import com.example.SafarNow.Model.Customer;
import com.example.SafarNow.Repository.CustomerRepository;
import com.example.SafarNow.Transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;//bean


    public CustomerResponse addCustomer(CustomerRequest customerRequest) {

        //RequestDto-->CustomerEntity

        Customer customer = CustomerTransformer.customerRequestToCustomer(customerRequest);

        Customer savedCustomer = customerRepository.save(customer);

        //CustomerEntity-->ResponseDto
        CustomerResponse customerResponse = CustomerTransformer.customerToCustomerResponse(savedCustomer);


        return customerResponse;
    }

    public CustomerResponse getCustomer(int customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Invalid customer id");
        }

        Customer savedCustomer = optionalCustomer.get();

        //CustomerEntity-->ResponseDto
        return CustomerTransformer.customerToCustomerResponse(savedCustomer);
    }

    public List<CustomerResponse> getCustomerByGender(Gender gender) {
        List<Customer> customers = customerRepository.findByGender(gender);
        //Entity--->responseDto
        List<CustomerResponse> customerResponse = new ArrayList<>();

        for(Customer customer: customers){
            customerResponse.add(CustomerTransformer.customerToCustomerResponse(customer));
        }
        return customerResponse;
    }

    public List<CustomerResponse> getByGenderAndAge(Gender gender, int age) {
        List<Customer> customers = customerRepository.findByGenderAndAge(gender, age);

        //Entity-->responseDto
        List<CustomerResponse> customerResponse = new ArrayList<>();

        for(Customer customer: customers){
            customerResponse.add(CustomerTransformer.customerToCustomerResponse(customer));
        }

        return customerResponse;
    }

    public List<CustomerResponse> getAgeGreaterThan(Gender gender, int age) {
        List<Customer> customers = customerRepository.getAgeGreaterThan(gender, age);

        //Entity-->responseDto
        List<CustomerResponse> customerResponse = new ArrayList<>();
        for(Customer customer: customers){
            customerResponse.add(CustomerTransformer.customerToCustomerResponse(customer));
        }
        return customerResponse;
    }
}
