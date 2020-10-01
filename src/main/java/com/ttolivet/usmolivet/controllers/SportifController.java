package com.ttolivet.usmolivet.controllers;

import com.ttolivet.usmolivet.repositories.*;
import com.ttolivet.usmolivet.services.ConnectApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SportifController {

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PouleRepository pouleRepository;

    @GetMapping("/sportif/entrainements")
    public String toEntrainement(Model model) {
        model.addAttribute("schedules", scheduleRepository.findAll());
        model.addAttribute("trainers", trainerRepository.findAll());

        return "client/sportif/entrainements";
    }

    @GetMapping("/sportif/stages")
    public String toStages(Model model) {

        return "redirect:/articles#stage";
    }

    @GetMapping("/sportif/competitions")
    public String toCompetitions(Model model) {

        return "redirect:/articles#competition";
    }

    @GetMapping("/sportif/classement")
    public String toClassement(Model model) {
        model.addAttribute("players", playerRepository.findAllByOrderByPointsDesc());

        return "client/sportif/classement";
    }

    @GetMapping("/sportif/equipes")
    public String toTeams(Model model) {
        model.addAttribute("poules", pouleRepository.findAll());

        return "client/sportif/equipes";
    }

}
