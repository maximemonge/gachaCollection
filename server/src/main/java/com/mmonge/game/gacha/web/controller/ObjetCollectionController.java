package com.mmonge.game.gacha.web.controller;

import com.mmonge.game.gacha.model.dto.ObjetCollectionDTO;
import com.mmonge.game.gacha.services.ObjetCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/objetCollection")
public class ObjetCollectionController {
    private ObjetCollectionService objetCollectionService;

    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<List<ObjetCollectionDTO>> getAllObjetCollection() {
        return ResponseEntity.ok(objetCollectionService.findAll());
    }

    @GetMapping(path="/utilisateurCollection")
    public @ResponseBody ResponseEntity<List<ObjetCollectionDTO>> getAllObjetCollectionByUtilisateurId(String utilisateurId) {
        return ResponseEntity.ok(objetCollectionService.findAllByUtilisateurId(Long.valueOf(utilisateurId)));
    }

    @Autowired
    public void setObjetCollectionService(ObjetCollectionService objetCollectionService) {
        this.objetCollectionService = objetCollectionService;
    }
}
