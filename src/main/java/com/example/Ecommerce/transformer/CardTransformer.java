package com.example.Ecommerce.transformer;

import com.example.Ecommerce.DTOs.RequestDTOs.CardRequestDto;
import com.example.Ecommerce.entity.Card;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CardTransformer {
    public static Card cardRequestDtoToCard(CardRequestDto cardRequestDto){
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .cvv(cardRequestDto.getCvv())
                .expiryDate(cardRequestDto.getExpiryDate())
                .build();
    }
}
