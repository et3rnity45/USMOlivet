package com.ttolivet.usmolivet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SponsorController {

    @Autowired
    private SponsorController sponsorController;

    @GetMapping("/partenaires")
    public String toSponsor() {

        return "client/partners";
    }
}
