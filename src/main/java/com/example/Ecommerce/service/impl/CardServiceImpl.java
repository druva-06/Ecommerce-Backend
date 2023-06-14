package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.DTOs.RequestDTOs.CardRequestDto;
import com.example.Ecommerce.DTOs.ResponseDTOs.CardResponseDto;
import com.example.Ecommerce.entity.Card;
import com.example.Ecommerce.entity.Customer;
import com.example.Ecommerce.exceptions.CustomerNotFoundException;
import com.example.Ecommerce.repository.CustomerRepository;
import com.example.Ecommerce.service.CardService;
import com.example.Ecommerce.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException {

        Customer customer = customerRepository.findById(cardRequestDto.getCustomerId()).get();

        if(customer == null)
            throw new CustomerNotFoundException("Customer Id is not Valid");

        Card card = CardTransformer.cardRequestDtoToCard(cardRequestDto);

        card.setCustomer(customer);

        customer.getCardList().add(card);

        customerRepository.save(customer);

        return CardResponseDto.builder()
                .customerName(customer.getName())
                .cardNo(card.getCardNo())
                .build();
    }
}
