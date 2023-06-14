package com.example.Ecommerce.transformer;

import com.example.Ecommerce.DTOs.RequestDTOs.ProductRequestDto;
import com.example.Ecommerce.DTOs.ResponseDTOs.ProductResponseDto;
import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.enums.ProductStatus;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductTransformer {
    public static Product productRequestDtoToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .name(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .productCategory(productRequestDto.getProductCategory())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }
    public static ProductResponseDto productToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .productStatus(product.getProductStatus()).build();
    }
}
