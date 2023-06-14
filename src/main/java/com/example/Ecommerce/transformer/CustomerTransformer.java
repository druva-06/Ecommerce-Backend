package com.example.Ecommerce.transformer;

import com.example.Ecommerce.DTOs.RequestDTOs.CustomerRequestDto;
import com.example.Ecommerce.DTOs.ResponseDTOs.CustomerResponseDto;
import com.example.Ecommerce.entity.Cart;
import com.example.Ecommerce.entity.Customer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerTransformer {
    public static Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .name(customerRequestDto.getName())
                .emailId(customerRequestDto.getEmailId())
                .age(customerRequestDto.getAge())
                .mobileNo(customerRequestDto.getMobileNo())
                .address(customerRequestDto.getAddress())
                .build();
    }

    public static CustomerResponseDto customerToCustomerResponseDto(Customer customerSaved) {
        return CustomerResponseDto.builder()
                .name(customerSaved.getName())
                .age(customerSaved.getAge())
                .build();
    }
}
