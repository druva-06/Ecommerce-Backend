package com.example.Ecommerce.service;

import com.example.Ecommerce.DTOs.RequestDTOs.ProductRequestDto;
import com.example.Ecommerce.DTOs.ResponseDTOs.ProductResponseDto;
import com.example.Ecommerce.exceptions.InvalidProductException;
import com.example.Ecommerce.exceptions.InvalidSellerException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException;

    List<ProductResponseDto> getProductBySellerEmailId(String emailId) throws InvalidSellerException;

    String delete(Integer sellerId, Integer productId) throws InvalidSellerException, InvalidProductException;

    List<ProductResponseDto> topCheapestProducts(Integer value);

    List<ProductResponseDto> topCostliestProducts(Integer value);

    List<ProductResponseDto> outOfStockProducts();

    List<ProductResponseDto> availableProducts();

    List<ProductResponseDto> productQuantityLessThan(Integer value);

    ProductResponseDto cheapestProductInCategory(String category) throws InvalidProductException;

    ProductResponseDto costliestProductInCategory(String category) throws InvalidProductException;
}
