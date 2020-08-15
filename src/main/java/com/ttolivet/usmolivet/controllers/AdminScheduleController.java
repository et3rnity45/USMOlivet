package com.ttolivet.usmolivet.controllers;

import com.ttolivet.usmolivet.entities.Schedule;
import com.ttolivet.usmolivet.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AdminScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @GetMapping("/admin/schedule")
    private String toList(Model model) {
        model.addAttribute("schedules", scheduleRepository.findAll());
        return "admin/adminSchedule";
    }

    @GetMapping("/admin/schedule/update")
    public String toUpdate(@RequestParam(required = false) Long id, RedirectAttributes redirect, Model model) {
        Schedule schedule = new Schedule();

        if (id != null) {
            Optional<Schedule> optional = scheduleRepository.findById(id);
            if (optional.isPresent()) {
                schedule = optional.get();
            } else {
                redirect.addFlashAttribute("error", "Cet entrainement n'existe pas !");
                return "redirect:/admin/schedule";
            }
        }

        model.addAttribute( "schedule", schedule);
        return "admin/updateSchedule";
    }

    @PostMapping("/admin/schedule/update")
    public String update(@Valid Schedule schedule,
                         BindingResult result,
                         RedirectAttributes redirect,
                         Model model) {

        if (result.hasErrors()) {
            model.addAttribute("hasError", true);
            model.addAttribute("schedule", schedule);
            return "admin/updateSchedule";
        }

        scheduleRepository.save(schedule);

        if (schedule.getId() != null) {
            redirect.addFlashAttribute("success", "L'entrainement à bien été mis à jour.");
        } else {
            redirect.addFlashAttribute("success", "L'entrainement à bien été ajouté.");
        }
        return "redirect:/admin/schedule";
    }

    @GetMapping("/admin/schedule/delete/{id}")
    public String remove(RedirectAttributes redirect, @PathVariable Long id) {
        scheduleRepository.deleteById(id);

        redirect.addFlashAttribute("success", "L'entrainement à bien été supprimé.");
        return "redirect:/admin/schedule";
    }

}
