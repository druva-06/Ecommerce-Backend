package com.example.Ecommerce.repository;

import com.example.Ecommerce.DTOs.ResponseDTOs.SellerResponseDto;
import com.example.Ecommerce.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {
    Seller findByEmailId(String emailId);

    @Query(value = "select * from seller where age=:age",nativeQuery = true)
    List<Seller> getSellerWithParticularAge(int age);
}
