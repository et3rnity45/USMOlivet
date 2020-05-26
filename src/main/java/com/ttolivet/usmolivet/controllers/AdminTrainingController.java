package com.ttolivet.usmolivet.controllers;

import com.ttolivet.usmolivet.entities.Schedule;
import com.ttolivet.usmolivet.entities.Trainer;
import com.ttolivet.usmolivet.repositories.ScheduleRepository;
import com.ttolivet.usmolivet.repositories.TrainerRepository;
import com.ttolivet.usmolivet.services.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AdminTrainingController {

    private String DIR = "entrainement";

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private FileUpload fileUpload;

    @Value("${app.uploads.dir}")
    private String uploads;

    @Autowired
    private String baseDir;

    @GetMapping("/admin/trainer")
    private String toList(Model model) {
        model.addAttribute("trainers", trainerRepository.findAll());
        model.addAttribute("schedule", scheduleRepository.findById((long) 1).get());
        return "/admin/adminTraining";
    }

    @GetMapping("/admin/trainer/update")
    public String toUpdate(@RequestParam(required = false) Long id, RedirectAttributes redirect, Model model) {
        Trainer trainer = new Trainer();

        if (id != null) {
            Optional<Trainer> optional = trainerRepository.findById(id);
            if (optional.isPresent()) {
                trainer = optional.get();
            } else {
                redirect.addFlashAttribute("error", "Cet entraineur n'existe pas !");
                return "redirect:/admin/trainer";
            }
        }

        model.addAttribute( "trainer", trainer);
        return "admin/updateTrainer";
    }

    @PostMapping("/admin/trainer/update")
    public String update(@Valid Trainer trainer,
                         BindingResult result,
                         @RequestParam (name = "picture", required = false) MultipartFile pictureFile,
                         RedirectAttributes redirect,
                         Model model) {

        if (result.hasErrors()) {
            model.addAttribute("hasError", true);
            model.addAttribute("trainer", trainer);
            return "admin/updateTrainer";
        }

        Long id = trainer.getId();
        String path = "";
        if (id != null) {
            path = trainerRepository.findById(id).get().getPicturePath();
        }
        if (!pictureFile.isEmpty()) {
            String fileName = (trainer.getFirstname() + " " + trainer.getLastname()).replaceAll(" ", "_").toLowerCase();
            path = fileUpload.writeFile(pictureFile, DIR, fileName);
        }

        trainer.setPicturePath(path);
        trainerRepository.save(trainer);

        if (id != null) {
            redirect.addFlashAttribute("success", "L'entraineur à bien été mis à jour.");
        } else {
            redirect.addFlashAttribute("success", "L'entraineur à bien été ajouté.");
        }
        return "redirect:/admin/trainer";
    }

    @GetMapping("/admin/trainer/delete/{id}")
    public String remove(RedirectAttributes redirect, @PathVariable Long id) {
        Trainer toDelete = trainerRepository.findById(id).get();
        fileUpload.deleteFile(toDelete.getPicturePath());
        trainerRepository.delete(toDelete);

        redirect.addFlashAttribute("success", "L'entraineur à bien été supprimé.");
        return "redirect:/admin/trainer";
    }
}
