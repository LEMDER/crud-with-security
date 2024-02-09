package com.example.registrationlogindemo.service.impl;

import com.example.registrationlogindemo.entity.Role;
import com.example.registrationlogindemo.entity.Staff;
import com.example.registrationlogindemo.repository.RoleRepository;
import com.example.registrationlogindemo.repository.StaffRepository;
import com.example.registrationlogindemo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Staff addStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public Staff getStaff(Long id) {
        return staffRepository.getOne(id);
    }

    @Override
    public void deleteStaff(Staff staff) {
        staffRepository.delete(staff);
    }

    @Override
    public Staff saveStaff(Staff staff) {
        return null;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRole(Long id) {
        return roleRepository.getReferenceById(id);
    }
}
