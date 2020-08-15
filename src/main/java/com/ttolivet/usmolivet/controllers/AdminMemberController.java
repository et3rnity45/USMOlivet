package com.ttolivet.usmolivet.controllers;

import com.ttolivet.usmolivet.entities.Member;
import com.ttolivet.usmolivet.entities.Schedule;
import com.ttolivet.usmolivet.entities.Trainer;
import com.ttolivet.usmolivet.repositories.MemberRepository;
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
public class AdminMemberController {

    private String DIR = "member";

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private FileUpload fileUpload;

    @Value("${app.uploads.dir}")
    private String uploads;

    @Autowired
    private String baseDir;

    @GetMapping("/admin/member")
    private String toList(Model model) {
        model.addAttribute("members", memberRepository.findAll());
        return "admin/adminMember";
    }

    @GetMapping("/admin/member/update")
    public String toUpdate(@RequestParam(required = false) Long id, RedirectAttributes redirect, Model model) {
        Member member = new Member();

        if (id != null) {
            Optional<Member> optional = memberRepository.findById(id);
            if (optional.isPresent()) {
                member = optional.get();
            } else {
                redirect.addFlashAttribute("error", "Ce membre n'existe pas !");
                return "redirect:/admin/member";
            }
        }

        model.addAttribute( "member", member);
        return "admin/updateMember";
    }

    @PostMapping("/admin/member/update")
    public String update(@Valid Member member,
                         BindingResult result,
                         @RequestParam (name = "picture", required = false) MultipartFile pictureFile,
                         RedirectAttributes redirect,
                         Model model) {

        if (result.hasErrors()) {
            model.addAttribute("hasError", true);
            model.addAttribute("member", member);
            return "admin/updateMember";
        }

        Long id = member.getId();
        String path = "";
        if (id != null) {
            path = memberRepository.findById(id).get().getPicturePath();
        }
        if (!pictureFile.isEmpty()) {
            String fileName = (member.getFirstname() + " " + member.getLastname()).replaceAll(" ", "_").toLowerCase();
            path = fileUpload.writeFile(pictureFile, DIR, fileName);
        }

        member.setPicturePath(path);
        memberRepository.save(member);

        if (id != null) {
            redirect.addFlashAttribute("success", "Le membre à bien été mis à jour.");
        } else {
            redirect.addFlashAttribute("success", "Le membre à bien été ajouté.");
        }
        return "redirect:/admin/member";
    }

    @GetMapping("/admin/member/delete/{id}")
    public String remove(RedirectAttributes redirect, @PathVariable Long id) {
        Member toDelete = memberRepository.findById(id).get();
        fileUpload.deleteFile(toDelete.getPicturePath());
        memberRepository.delete(toDelete);

        redirect.addFlashAttribute("success", "Le membre à bien été supprimé.");
        return "redirect:/admin/member";
    }
}