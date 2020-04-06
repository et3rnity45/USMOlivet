package com.ttolivet.usmolivet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    private String toAdmin() {

        return "admin/admin";
    }
}
