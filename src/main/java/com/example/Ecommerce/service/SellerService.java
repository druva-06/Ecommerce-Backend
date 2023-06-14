package com.example.Ecommerce.service;

import com.example.Ecommerce.DTOs.RequestDTOs.SellerRequestDto;
import com.example.Ecommerce.DTOs.ResponseDTOs.SellerResponseDto;
import com.example.Ecommerce.entity.Seller;
import com.example.Ecommerce.exceptions.InvalidSellerException;

import java.util.List;

public interface SellerService {
    SellerResponseDto addSeller(SellerRequestDto sellerRequestDto);

    SellerResponseDto getSellerByEmailId(String emailId) throws InvalidSellerException;

    SellerResponseDto getSellerById(Integer id) throws InvalidSellerException;

    List<SellerResponseDto> getAllSellers();

    SellerResponseDto updateSellerInfo(String email, String phNo) throws InvalidSellerException;

    String deleteSellerByEmailId(String emailId) throws InvalidSellerException;

    String deleteSellerById(Integer id) throws InvalidSellerException;

    List<SellerResponseDto> getSellersWithAge(Integer age);
}
