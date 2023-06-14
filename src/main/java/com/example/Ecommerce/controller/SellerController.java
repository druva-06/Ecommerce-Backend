package com.example.Ecommerce.controller;

import com.example.Ecommerce.DTOs.RequestDTOs.SellerRequestDto;
import com.example.Ecommerce.DTOs.ResponseDTOs.SellerResponseDto;
import com.example.Ecommerce.exceptions.InvalidSellerException;
import com.example.Ecommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;
    @PostMapping("/add")
    public SellerResponseDto addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        return sellerService.addSeller(sellerRequestDto);
    }
    @GetMapping("/getByEmailId/{emailId}")
    public ResponseEntity getSellerByEmailId(@PathVariable String emailId){
        try{
            SellerResponseDto sellerResponseDto = sellerService.getSellerByEmailId(emailId);
            return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
        }
        catch (InvalidSellerException invalidSellerException){
            return new ResponseEntity(invalidSellerException.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity getSellerById(@PathVariable Integer id){
        try{
            SellerResponseDto sellerResponseDto = sellerService.getSellerById(id);
            return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
        }
        catch (InvalidSellerException invalidSellerException){
            return new ResponseEntity(invalidSellerException.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getAllSellers")
    public List<SellerResponseDto> getAllSellers(){
        return sellerService.getAllSellers();
    }
    @PutMapping("/updateInfo/{emailId}/{phoneNumber}")
    public ResponseEntity updateSellerInfo(@PathVariable("emailId") String email, @PathVariable("phoneNumber") String phNo){
        try{
            SellerResponseDto sellerResponseDto = sellerService.updateSellerInfo(email,phNo);
            return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
        }
        catch (InvalidSellerException invalidSellerException){
            return new ResponseEntity(invalidSellerException.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deleteByEmailId")
    public ResponseEntity deleteSellerByEmailId(@RequestParam String emailId){
        try{
            String status = sellerService.deleteSellerByEmailId(emailId);
            return new ResponseEntity(status,HttpStatus.ACCEPTED);
        }
        catch (InvalidSellerException invalidSellerException){
            return new ResponseEntity(invalidSellerException.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deleteById")
    public ResponseEntity deleteSellerById(@RequestParam Integer id){
        try{
            String status = sellerService.deleteSellerById(id);
            return new ResponseEntity(status,HttpStatus.ACCEPTED);
        }
        catch (InvalidSellerException invalidSellerException){
            return new ResponseEntity(invalidSellerException.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getSellersWithAge/{age}")
    public List<SellerResponseDto> getSellersWithAge(@PathVariable Integer age){
        return sellerService.getSellersWithAge(age);
    }
}
