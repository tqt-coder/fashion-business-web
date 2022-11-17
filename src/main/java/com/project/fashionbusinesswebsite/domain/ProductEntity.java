package com.project.fashionbusinesswebsite.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productsId;
    private int pCategoryId;
    private int categoryId;
    private Date date;
    private String productTitle;
    private String productImg;
    private double productPrice;
    private String productKeywords;
    private String productDescription;
    private int productQuantity;
}
