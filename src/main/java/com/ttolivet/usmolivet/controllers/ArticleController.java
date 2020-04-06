package com.ttolivet.usmolivet.controllers;

import com.ttolivet.usmolivet.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository repo;

    @GetMapping("/articles")
    public String toArticles(Model model) {

        model.addAttribute("articles", repo.findAllByOrderByDateDesc());
        return "client/articles";
    }

    @GetMapping("/article/{id}")
    public String toArticle(@PathVariable Long id, Model model) {

        model.addAttribute("article", repo.findById(id).get());
        return "client/article";
    }

}
