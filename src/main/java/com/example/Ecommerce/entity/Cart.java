package com.example.Ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    Integer cartTotal;
    Integer noOfItems;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    List<Item> itemList = new ArrayList<>();
    @JoinColumn
    @OneToOne
    Customer customer;
}
