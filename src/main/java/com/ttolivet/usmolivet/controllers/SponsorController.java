package com.ttolivet.usmolivet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SponsorController {

    @GetMapping("/partenaires")
    public String toSponsor() {

        return "client/partners";
    }
}
