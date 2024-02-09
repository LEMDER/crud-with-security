package com.example.registrationlogindemo.service.impl;

import com.example.registrationlogindemo.entity.Shop;
import com.example.registrationlogindemo.repository.CityRepository;
import com.example.registrationlogindemo.repository.ShopRepository;
import com.example.registrationlogindemo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    CityRepository cityRepository;

    @Override
    public Shop addShop(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public List<Shop> getAllShop() {
        return shopRepository.findAll();
    }

    @Override
    public Shop getShop(Long id) {
        return shopRepository.getOne(id);
    }

    @Override
    public void deleteShop(Shop shop) {
        shopRepository.delete(shop);
    }

    @Override
    public Shop saveShop(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public List<Shop> getAllRoles() {
        return cityRepository.findAll();
    }

    @Override
    public Shop getRole(Long id) {
        return cityRepository.getOne(id);
    }


}
