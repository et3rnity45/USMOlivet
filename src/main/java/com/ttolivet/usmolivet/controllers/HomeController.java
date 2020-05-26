package com.ttolivet.usmolivet.controllers;

import com.ttolivet.usmolivet.repositories.ArticleRepository;
import com.ttolivet.usmolivet.repositories.SponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/")
    public String toHome(Model model) {
        model.addAttribute("articles", articleRepository.findTop2ByOrderByDateDesc());

        return "client/home";
    }
}
