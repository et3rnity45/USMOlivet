package com.ttolivet.usmolivet.controllers;

import com.ttolivet.usmolivet.repositories.ScheduleRepository;
import com.ttolivet.usmolivet.repositories.TrainerRepository;
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

}
