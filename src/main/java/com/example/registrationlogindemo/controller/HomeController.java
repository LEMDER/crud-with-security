package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.entity.*;
import com.example.registrationlogindemo.service.ItemService;
import com.example.registrationlogindemo.service.ShopService;
import com.example.registrationlogindemo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private StaffService staffService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ShopService shopService;

    @GetMapping(value = "/items")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ADMIN')")
    public String index(Model model){
        List<ShopItems> items = itemService.getAllItems();
        model.addAttribute("tovary", items);

        List<Categories> categories = itemService.getAllCategories();
        model.addAttribute("categories", categories);
        return "items";
    }

    @GetMapping(value = "/index")
    public String home(Model model){
        List<ShopItems> items = itemService.getAllItems();
        model.addAttribute("tovary", items);

        List<Categories> categories = itemService.getAllCategories();
        model.addAttribute("categories", categories);
        return "index";
    }


    @PostMapping(value = "/additem")
    public String addItem(@RequestParam(name = "category_id", defaultValue = "0") Long id,
                          @RequestParam(name = "item_name", defaultValue = "NaN") String name,
                          @RequestParam(name = "item_price", defaultValue = "0") int price,
                          @RequestParam(name = "item_amount", defaultValue = "0") int amount){

        Categories cnt = itemService.getCategory(id);
        if(cnt != null){
            ShopItems item = new ShopItems();
            item.setName(name);
            item.setPrice(price);
            item.setAmount(amount);
            item.setCategory(cnt);

            itemService.addItem(item);
        }
        return "redirect:/items";
    }

    @GetMapping(value = "/edit/{idshka}")
    public String edit(Model model, @PathVariable(name = "idshka") Long id){
        ShopItems item = itemService.getItem(id);
        model.addAttribute("item", item);

        List<Categories> categories = itemService.getAllCategories();
        model.addAttribute("categories", categories);
        return "edit";
    }

    @PostMapping(value = "/saveitem")
    public String saveItem(@RequestParam(name = "id", defaultValue = "0") Long id,
                           @RequestParam(name = "category_id", defaultValue = "0") Long categoryId,
                           @RequestParam(name = "item_name", defaultValue = "NaN") String name,
                           @RequestParam(name = "item_price", defaultValue = "0") int price,
                           @RequestParam(name = "item_amount", defaultValue = "0") int amount){


        ShopItems item = itemService.getItem(id);
        if(item != null){
            Categories cnt = itemService.getCategory(categoryId);
            if(cnt != null){
                item.setName(name);
                item.setPrice(price);
                item.setAmount(amount);
                item.setCategory(cnt);
                itemService.saveItem(item);
            }
        }
        return "redirect:/items";
    }

    @GetMapping(value = "/deleteitem/{id}")
    public String deleteItem(@PathVariable("id") Long id){
        ShopItems item = itemService.getItem(id);
        if(item != null){
            itemService.deleteItem(item);
        }
        return "redirect:/items";
    }

//    STAFF ----------------------------------------------------------------

    @GetMapping(value = "/staff")
    public String staff(Model model){
        List<Staff> staff = staffService.getAllStaff();
        model.addAttribute("staff", staff);
        return "staff";
    }

    @GetMapping(value = "/editstaff/{id}")
    public String editstaff(Model model, @PathVariable(name = "id") Long id){
        Staff staff = staffService.getStaff(id);
        model.addAttribute("staff", staff);
        return "editstaff";
    }

    @PostMapping(value = "/savestaff")
    public String saveStaff(@RequestParam(name = "id", defaultValue = "0") Long id,
                           @RequestParam(name = "role_id", defaultValue = "0") Long roleId,
                           @RequestParam(name = "user_fName", defaultValue = "NaN") String name,
                            @RequestParam(name = "user_lName", defaultValue = "NaN") String surname,
                            @RequestParam(name = "user_email", defaultValue = "NaN") String email){
        Staff staff = staffService.getStaff(id);
        if(staff != null){
            Role cnt = staffService.getRole(roleId);
            if(cnt != null){
                staff.setFName(name);
                staff.setSName(surname);
                staff.setEmail(email);
                staff.setRole(cnt);
                staffService.saveStaff(staff);
            }
        }
        return "redirect:/staff";
    }

    @GetMapping(value = "/deletestaff/{id}")
    public String deleteStaff(@PathVariable("id") Long id){
        Staff staff = staffService.getStaff(id);
        if(staff != null){
            staffService.deleteStaff(staff);
        }
        return "redirect:/staff";
    }

//       SHOP ----------------------------------------------------------

    @GetMapping(value = "/shops")
    public String shops(Model model){
        List<Shop> shops = shopService.getAllShop();
        model.addAttribute("shops", shops);
        return "shops";
    }

}
