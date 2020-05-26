package com.ttolivet.usmolivet.controllers;

import com.ttolivet.usmolivet.entities.Article;
import com.ttolivet.usmolivet.repositories.ArticleRepository;
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
public class AdminArticleController {

    private String DIR = "article";

    @Autowired
    private ArticleRepository repo;

    @Autowired
    private FileUpload fileUpload;

    @Value("${app.uploads.dir}")
    private String uploads;

    @Autowired
    private String baseDir;

    @GetMapping("/admin/article")
    public String toList(Model model) {
        model.addAttribute("articles", repo.findAll());
        return "admin/adminArticle";
    }

    @GetMapping("/admin/article/update")
    public String toUpdate(@RequestParam(required = false) Long id, RedirectAttributes redirect, Model model) {
        Article article = new Article();

        if (id != null) {
            Optional<Article> optional = repo.findById(id);
            if (optional.isPresent()) {
                article = optional.get();
            } else {
                redirect.addFlashAttribute("error", "Cet article n'existe pas !");
                return "redirect:/admin/article";
            }
        }

        model.addAttribute( "article", article);
        return "admin/updateArticle";
    }

    @PostMapping("/admin/article/update")
    public String update(@Valid Article article,
                         BindingResult result,
                         @RequestParam (name = "picture", required = false) MultipartFile pictureFile,
                         RedirectAttributes redirect,
                         Model model) {

        if (result.hasErrors()) {
            model.addAttribute("hasError", true);
            model.addAttribute("article", article);
            return "admin/updateArticle";
        }

        Long id = article.getId();
        String path = "";
        if (id != null) {
            path = repo.findById(id).get().getPicturePath();
        }
        if (!pictureFile.isEmpty()) {
            String fileName = article.getTitle().replaceAll(" ", "_").toLowerCase();
            path = fileUpload.writeFile(pictureFile, DIR, fileName);
        }

        article.setPicturePath(path);
        repo.save(article);

        if (id != null) {
            redirect.addFlashAttribute("success", "L'article à bien été mis à jour.");
        } else {
            redirect.addFlashAttribute("success", "L'article à bien été ajouté.");
        }
        return "redirect:/admin/article";
    }

    @GetMapping("/admin/article/delete/{id}")
    public String remove(RedirectAttributes redirect, @PathVariable Long id) {
        Article toDelete = repo.findById(id).get();
        fileUpload.deleteFile(toDelete.getPicturePath());
        repo.delete(toDelete);

        redirect.addFlashAttribute("success", "L'article à bien été supprimé.");
        return "redirect:/admin/article";
    }

}
