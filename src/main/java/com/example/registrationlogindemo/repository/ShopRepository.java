package com.example.registrationlogindemo.repository;

import com.example.registrationlogindemo.entity.Shop;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ShopRepository extends JpaRepository<Shop, Long> {
}
