package com.ttolivet.usmolivet.controllers;

import com.ttolivet.usmolivet.entities.Contact;
import com.ttolivet.usmolivet.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AdminContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/admin/contact")
    public String toUpdate(Model model) {

        model.addAttribute("contact", contactRepository.findById((long) 1).get());
        return "admin/adminContact";
    }

    @PostMapping("/admin/contact")
    public String update(@Valid Contact contact,
                         BindingResult result,
                         RedirectAttributes redirect,
                         Model model) {


        if (result.hasErrors()) {
            model.addAttribute("hasError", true);
            model.addAttribute("contact", contact);
            return "admin/adminContact";
        }

        contactRepository.save(contact);
        redirect.addFlashAttribute("success", "Les informations ont bien été mises à jour.");

        return "redirect:/admin";
    }

}
