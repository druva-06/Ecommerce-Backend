package com.example.Ecommerce.transformer;

import com.example.Ecommerce.DTOs.RequestDTOs.SellerRequestDto;
import com.example.Ecommerce.DTOs.ResponseDTOs.SellerResponseDto;
import com.example.Ecommerce.entity.Seller;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SellerTransformer {
    public static Seller sellerReqestDtoToSeller(SellerRequestDto sellerRequestDto){
        return Seller.builder().
                name(sellerRequestDto.getName()).
                age(sellerRequestDto.getAge()).
                mobileNo(sellerRequestDto.getMobileNo()).
                emailId(sellerRequestDto.getEmailId()).
                build();
    }

    public static SellerResponseDto sellerToSellerResponseDto(Seller seller){
        return SellerResponseDto.builder().
                name(seller.getName()).
                mobileNo(seller.getMobileNo()).
                build();
    }
}
