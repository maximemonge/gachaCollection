package com.mmonge.game.gacha.web.controller;

import com.mmonge.game.gacha.exception.DuplicationDonneeException;
import com.mmonge.game.gacha.model.pojo.UtilisateurPojo;
import com.mmonge.game.gacha.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin(origins = "http://localhost:8080")
public class UtilisateurController {
    private UtilisateurService utilisateurService;

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Long> login(@RequestBody UtilisateurPojo utilisateur) {
        try {
            Long userId = utilisateurService.login(utilisateur.getIdentifiant(), utilisateur.getMotDePasse());
            return ResponseEntity.ok(userId);
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping(path = "/new", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Long> creerUtilisateur(@RequestBody UtilisateurPojo utilisateur) {
        try {
            Long userId = utilisateurService.creerUtilisateur(utilisateur.getIdentifiant(), utilisateur.getMotDePasse());
            return ResponseEntity.ok(userId);
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (DuplicationDonneeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Autowired
    public void setUtilisateurService(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }
}
