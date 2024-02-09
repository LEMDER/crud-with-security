package com.example.registrationlogindemo.repository;

import com.example.registrationlogindemo.entity.ShopItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ShopItemRepository extends JpaRepository<ShopItems, Long> {
    ShopItems findByIdAndAmountGreaterThan(Long id, int amount);
}
