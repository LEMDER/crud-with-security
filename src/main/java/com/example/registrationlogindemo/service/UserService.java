package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.Categories;
import com.example.registrationlogindemo.entity.Shop;
import com.example.registrationlogindemo.entity.ShopItems;
import com.example.registrationlogindemo.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    void saveUserE(User user);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
    User getUser(Long id);
    User getUserByEmail(String email);

    List<Shop> getAllShops();
    Shop getShop(Long id);

}
