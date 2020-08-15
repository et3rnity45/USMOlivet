package com.ttolivet.usmolivet.controllers;

import com.ttolivet.usmolivet.repositories.ContactRepository;
import com.ttolivet.usmolivet.repositories.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OthersController {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @GetMapping("/cgu")
    public String toCgu() {
        return "client/others/cgu";
    }

    @GetMapping("/privacy")
    public String toPrivacy() {
        return "/client/others/privacy";
    }

    @GetMapping("/contact")
    public String toContact(Model model) {
        model.addAttribute("contact", contactRepository.findById((long) 1).get());
        model.addAttribute("trainers", trainerRepository.findAll());

        return "client/others/contact";
    }

}
