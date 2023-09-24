package com.mmonge.game.gacha.web.controller;

import com.mmonge.game.gacha.exception.DuplicationDonneeException;
import com.mmonge.game.gacha.model.dto.UtilisateurDTO;
import com.mmonge.game.gacha.model.pojo.UtilisateurPojo;
import com.mmonge.game.gacha.services.UtilisateurService;
import com.mmonge.game.gacha.web.controller.cors.ControllerConfig;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
@AllArgsConstructor
public class UtilisateurController extends ControllerConfig {
    private final UtilisateurService utilisateurService;

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<UtilisateurDTO> login(@RequestBody UtilisateurPojo utilisateurRequest) {
        try {
            UtilisateurDTO utilisateur = utilisateurService.login(utilisateurRequest.getIdentifiant(), utilisateurRequest.getMotDePasse());
            return ResponseEntity.ok(utilisateur);
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping(path = "/new", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<UtilisateurDTO> creerUtilisateur(@RequestBody UtilisateurPojo utilisateurRequest) {
        try {
            UtilisateurDTO utilisateur = utilisateurService.creerUtilisateur(utilisateurRequest.getIdentifiant(), utilisateurRequest.getMotDePasse());
            return ResponseEntity.ok(utilisateur);
        } catch (DuplicationDonneeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping(path = "/monnaie", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<UtilisateurDTO> gagnerMonnaie(@RequestBody UtilisateurDTO utilisateur) {
        return ResponseEntity.ok(utilisateurService.ajouterMonnaie(utilisateur.getId(), utilisateur.getMonnaie()));
    }
}
