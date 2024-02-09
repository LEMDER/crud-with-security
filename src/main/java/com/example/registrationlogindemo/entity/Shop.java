package com.example.registrationlogindemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "address")
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    private City city;

    @Column(name = "staff_amount")
    private int staffAmount;

    @Column(name = "salesAmount")
    private int salesAmount;

    @Column(name = "salesCost")
    private double salesCost;

    @OneToOne(fetch = FetchType.EAGER)
    private Staff director;
}
