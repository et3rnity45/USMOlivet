package com.ttolivet.usmolivet.controllers;

import com.ttolivet.usmolivet.entities.Article;
import com.ttolivet.usmolivet.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

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
    public String toArticle(@PathVariable Long id, RedirectAttributes redirect, Model model) {

        Optional<Article> optional = repo.findById(id);
        if (!optional.isPresent()) {
            redirect.addFlashAttribute("error", "Cet article n'existe pas !");
            return "redirect:/articles";
        }

        model.addAttribute("article",optional.get());
        return "client/article";
    }

}
