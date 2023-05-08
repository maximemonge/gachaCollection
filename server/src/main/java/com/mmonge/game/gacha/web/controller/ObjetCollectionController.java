package com.mmonge.game.gacha.web.controller;

import com.mmonge.game.gacha.model.dto.ObjetCollectionDTO;
import com.mmonge.game.gacha.services.ObjetCollectionService;
import com.mmonge.game.gacha.web.controller.cors.ControllerConfig;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/objetCollection")
@AllArgsConstructor
public class ObjetCollectionController extends ControllerConfig {
    private ObjetCollectionService objetCollectionService;

    @GetMapping(path = "/all")
    public @ResponseBody ResponseEntity<List<ObjetCollectionDTO>> getAllObjetCollection() {
        return ResponseEntity.ok(objetCollectionService.findAll());
    }

    @GetMapping(path = "/all/user/{utilisateurId}")
    public @ResponseBody ResponseEntity<List<Long>> getAllObjetCollectionByUtilisateurId(@PathVariable Long utilisateurId) {
        return ResponseEntity.ok(objetCollectionService.findAllIdsByUtilisateurId(utilisateurId));
    }

    @GetMapping(path = "/obtenir/user/{utilisateurId}")
    public @ResponseBody ResponseEntity<ObjetCollectionDTO> obtenirUnObjet(@PathVariable Long utilisateurId) {
        return ResponseEntity.ok(objetCollectionService.obtenirUnObjet(utilisateurId));
    }
}
