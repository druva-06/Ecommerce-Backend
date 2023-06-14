package com.example.Ecommerce.controller;

import com.example.Ecommerce.DTOs.RequestDTOs.ProductRequestDto;
import com.example.Ecommerce.DTOs.ResponseDTOs.ProductResponseDto;
import com.example.Ecommerce.exceptions.InvalidSellerException;
import com.example.Ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto){
        try{
            ProductResponseDto productResponseDto = productService.addProduct(productRequestDto);
            return new ResponseEntity(productResponseDto,HttpStatus.CREATED);
        }
        catch (InvalidSellerException invalidSellerException){
            return new ResponseEntity(invalidSellerException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
