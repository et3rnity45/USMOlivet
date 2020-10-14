package com.ttolivet.usmolivet.controllers;

import com.ttolivet.usmolivet.entities.Sponsor;
import com.ttolivet.usmolivet.repositories.SponsorRepository;
import com.ttolivet.usmolivet.services.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AdminSponsorController {

    private String DIR = "sponsor";

    @Autowired
    private SponsorRepository repo;

    @Autowired
    private FileUpload fileUpload;

    @GetMapping("/admin/sponsor")
    public String toList(Model model) {

        model.addAttribute("sponsors", repo.findAll());
        return "admin/adminSponsor";
    }

    @GetMapping("/admin/sponsor/update")
    public String toUpdate(@RequestParam(required = false) Long id, RedirectAttributes redirect, Model model) {
        Sponsor sponsor = new Sponsor();

        if (id != null) {
            Optional<Sponsor> optional = repo.findById(id);
            if (optional.isPresent()) {
                sponsor = optional.get();
            } else {
                redirect.addFlashAttribute("error", "Ce sponsor n'existe pas !");
                return "redirect:/admin/sponsor";
            }
        }

        model.addAttribute( "sponsor", sponsor);
        return "admin/updateSponsor";
    }

    @PostMapping("/admin/sponsor/update")
    public String update(@Valid Sponsor sponsor,
                         BindingResult result,
                         @RequestParam (name = "picture", required = false) MultipartFile pictureFile,
                         @RequestParam (name = "pictureBis", required = false) MultipartFile pictureFileBis,
                         RedirectAttributes redirect,
                         Model model) {

        if (result.hasErrors()) {
            model.addAttribute("hasError", true);
            model.addAttribute("sponsor", sponsor);
            return "admin/updateSponsor";
        }

        Long id = sponsor.getId();
        String path = "";
        String pathBis = "";
        if (id != null) {
            path = repo.findById(id).get().getPicturePath();
            pathBis = repo.findById(id).get().getPicturePathBis();
        }
        if (!pictureFile.isEmpty()) {
            String fileName = sponsor.getName().replaceAll(" ", "_").toLowerCase();
            path = fileUpload.writeFile(pictureFile, DIR, fileName);
        }
        if (!pictureFileBis.isEmpty()) {
            String fileName = sponsor.getName().replaceAll(" ", "_").toLowerCase() + "_bis";
            pathBis = fileUpload.writeFile(pictureFileBis, DIR, fileName);
        }

        sponsor.setPicturePath(path);
        sponsor.setPicturePathBis(pathBis);
        repo.save(sponsor);

        if (id != null) {
            redirect.addFlashAttribute("success", "Le sponsor à bien été mis à jour.");
        } else {
            redirect.addFlashAttribute("success", "Le sponsor à bien été ajouté.");
        }
        return "redirect:/admin/sponsor";
    }

    @GetMapping("/admin/sponsor/delete/{id}")
    public String remove(RedirectAttributes redirect, @PathVariable Long id) {
        Sponsor toDelete = repo.findById(id).get();
        fileUpload.deleteFile(toDelete.getPicturePath());
        repo.delete(toDelete);

        redirect.addFlashAttribute("success", "Le sponsor à bien été supprimé.");
        return "redirect:/admin/sponsor";
    }

}
