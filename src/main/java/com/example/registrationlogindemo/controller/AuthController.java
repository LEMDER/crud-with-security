package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.*;
import com.example.registrationlogindemo.service.StaffService;
import com.example.registrationlogindemo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private StaffService staffService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // handler method to handle user registration request
    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }

    @PostMapping(value = "/saveuser")
    public String saveItem(@RequestParam(name = "id", defaultValue = "0") Long id,
                           @RequestParam(name = "user_fName", defaultValue = "NaN") String name,
                           @RequestParam(name = "user_email", defaultValue = "1@gmail.com") String email,
                           @RequestParam(name = "password", defaultValue = "123456") String password){
        User user = userService.getUser(id);
        if(user != null){
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                userService.saveUserE(user);

        }

        String[] newname = name.split(" ");

        Staff staff = staffService.getStaff(id);
        if(staff != null){
            staff.setEmail(email);
            staff.setFName(newname[0]);
            staff.setSName(newname[1]);
            staff.setSalesAmount(0);
            staff.setSalesCost(0);

            staffService.addStaff(staff);
        }

        return "redirect:/users";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("currentUser", getUserData());

        List<Shop> shops = userService.getAllShops();
        model.addAttribute("shops", shops);
        return "users";
    }

    @GetMapping(value = "/edituser/{id}")
    public String edit(Model model, @PathVariable(name = "id") Long id){
        List<User> user = (List<User>) userService.getUser(id);
        model.addAttribute("user", user);
        return "edituser";
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String profile(Model model){
        model.addAttribute("currentUser", getUserData());
        return "profile";
    }

    private User getUserData(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            org.springframework.security.core.userdetails.User secUser = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
            User myUser = userService.getUserByEmail(secUser.getUsername());
            return myUser;
        }
        return null;
    }

}
