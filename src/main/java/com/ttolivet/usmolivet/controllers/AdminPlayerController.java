package com.ttolivet.usmolivet.controllers;

import com.ttolivet.usmolivet.repositories.PlayerRepository;
import com.ttolivet.usmolivet.services.ConnectApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminPlayerController {

    @Autowired
    private ConnectApi connectApi;

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/admin/player")
    public String toList(Model model) {
        model.addAttribute("players", playerRepository.findAllByOrderByPointsDesc());

        return "admin/adminPlayer";
    }

    @PostMapping("/admin/player")
    public String refresh(Model model) {
        playerRepository.deleteAll();
        playerRepository.saveAll(connectApi.getClubPlayers());

        model.addAttribute("players", playerRepository.findAllByOrderByPointsDesc());
        model.addAttribute("success", "La liste à bien été mise à jour.");
        return "admin/adminPlayer";
    }

}
