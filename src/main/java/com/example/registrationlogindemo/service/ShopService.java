package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.entity.Shop;

import java.util.List;

public interface ShopService {
    Shop addShop(Shop shop);
    List<Shop> getAllShop();
    Shop getShop(Long id);
    void deleteShop(Shop shop);
    Shop saveShop(Shop shop);

    List<Shop> getAllRoles();
    Shop getRole(Long id);
}
