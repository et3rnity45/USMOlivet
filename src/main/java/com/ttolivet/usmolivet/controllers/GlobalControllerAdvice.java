package com.ttolivet.usmolivet.controllers;

import com.ttolivet.usmolivet.entities.Inscription;
import com.ttolivet.usmolivet.entities.Sponsor;
import com.ttolivet.usmolivet.repositories.InscriptionRepository;
import com.ttolivet.usmolivet.repositories.SponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Calendar;
import java.util.List;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private SponsorRepository sponsorRepository;

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @ModelAttribute("sponsors")
    public List<Sponsor> getAllSponsors(Model model) {
        return sponsorRepository.findAll();
    }

    @ModelAttribute("inscription")
    public Inscription getInscription(Model model) {
        return inscriptionRepository.findTopByOrderByIdDesc();
}

}
