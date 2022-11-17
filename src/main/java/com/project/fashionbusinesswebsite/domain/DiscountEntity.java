package com.project.fashionbusinesswebsite.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "discount")
@NoArgsConstructor
@AllArgsConstructor
public class DiscountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int discountId;
    private double discountPercent;
    private int productId;
}
