package com.example.registrationlogindemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstName")
    private String fName;

    @Column(name = "secondName")
    private String sName;

    @Column(name = "email")
    private String email;

    @Column(name = "salesAmount")
    private int salesAmount;

    @Column(name = "salesCost")
    private double salesCost;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;
}
