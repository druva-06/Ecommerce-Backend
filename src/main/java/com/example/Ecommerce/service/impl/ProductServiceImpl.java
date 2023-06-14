package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.DTOs.RequestDTOs.ProductRequestDto;
import com.example.Ecommerce.DTOs.ResponseDTOs.ProductResponseDto;
import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.entity.Seller;
import com.example.Ecommerce.exceptions.InvalidSellerException;
import com.example.Ecommerce.repository.ProductRepository;
import com.example.Ecommerce.repository.SellerRepository;
import com.example.Ecommerce.service.ProductService;
import com.example.Ecommerce.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SellerRepository sellerRepository;
    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException {

        //Check Weather Customer is Valid
        if(sellerRepository.findById(productRequestDto.getSellerId()).isEmpty())
            throw new InvalidSellerException("Invalid Seller!");

        //Creating Product
        Product product = ProductTransformer.productRequestDtoToProduct(productRequestDto);

        //adding Seller to Product
        int sellerId = productRequestDto.getSellerId();
        Seller seller = sellerRepository.findById(sellerId).get();
        product.setSeller(seller);

        //adding Product in Seller List
        seller.getProductList().add(product);

        //Saving the Seller and Product
        sellerRepository.save(seller);

        return ProductTransformer.productToProductResponseDto(product);
    }
}
