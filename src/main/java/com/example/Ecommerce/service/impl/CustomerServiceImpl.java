package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.DTOs.RequestDTOs.CustomerRequestDto;
import com.example.Ecommerce.DTOs.ResponseDTOs.CustomerResponseDto;
import com.example.Ecommerce.entity.Cart;
import com.example.Ecommerce.entity.Customer;
import com.example.Ecommerce.repository.CustomerRepository;
import com.example.Ecommerce.service.CustomerService;
import com.example.Ecommerce.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {
        Customer customer = CustomerTransformer.customerRequestDtoToCustomer(customerRequestDto);

        customer.setCart(
                Cart.builder()
                        .cartTotal(0)
                        .noOfItems(0)
                        .customer(customer)
                        .build()
        );

        Customer savedCustomer = customerRepository.save(customer);

        return CustomerTransformer.customerToCustomerResponseDto(savedCustomer);
    }
}
