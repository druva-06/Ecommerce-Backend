package com.example.Ecommerce.DTOs.ResponseDTOs;

import com.example.Ecommerce.enums.ProductCategory;
import com.example.Ecommerce.enums.ProductStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponseDto {
    String name;
    int price;
    ProductStatus productStatus;
}
