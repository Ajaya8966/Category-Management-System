package com.aj.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String showAdminDashboard() {
        return "admin/dashboard"; // Loads the admin dashboard page
    }
    
    
    
}
