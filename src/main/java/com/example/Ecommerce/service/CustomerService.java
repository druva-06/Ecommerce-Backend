package com.example.Ecommerce.service;

import com.example.Ecommerce.DTOs.RequestDTOs.CustomerRequestDto;
import com.example.Ecommerce.DTOs.ResponseDTOs.CustomerResponseDto;
import com.example.Ecommerce.entity.Customer;

public interface CustomerService {
    CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto);
}
