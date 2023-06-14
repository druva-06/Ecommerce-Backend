package com.example.Ecommerce.entity;

import com.example.Ecommerce.enums.CardType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "card")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(unique = true,nullable = false)
    String cardNo;
    int cvv;
    Date expiryDate;
    @Enumerated(EnumType.STRING)
    CardType cardType;

    @JoinColumn
    @ManyToOne
    Customer customer;
}