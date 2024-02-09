package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.entity.Role;
import com.example.registrationlogindemo.entity.Staff;

import java.util.List;

public interface StaffService {
    Staff addStaff(Staff staff);
    List<Staff> getAllStaff();
    Staff getStaff(Long id);
    void deleteStaff(Staff staff);
    Staff saveStaff(Staff staff);

    List<Role> getAllRoles();
    Role getRole(Long id);
}
