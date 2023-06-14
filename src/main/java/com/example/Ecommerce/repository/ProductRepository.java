package com.example.Ecommerce.repository;

import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findBySellerId(Integer sellerId);

    Product findBySellerIdAndId(Integer sellerId, Integer productId);

    @Query(value = "select * from product order by price ASC",nativeQuery = true)
    List<Product> sortAllProductsByPriceAsc();

    @Query(value = "select * from product order by price DESC",nativeQuery = true)
    List<Product> sortAllProductsByPriceDesc();

    List<Product> findByProductStatus(ProductStatus productStatus);

    @Query(value = "select * from product where quantity < :value",nativeQuery = true)
    List<Product> findAllProductQuantityLessThan(Integer value);

    @Query(value = "select * from product where price = (select min(price) from product where product_category = :category)",nativeQuery = true)
    Product findCheapestProductInCategory(String category);

    @Query(value = "select * from product where price = (select max(price) from product where product_category = :category)",nativeQuery = true)
    Product findCostliestProductInCategory(String category);

    Product findByProductCategory(String category);
}
