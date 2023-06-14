package com.example.Ecommerce.controller;

import com.example.Ecommerce.DTOs.RequestDTOs.ProductRequestDto;
import com.example.Ecommerce.DTOs.ResponseDTOs.ProductResponseDto;
import com.example.Ecommerce.enums.ProductCategory;
import com.example.Ecommerce.exceptions.InvalidProductException;
import com.example.Ecommerce.exceptions.InvalidSellerException;
import com.example.Ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/productsByEmailId/{emailId}")
    public ResponseEntity getProductBySellerEmailId(@PathVariable String emailId){
        try{
            List<ProductResponseDto> productResponseDtoList = productService.getProductBySellerEmailId(emailId);
            return new ResponseEntity(productResponseDtoList,HttpStatus.ACCEPTED);
        }
        catch (InvalidSellerException invalidSellerException){
            return new ResponseEntity(invalidSellerException.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{sellerId}/{productId}")
    public ResponseEntity deleteProduct(@PathVariable Integer sellerId,@PathVariable Integer productId){
        try{
            String status = productService.delete(sellerId,productId);
            return new ResponseEntity(status,HttpStatus.ACCEPTED);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/topCheapestProducts/{value}")
    public List<ProductResponseDto> topCheapestProducts(@PathVariable Integer value){
        return productService.topCheapestProducts(value);
    }
    @GetMapping("/topCostliestProducts/{value}")
    public List<ProductResponseDto> topCostliestProducts(@PathVariable Integer value){
        return productService.topCostliestProducts(value);
    }
    @GetMapping("/outOfStock")
    public List<ProductResponseDto> outOfStockProducts(){
        return productService.outOfStockProducts();
    }
    @GetMapping("/available")
    public List<ProductResponseDto> availableProducts(){
        return productService.availableProducts();
    }
    @GetMapping("/quantityLessThan/{value}")
    public List<ProductResponseDto> productQuantityLessThan(@PathVariable Integer value){
        return productService.productQuantityLessThan(value);
    }
    @GetMapping("/cheapestProductInCategory/{category}")
    public ResponseEntity cheapestProductInCategory(@PathVariable String category){
        try{
            return new ResponseEntity(productService.cheapestProductInCategory(category),HttpStatus.ACCEPTED);
        }
        catch (InvalidProductException invalidProductException){
            return new ResponseEntity(invalidProductException.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/costliestProductInCategory/{category}")
    public ResponseEntity costliestProductInCategory(@PathVariable String category){
        try{
            return new ResponseEntity(productService.costliestProductInCategory(category),HttpStatus.ACCEPTED);
        }
        catch (InvalidProductException invalidProductException){
            return new ResponseEntity(invalidProductException.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
