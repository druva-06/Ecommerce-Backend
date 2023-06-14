package com.example.Ecommerce.service;

import com.example.Ecommerce.DTOs.RequestDTOs.ProductRequestDto;
import com.example.Ecommerce.DTOs.ResponseDTOs.ProductResponseDto;
import com.example.Ecommerce.exceptions.InvalidSellerException;

public interface ProductService {
    ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException;
}
