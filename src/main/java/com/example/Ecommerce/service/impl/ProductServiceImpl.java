package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.DTOs.RequestDTOs.ProductRequestDto;
import com.example.Ecommerce.DTOs.ResponseDTOs.ProductResponseDto;
import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.entity.Seller;
import com.example.Ecommerce.enums.ProductStatus;
import com.example.Ecommerce.exceptions.InvalidProductException;
import com.example.Ecommerce.exceptions.InvalidSellerException;
import com.example.Ecommerce.repository.ProductRepository;
import com.example.Ecommerce.repository.SellerRepository;
import com.example.Ecommerce.service.ProductService;
import com.example.Ecommerce.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SellerRepository sellerRepository;
    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException {

        //Check Weather Customer is Valid
        if(sellerRepository.findById(productRequestDto.getSellerId()).isEmpty())
            throw new InvalidSellerException("Invalid Seller!");

        //Creating Product
        Product product = ProductTransformer.productRequestDtoToProduct(productRequestDto);

        //adding Seller to Product
        int sellerId = productRequestDto.getSellerId();
        Seller seller = sellerRepository.findById(sellerId).get();
        product.setSeller(seller);

        //adding Product in Seller List
        seller.getProductList().add(product);

        //Saving the Seller and Product
        sellerRepository.save(seller);

        return ProductTransformer.productToProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> getProductBySellerEmailId(String emailId) throws InvalidSellerException {

        //Check weather the EmailId is valid;
        if(sellerRepository.findByEmailId(emailId) == null)
            throw new InvalidSellerException("Invalid EmailId!");


        //id of the seller using EmailId
        Seller seller = sellerRepository.findByEmailId(emailId);

        //Products with similar EmailId
        List<Product> productList = productRepository.findBySellerId(seller.getId());

        //Convert the product list to ProductResponseDto list
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(Product product:productList){
            productResponseDtoList.add(ProductTransformer.productToProductResponseDto(product));
        }

        return productResponseDtoList;
    }

    @Override
    public String delete(Integer sellerId, Integer productId) throws InvalidSellerException, InvalidProductException {

        //Check weather sellerId is valid
        if(sellerRepository.findById(sellerId).isEmpty())
            throw new InvalidSellerException("Invalid Seller");

        //Check weather productId is valid
        if(productRepository.findById(productId).isEmpty())
            throw new InvalidProductException("Invalid Product");

        //Product with sellerId and ProductId
        Product product = productRepository.findBySellerIdAndId(sellerId,productId);

        if(product == null){
            return "Seller Doesn't Contains the Product!";
        }

        //Removing the product
        productRepository.delete(product);

        return "Product is Deleted Successfully!";
    }

    @Override
    public List<ProductResponseDto> topCheapestProducts(Integer value) {
        //All Products Sorted in Asc by price
        List<Product> productList = productRepository.sortAllProductsByPriceAsc();

        //Required number of products
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(int i=0;i<productList.size() && i < value; i++){
            productResponseDtoList.add(ProductTransformer.productToProductResponseDto(productList.get(i)));
        }

        return productResponseDtoList;
    }

    @Override
    public List<ProductResponseDto> topCostliestProducts(Integer value) {
        //All Products Sorted in DESC by price
        List<Product> productList = productRepository.sortAllProductsByPriceDesc();

        //Required number of products
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(int i=0;i<productList.size() && i < value; i++){
            productResponseDtoList.add(ProductTransformer.productToProductResponseDto(productList.get(i)));
        }

        return productResponseDtoList;
    }

    @Override
    public List<ProductResponseDto> outOfStockProducts() {
        //Products without of Stock
        List<Product> productList = productRepository.findByProductStatus(ProductStatus.OUT_OF_STOCK);

        //Convert the product list to ProductResponseDto list
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(Product product:productList){
            productResponseDtoList.add(ProductTransformer.productToProductResponseDto(product));
        }

        return productResponseDtoList;
    }

    @Override
    public List<ProductResponseDto> availableProducts() {
        //Products within the Stock
        List<Product> productList = productRepository.findByProductStatus(ProductStatus.AVAILABLE);

        //Convert the product list to ProductResponseDto list
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(Product product:productList){
            productResponseDtoList.add(ProductTransformer.productToProductResponseDto(product));
        }

        return productResponseDtoList;
    }

    @Override
    public List<ProductResponseDto> productQuantityLessThan(Integer value) {

        //Products with less than given quantity
        List<Product> productList = productRepository.findAllProductQuantityLessThan(value);

        //Convert the product list to ProductResponseDto list
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(Product product:productList){
            productResponseDtoList.add(ProductTransformer.productToProductResponseDto(product));
        }

        return productResponseDtoList;
    }

    @Override
    public ProductResponseDto cheapestProductInCategory(String category) throws InvalidProductException {
        //Cheapest product in a category
        Product product = productRepository.findCheapestProductInCategory(category);

        if(product == null)
            throw new InvalidProductException("No Product with "+category);

        return ProductTransformer.productToProductResponseDto(product);
    }

    @Override
    public ProductResponseDto costliestProductInCategory(String category) throws InvalidProductException {
        //Costliest product in a category
        Product product = productRepository.findCostliestProductInCategory(category);

        if(product == null)
            throw new InvalidProductException("No Product with "+category);

        return ProductTransformer.productToProductResponseDto(product);
    }
}
