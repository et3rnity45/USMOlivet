package com.ttolivet.usmolivet.controllers;

import com.ttolivet.usmolivet.repositories.MemberRepository;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClubController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/club/bureau")
    public String toBureau(Model model) {

        model.addAttribute("members", memberRepository.findAll());
        return "client/club/bureau";
    }

    @GetMapping("/club/historique")
    public String toHistory(Model model) {

        return "client/club/historique";
    }

    @GetMapping("/club/projet")
    public String toProject(Model model) {

        return "client/club/projet";
    }

}
