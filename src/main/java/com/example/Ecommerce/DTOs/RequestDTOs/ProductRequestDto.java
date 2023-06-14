package com.example.Ecommerce.DTOs.RequestDTOs;

import com.example.Ecommerce.enums.ProductCategory;
import com.example.Ecommerce.enums.ProductStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDto {
    int sellerId;
    String name;
    int price;
    int quantity;
    ProductCategory productCategory;
}
