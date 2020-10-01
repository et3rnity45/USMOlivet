package com.ttolivet.usmolivet.controllers;

import com.ttolivet.usmolivet.repositories.PouleRepository;
import com.ttolivet.usmolivet.services.ConnectApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminTeamController {

    @Autowired
    private ConnectApi connectApi;

    @Autowired
    private PouleRepository pouleRepository;

    @GetMapping("/admin/team")
    public String toList(Model model) {
        model.addAttribute("poules", pouleRepository.findAll());

        return "admin/adminTeam";
    }

    @PostMapping("/admin/team")
    public String refresh(Model model) {
        connectApi.getTeams();

        model.addAttribute("poules", pouleRepository.findAll());
        model.addAttribute("success", "Les équipes ont bien été mises à jour.");
        return "admin/adminTeam";
    }

}