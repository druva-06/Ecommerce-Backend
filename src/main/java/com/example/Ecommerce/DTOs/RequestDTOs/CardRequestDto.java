package com.example.Ecommerce.DTOs.RequestDTOs;

import com.example.Ecommerce.enums.CardType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardRequestDto {
    int customerId;
    String cardNo;
    int cvv;
    Date expiryDate;
    CardType cardType;
}
