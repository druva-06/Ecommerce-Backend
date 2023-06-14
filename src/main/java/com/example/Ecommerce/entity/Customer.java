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
@Table(name = "customer")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    @Column(unique = true)
    String emailId;
    Integer age;
    @Column(unique = true)
    String mobileNo;
    String address;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<Ordered> orderedList = new ArrayList<>();
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    Cart cart;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<Card> cardList = new ArrayList<>();
}