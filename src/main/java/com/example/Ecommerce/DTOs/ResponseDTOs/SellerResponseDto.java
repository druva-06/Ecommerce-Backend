package com.example.Ecommerce.DTOs.ResponseDTOs;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class SellerResponseDto {
    String name;
    String mobileNo;
}
