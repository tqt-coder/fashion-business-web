package com.project.fashionbusinesswebsite.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;
    private String userName;
    private String customerName;
    private String customerEmail;
    private String customerPass;
    private String customerAddress;
    private String customerContact;
    private String customerImage;
}
