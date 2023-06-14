package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.DTOs.RequestDTOs.SellerRequestDto;
import com.example.Ecommerce.DTOs.ResponseDTOs.SellerResponseDto;
import com.example.Ecommerce.entity.Seller;
import com.example.Ecommerce.exceptions.InvalidSellerException;
import com.example.Ecommerce.repository.SellerRepository;
import com.example.Ecommerce.service.SellerService;
import com.example.Ecommerce.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerRepository sellerRepository;
    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {

        return SellerTransformer.sellerToSellerResponseDto(
                sellerRepository.save(
                        SellerTransformer.sellerReqestDtoToSeller(
                                sellerRequestDto
                        )
                )
        );
    }

    @Override
    public SellerResponseDto getSellerByEmailId(String emailId) throws InvalidSellerException {
        if(sellerRepository.findByEmailId(emailId)==null)
            throw new InvalidSellerException("Invalid EmailId");
        return SellerTransformer.sellerToSellerResponseDto(sellerRepository.findByEmailId(emailId));
    }

    @Override
    public SellerResponseDto getSellerById(Integer id) throws InvalidSellerException {
        if(sellerRepository.findById(id).isEmpty())
            throw new InvalidSellerException("Invalid Id");
        return SellerTransformer.sellerToSellerResponseDto(sellerRepository.findById(id).get());
    }

    @Override
    public List<SellerResponseDto> getAllSellers() {
        List<Seller> sellerList = sellerRepository.findAll();
        List<SellerResponseDto> sellerResponseDtoList = new ArrayList<>();
        for(Seller seller: sellerList){
            sellerResponseDtoList.add(SellerTransformer.sellerToSellerResponseDto(seller));
        }
        return sellerResponseDtoList;
    }

    @Override
    public SellerResponseDto updateSellerInfo(String email, String phNo) throws InvalidSellerException {
        Seller seller = sellerRepository.findByEmailId(email);

        if(seller == null) throw new InvalidSellerException("Invalid EmailId!");

        seller.setMobileNo(phNo);
        return SellerTransformer.sellerToSellerResponseDto(seller);
    }

    @Override
    public String deleteSellerByEmailId(String emailId) throws InvalidSellerException {
        Seller seller = sellerRepository.findByEmailId(emailId);

        if(seller == null) throw new InvalidSellerException("Invalid EmailId!");

        sellerRepository.delete(seller);
        return "Seller deleted Successfully!";
    }

    @Override
    public String deleteSellerById(Integer id) throws InvalidSellerException {
        if(sellerRepository.findById(id).isEmpty())
            throw new InvalidSellerException("Invalid Id!");

        sellerRepository.deleteById(id);
        return "Seller Deleted Successfully!";
    }

    @Override
    public List<SellerResponseDto> getSellersWithAge(Integer age) {
        List<Seller> sellerList =  sellerRepository.getSellerWithParticularAge(age);

        List<SellerResponseDto> sellerResponseDtoList = new ArrayList<>();
        for(Seller seller: sellerList){
            sellerResponseDtoList.add(SellerTransformer.sellerToSellerResponseDto(seller));
        }
        return sellerResponseDtoList;
    }
}
