package com.example.Ecommerce.service;

import com.example.Ecommerce.DTOs.RequestDTOs.CardRequestDto;
import com.example.Ecommerce.DTOs.ResponseDTOs.CardResponseDto;
import com.example.Ecommerce.exceptions.CustomerNotFoundException;

public interface CardService {

    CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException;
}
